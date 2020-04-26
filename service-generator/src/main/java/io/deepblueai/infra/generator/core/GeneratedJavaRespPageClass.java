package io.deepblueai.infra.generator.core;

import com.google.common.base.CaseFormat;
import io.deepblueai.infra.generator.model.DBField;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.JavaField;
import io.deepblueai.infra.generator.model.TableInfo;

import java.util.List;

/**
 * 生成VO类
 *
 * @author lutiehua
 * @date 2017/09/26
 */
public class GeneratedJavaRespPageClass extends AbstractGeneratedJavaDatabaseClass {

  /**
   * 构造函数
   *
   * @param generatorParam
   * @param tableInfo
   */
  public GeneratedJavaRespPageClass(GeneratorParam generatorParam, TableInfo tableInfo) {
    super(generatorParam, tableInfo, true);

    List<DBField> fieldList = null;
    try {
      fieldList = getTableColumns();
      for (DBField field : fieldList) {
        JavaField javaField = new JavaField();
        // 将数据库字段名转化为Java属性名，product_type => productType
        String fieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, field.getColumnName());
        javaField.setName(fieldName);
        String fieldType = toJavaType(field.getTypeName());
        fieldType = super.parseJavaImportType(fieldType);
        javaField.setType(fieldType);
        javaField.setRemark(field.getRemarks());

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
    return "response-page.ftl";
  }

  /**
   * 包名
   *
   * @return
   */
  @Override
  public String getJavaPackageName() {
    return generatorParam.getPackageInfo().getRespPackage();
  }

  /**
   * 类名后缀
   *
   * @return
   */
  @Override
  public String getJavaClassNamePostfix() {
    return "PageResp";
  }

  /**
   * 类说明后缀
   *
   * @return
   */
  @Override
  public String getJavaClassRemarkPostfix() {
    return "分页返回对象";
  }
}