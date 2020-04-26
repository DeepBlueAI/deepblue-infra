package io.deepblueai.infra.generator.core;

import com.google.common.base.CaseFormat;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.TableInfo;

/**
 * 生成Service接口类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaExceptionNotExistClass extends AbstractGeneratedJavaTemplateClass {

  public GeneratedJavaExceptionNotExistClass(GeneratorParam generatorParam, TableInfo tableInfo) {
    super(generatorParam, tableInfo, true);
    String projectName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, generatorParam.getProjectInfo().getName().toLowerCase());
    model.setBaseExceptionClassName(projectName.concat("Exception"));
  }

  /**
   * 模板
   *
   * @return
   */
  @Override
  public String getTemplateName() throws Exception {
    return "exception-not-exist.ftl";
  }

  /**
   * 包名
   *
   * @return
   */
  @Override
  public String getJavaPackageName() {
    return generatorParam.getPackageInfo().getExceptionPackage();
  }

  /**
   * 类名后缀
   *
   * @return
   */
  @Override
  public String getJavaClassNamePostfix() {
    return "NotExistException";
  }

  /**
   * 类说明后缀
   *
   * @return
   */
  @Override
  public String getJavaClassRemarkPostfix() {
    return "资源不存在异常类";
  }
}
