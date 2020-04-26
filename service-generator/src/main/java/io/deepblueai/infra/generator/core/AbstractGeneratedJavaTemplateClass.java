package io.deepblueai.infra.generator.core;

import com.google.common.base.CaseFormat;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.PackageInfo;
import io.deepblueai.infra.generator.model.TableInfo;

/**
 * 根据模板生成Java类的基类
 *
 * @author lutiehua
 * @date 2017/11/16
 */
public abstract class AbstractGeneratedJavaTemplateClass extends AbstractGeneratedJavaClass {

  public AbstractGeneratedJavaTemplateClass(GeneratorParam generatorParam, TableInfo tableInfo, boolean isClient) {
    super(generatorParam, tableInfo, isClient);

    // 基础表名
    String tableName = tableInfo.getName();

    // 基础类名
    String modelName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, tableName.toLowerCase());

    // Package信息
    PackageInfo packageInfo = generatorParam.getPackageInfo();

    // 基础包名
    String basePackage = packageInfo.getBasePackage();

    if (!isClient) {
      // Entity类和对象名称
      String modelClassName = String.format("%s.%s.%s", basePackage, packageInfo.getEntityPackage(), modelName);
      modelClassName = super.parseJavaImportType(modelClassName);
      model.setModelClassName(modelClassName);
      String modelObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelClassName);
      model.setModelObjectName(modelObjectName);

      // Service类和对象名称
      String serviceClassName = String.format("%s.%s.%sService", basePackage, packageInfo.getServicePackage(), modelName);
      serviceClassName = super.parseJavaImportType(serviceClassName);
      model.setServiceClassName(serviceClassName);
      String serviceObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, serviceClassName);
      model.setServiceObjectName(serviceObjectName);

      // ServiceImpl类名称
      String serviceImplClassName = String.format("%s.%s.impl.%sServiceImpl", basePackage, packageInfo.getServicePackage(), modelName);
      serviceImplClassName = super.parseJavaImportType(serviceImplClassName);
      model.setServiceImplClassName(serviceImplClassName);

      // 持久化类
      String mapperClassName = String.format("%s.%s.%sMapper", basePackage, packageInfo.getDaoPackage(), modelName);
      mapperClassName = super.parseJavaImportType(mapperClassName);
      model.setMapperClassName(mapperClassName);
      String mapperObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, mapperClassName);
      model.setMapperObjectName(mapperObjectName);

      // 转换器类
      String converterClassName = String.format("%s.%s.%sMapperConverter", basePackage, packageInfo.getConverterPackage(), modelName);
      converterClassName = super.parseJavaImportType(converterClassName);
      model.setConverterClassName(converterClassName);
      String converterObjectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, converterClassName);
      model.setConverterObjectName(converterObjectName);
    }

    // 资源不存在异常类
    String exceptionNotExistClassName = String.format("%s.%s.%sNotExistException", basePackage, packageInfo.getExceptionPackage(), modelName);
    exceptionNotExistClassName = super.parseJavaImportType(exceptionNotExistClassName);
    model.setNotExistExceptionClassName(exceptionNotExistClassName);

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
    // "/mapping"
    String classMapping = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, modelName);
    model.setClassMapping(classMapping);
    model.setServiceName(generatorParam.getProjectInfo().getName());
    model.setEntityName(modelName);
  }
}