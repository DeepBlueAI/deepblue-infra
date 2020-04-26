package io.deepblueai.infra.generator.core;


import com.google.common.base.CaseFormat;
import com.google.common.base.Strings;
import io.deepblueai.infra.generator.consts.DBDataType;
import io.deepblueai.infra.generator.consts.DBType;
import io.deepblueai.infra.generator.model.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.*;

/**
 * 根据数据库表生成Java类
 *
 * @author lutiehua
 * @date 2017/9/27
 */
public abstract class AbstractGeneratedJavaDatabaseClass extends AbstractGeneratedJavaClass {

  /**
   * 用于Boolean值判断
   */
  private final static String YES = "YES";
  /**
   * JSON数据类型
   */
  private final static String JSON = "JSON";
  /**
   * 数据类型转换对应关系
   */
  protected final Map<String, String> DB_TYPE_2_JAVA_TYPE = new HashMap<>();
  /**
   * 数据库连接
   */
  protected DatabaseInfo databaseInfo;
  protected Map<String, String> ignoreFieldMap = new HashMap<>();

  /**
   * 构造函数
   *
   * @param generatorParam
   */
  public AbstractGeneratedJavaDatabaseClass(GeneratorParam generatorParam, TableInfo tableInfo, boolean isClient) {
    super(generatorParam, tableInfo, isClient);

    // 忽略这些字段，不用生成到DTO对象中
    ignoreFieldMap.put("deleted", "deleted");
    ignoreFieldMap.put("created_time", "created_time");
    ignoreFieldMap.put("updated_time", "updated_time");
    ignoreFieldMap.put("created_by", "created_by");
    ignoreFieldMap.put("updated_by", "updated_by");

    databaseInfo = generatorParam.getDatabaseInfo();

    DB_TYPE_2_JAVA_TYPE.put("varchar", "java.lang.String");
    DB_TYPE_2_JAVA_TYPE.put("char", "java.lang.String");
    DB_TYPE_2_JAVA_TYPE.put("text", "java.lang.String");
    DB_TYPE_2_JAVA_TYPE.put("json", "java.lang.String");
    DB_TYPE_2_JAVA_TYPE.put("boolean", "java.lang.Integer");
    DB_TYPE_2_JAVA_TYPE.put("tinyint", "java.lang.Integer");
    DB_TYPE_2_JAVA_TYPE.put("tinyint unsigned", "java.lang.Integer");
    DB_TYPE_2_JAVA_TYPE.put("smallint", "java.lang.Integer");
    DB_TYPE_2_JAVA_TYPE.put("smallint unsigned", "java.lang.Integer");
    DB_TYPE_2_JAVA_TYPE.put("int", "java.lang.Integer");
    DB_TYPE_2_JAVA_TYPE.put("int unsigned", "java.lang.Integer");
    DB_TYPE_2_JAVA_TYPE.put("bigint", "java.lang.Long");
    DB_TYPE_2_JAVA_TYPE.put("bigint unsigned", "java.lang.Long");
    DB_TYPE_2_JAVA_TYPE.put("id", "java.lang.Long");
    DB_TYPE_2_JAVA_TYPE.put("float", "java.lang.Float");
    DB_TYPE_2_JAVA_TYPE.put("float unsigned", "java.lang.Float");
    DB_TYPE_2_JAVA_TYPE.put("double", "java.lang.Double");
    DB_TYPE_2_JAVA_TYPE.put("double unsigned", "java.lang.Double");
    DB_TYPE_2_JAVA_TYPE.put("decimal", "java.math.BigDecimal");
    DB_TYPE_2_JAVA_TYPE.put("decimal unsigned", "java.math.BigDecimal");
    DB_TYPE_2_JAVA_TYPE.put("date", "java.util.Date");
    DB_TYPE_2_JAVA_TYPE.put("time", "java.util.Date");
    DB_TYPE_2_JAVA_TYPE.put("bit", "java.lang.Boolean");
    DB_TYPE_2_JAVA_TYPE.put("datetime", "java.util.Date");
    DB_TYPE_2_JAVA_TYPE.put("timestamp", "java.util.Date");

    // 基础表名
    String tableName = tableInfo.getName();

    // 基础类名
    String modelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    // Package信息
    PackageInfo packageInfo = generatorParam.getPackageInfo();

    // 基础包名
    String basePackage = packageInfo.getBasePackage();

    // 创建请求对象定义
    String reqCreateClassName = String.format("%s.%s.%sCreateReq", basePackage, packageInfo.getReqPackage(), modelName);
    reqCreateClassName = super.parseJavaImportType(reqCreateClassName);
    model.setCreateReqClassName(reqCreateClassName);
    String reqCreateObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, reqCreateClassName);
    model.setCreateReqObjectName(reqCreateObjectName);

    // 搜索请求对象定义
    String reqSearchClassName = String.format("%s.%s.%sSearchReq", basePackage, packageInfo.getReqPackage(), modelName);
    reqSearchClassName = super.parseJavaImportType(reqSearchClassName);
    model.setSearchReqClassName(reqSearchClassName);
    String reqSearchObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, reqSearchClassName);
    model.setSearchReqObjectName(reqSearchObjectName);


    // 更新请求对象定义
    String reqUpdateClassName = String.format("%s.%s.%sUpdateReq", basePackage, packageInfo.getReqPackage(), modelName);
    reqUpdateClassName = super.parseJavaImportType(reqUpdateClassName);
    model.setUpdateReqClassName(reqUpdateClassName);
    String reqUpdateObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, reqUpdateClassName);
    model.setUpdateReqObjectName(reqUpdateObjectName);

    // 单对象响应定义
    String respSingleClassName = String.format("%s.%s.%sResp", basePackage, packageInfo.getRespPackage(), modelName);
    respSingleClassName = super.parseJavaImportType(respSingleClassName);
    model.setSingleRespClassName(respSingleClassName);
    String respSingleObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, respSingleClassName);
    model.setSingleRespObjectName(respSingleObjectName);

    // 分页对象响应定义
    String respPageClassName = String.format("%s.%s.%sPageResp", basePackage, packageInfo.getRespPackage(), modelName);
    respPageClassName = super.parseJavaImportType(respPageClassName);
    model.setPageRespClassName(respPageClassName);
    String respPageObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, respPageClassName);
    model.setPageRespObjectName(respPageObjectName);
  }

  /**
   * 获取数据库连接
   *
   * @return
   * @throws Exception
   */
  protected Connection getConnection() throws Exception {
    DriverManager.setLoginTimeout(3);
    DBType dbType = DBType.valueOf(databaseInfo.getDbType());
    Class.forName(dbType.getDriverClass());
    String url = getConnectionURL();
    Properties props = new Properties();
    props.setProperty("user", databaseInfo.getDbUsername());
    props.setProperty("password", databaseInfo.getDbPassword());
    //设置可以获取remarks信息
    props.setProperty("remarks", "true");
    //设置可以获取tables remarks信息
    props.setProperty("useInformationSchema", "true");
    Connection connection = DriverManager.getConnection(url, props);
    return connection;
  }

  protected String getConnectionURL() throws ClassNotFoundException {
    DBType dbType = DBType.valueOf(databaseInfo.getDbType());
    String connectionRUL = String.format(dbType.getConnectionUrlPattern(), databaseInfo.getDbIP(),
            databaseInfo.getDbPort(), databaseInfo.getDbName());
    return connectionRUL;
  }

  /**
   * 读取表结构
   *
   * @return
   * @throws Exception
   */
  protected List<DBField> getTableColumns() throws Exception {
    Connection connection = getConnection();
    DatabaseMetaData metaData = connection.getMetaData();
    String primaryKeyFiledName = getTablePrimaryKey(metaData, tableInfo.getName());
    ResultSet resultSet = metaData.getColumns(null, null, tableInfo.getName(), null);
    List<DBField> fieldList = new ArrayList<>();
    while (resultSet.next()) {
      DBField field = new DBField();
      field.setColumnName(resultSet.getString("COLUMN_NAME"));
      field.setDataType(resultSet.getInt("DATA_TYPE"));
      field.setTypeName(resultSet.getString("TYPE_NAME"));
      field.setColumnSize(resultSet.getInt("COLUMN_SIZE"));
      field.setDecimalDigits(resultSet.getInt("DECIMAL_DIGITS"));
      field.setNullable(YES.equalsIgnoreCase(resultSet.getString("IS_NULLABLE")));
      field.setAutoIncrement(YES.equalsIgnoreCase(resultSet.getString("IS_AUTOINCREMENT")));
      field.setRemarks(resultSet.getString("REMARKS"));
      field.setColumnDef(resultSet.getObject("COLUMN_DEF"));
      field.setCharOctetLength(resultSet.getInt("CHAR_OCTET_LENGTH"));

      if (null != primaryKeyFiledName) {
        if (field.getColumnName().equalsIgnoreCase(primaryKeyFiledName)) {
          field.setPrimaryKey(true);
        } else {
          field.setPrimaryKey(false);
        }
      }
      fieldList.add(field);
    }

    return fieldList;
  }

  /**
   * 读取主键
   *
   * @param metaData
   * @param tableName
   * @return
   * @throws Exception
   */
  protected String getTablePrimaryKey(DatabaseMetaData metaData, String tableName) throws Exception {
    ResultSet rs = metaData.getPrimaryKeys(null, null, tableName);
    while (rs.next()) {
      return rs.getString("COLUMN_NAME");
    }

    return null;
  }

  /**
   * 数据库类型转Java类型
   *
   * @param dbType
   * @return
   */
  public String toJavaType(String dbType) {
    dbType = dbType.toLowerCase();
    if (DB_TYPE_2_JAVA_TYPE.containsKey(dbType)) {
      return DB_TYPE_2_JAVA_TYPE.get(dbType);
    } else {
      return null;
    }
  }

  /**
   * 设置字段默认值
   *
   * @param field
   */
  protected String getDefaultValue(DBField field) {
    if (null != field.getColumnDef()) {
      if (isCharType(field.getDataType()) || isNumberType(field.getDataType())) {
        // 字符串,整数
        String value = field.getColumnDef().toString();
        if (null == value) {
          return "null";
        } else if (Strings.isNullOrEmpty(value)) {
          return "\"\"";
        } else {
          return value;
        }
      } else if (isDecimalType(field.getDataType())) {
        // 浮点数 TODO

      }

      // do nothing
      return null;
    } else {
      return null;
    }
  }


  /**
   * 是否为空的校验注解
   *
   * @param field 字段
   * @return
   */
  protected JavaAnnotation getNullValidationAnnotation(DBField field) {
    return getNullValidationAnnotation(field, false);
  }

  /**
   * 是否为空的校验注解
   *
   * @param field 字段
   * @param group 是否增加Group属性
   * @return
   */
  protected JavaAnnotation getNullValidationAnnotation(DBField field, boolean group) {
    JavaAnnotation annotation = new JavaAnnotation();

    if (isCharType(field.getDataType())) {
      // 字符串不能为空
      String importName = super.parseJavaImportType("javax.validation.constraints.NotBlank");
      annotation.setName(importName);
    } else {
      // 基本类型不能为空
      String importName = super.parseJavaImportType("javax.validation.constraints.NotNull");
      annotation.setName(importName);
    }

    // 只在创建时做非空校验
//        if (group) {
//            annotation.addObjectProp("groups", "{InsertGroup.class}");
//        }

    return annotation;
  }

  /**
   * 字符串长度校验的注解
   *
   * @param field 字段
   * @return
   */
  protected JavaAnnotation getLenValidationAnnotation(DBField field) {
    if (isCharType(field.getDataType())) {
      // JSON类型不加字符串长度校验
      if (JSON.equalsIgnoreCase(field.getTypeName())) {
        return null;
      }

      JavaAnnotation annotation = new JavaAnnotation();
      String importName = super.parseJavaImportType("javax.validation.constraints.Size");
      annotation.setName(importName);
      annotation.addProp("max", field.getColumnSize());
      return annotation;
    } else {
      return null;
    }
  }

  /**
   * API文档的注解
   *
   * @param field 字段
   * @return
   */
  protected JavaAnnotation getApiDocumentAnnotation(DBField field) {
    JavaAnnotation annotation = new JavaAnnotation();
    String importName = super.parseJavaImportType("io.swagger.annotations.ApiModelProperty");
    annotation.setName(importName);
    String remark = field.getRemarks();
    // 字段注释里面如果有"，生成的@ApiModelProperty中需要替换为\"
    remark = remark.replaceAll("\"", "\\\\\"");
    annotation.addProp("value", remark);
    if (field.isNullable()) {
      annotation.addProp("required", false);
    } else {
      annotation.addProp("required", true);
    }
    Object defaultValue = field.getColumnDef();
    if (null != defaultValue) {
      annotation.addProp("example", defaultValue.toString());
    }

    return annotation;
  }

  protected boolean isCharType(int type) {
    if (type == DBDataType.CHAR) {
      return true;
    }

    if (type == DBDataType.VARCHAR) {
      return true;
    }

    return type == DBDataType.TEXT;

  }

  protected boolean isNumberType(int type) {
    if (type == DBDataType.INT) {
      return true;
    }

    if (type == DBDataType.TINYINT) {
      return true;
    }

    if (type == DBDataType.SMALLINT) {
      return true;
    }

    return type == DBDataType.BIGINT;

  }

  protected boolean isDecimalType(int type) {
    return type == DBDataType.DECIMAL;

  }
}
