package io.deepblueai.infra.generator.core;

import com.google.common.base.CaseFormat;
import io.deepblueai.infra.generator.model.GeneratorParam;

/**
 * 生成ApplicationTests类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaAppTestClass extends GeneratedJavaAppClass {

  private String fileName;

  public GeneratedJavaAppTestClass(GeneratorParam generatorParam) {
    super(generatorParam);

    String projectName = generatorParam.getProjectInfo().getName();
    projectName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, projectName.toLowerCase());
    String className = projectName + "ApplicationTests";
    String packageName = generatorParam.getPackageInfo().getBasePackage();
    String testPath = generatorParam.getPackageInfo().getTestPath();
    String packagePath = packageName.replaceAll("\\.", "/");
    fileName = buildActualDir(testPath, false).concat("/")
            .concat(packagePath).concat("/")
            .concat(className).concat(".java");
  }

  /**
   * 模板
   *
   * @return
   */
  @Override
  public String getTemplateName() {
    return "app-test.ftl";
  }

  /**
   * 文件名称
   *
   * @return
   */
  @Override
  public String getFileName() {
    return fileName;
  }
}
