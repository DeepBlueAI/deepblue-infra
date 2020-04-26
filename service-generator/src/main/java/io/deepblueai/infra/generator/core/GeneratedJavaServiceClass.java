package io.deepblueai.infra.generator.core;

import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.TableInfo;

/**
 * 生成Service接口类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaServiceClass extends AbstractGeneratedJavaTemplateClass {

  public GeneratedJavaServiceClass(GeneratorParam generatorParam, TableInfo tableInfo) {
    super(generatorParam, tableInfo, false);
  }

  /**
   * 模板
   *
   * @return
   */
  @Override
  public String getTemplateName() throws Exception {
    return "service-id.ftl";
  }

  /**
   * 包名
   *
   * @return
   */
  @Override
  public String getJavaPackageName() {
    return generatorParam.getPackageInfo().getServicePackage();
  }

  /**
   * 类名后缀
   *
   * @return
   */
  @Override
  public String getJavaClassNamePostfix() {
    return "Service";
  }

  /**
   * 类说明后缀
   *
   * @return
   */
  @Override
  public String getJavaClassRemarkPostfix() {
    return "服务接口类";
  }
}
