package io.deepblueai.infra.generator.core;

import com.google.common.base.CaseFormat;
import io.deepblueai.infra.generator.model.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生成DTO类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaReqUpdateClass extends AbstractGeneratedJavaDatabaseClass {

  private Map<String, String> ignoreFieldMap = new HashMap<>();

  /**
   * 构造函数
   *
   * @param generatorParam
   * @param tableInfo
   */
  public GeneratedJavaReqUpdateClass(GeneratorParam generatorParam, TableInfo tableInfo) {
    super(generatorParam, tableInfo, true);

    // 忽略这些字段，不用生成到DTO对象中
    ignoreFieldMap.put("deleted", "deleted");
    ignoreFieldMap.put("created_time", "created_time");
    ignoreFieldMap.put("updated_time", "updated_time");
    ignoreFieldMap.put("created_by", "created_by");
    ignoreFieldMap.put("updated_by", "updated_by");
    ignoreFieldMap.put("id", "id");

    try {
      List<DBField> fieldList = getTableColumns();
      for (DBField field : fieldList) {
        // 有默认值的基础字段，不用添加到DTO中
        if (ignoreFieldMap.containsKey(field.getColumnName())) {
          continue;
        }

        // 自增主键字段自动赋值，不用添加到DTO中
        if (field.isPrimaryKey() && field.isAutoIncrement()) {
          continue;
        }

        // 自增字段不用赋值，不用添加到DTO中
        if (field.isAutoIncrement()) {
          continue;
        }

        JavaField javaField = new JavaField();
        // 将数据库字段名转化为Java属性名，product_type => productType
        String fieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getColumnName());
        javaField.setName(fieldName);
        String fieldType = toJavaType(field.getTypeName());
        fieldType = super.parseJavaImportType(fieldType);
        javaField.setType(fieldType);
        javaField.setRemark(field.getRemarks());

        // 不能为空，且没有默认值，增加非空校验
        String defaultValue = getDefaultValue(field);
        if (!field.isNullable() && null == defaultValue) {
          // 非空校验的注解
          javaField.getAnnotations().add(getNullValidationAnnotation(field, true));
        }

        // 字符串长度校验的注解
        JavaAnnotation annotation = getLenValidationAnnotation(field);
        if (null != annotation) {
          javaField.getAnnotations().add(annotation);
        }

        // API文档的注解
        javaField.getAnnotations().add(getApiDocumentAnnotation(field));

        javaFieldList.add(javaField);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getTemplateName() {
    return "request-update.ftl";
  }

  /**
   * 包名
   *
   * @return
   */
  @Override
  public String getJavaPackageName() {
    return generatorParam.getPackageInfo().getReqPackage();
  }

  /**
   * 类名后缀
   *
   * @return
   */
  @Override
  public String getJavaClassNamePostfix() {
    return "UpdateReq";
  }

  /**
   * 类说明后缀
   *
   * @return
   */
  @Override
  public String getJavaClassRemarkPostfix() {
    return "更新对象";
  }
}