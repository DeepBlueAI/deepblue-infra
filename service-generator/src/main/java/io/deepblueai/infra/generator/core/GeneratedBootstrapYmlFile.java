package io.deepblueai.infra.generator.core;


import io.deepblueai.infra.generator.model.GeneratorParam;

/**
 * 生成bootstrap.properties文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedBootstrapYmlFile extends AbstractGeneratedYmlFile {

  private String fileName;

  public GeneratedBootstrapYmlFile(GeneratorParam generatorParam) {
    super(generatorParam);
    String resourcePath = generatorParam.getPackageInfo().getResourcePath();
    fileName = buildActualDir(resourcePath, false).concat("/").concat("bootstrap.yml");
  }

  /**
   * 模板
   *
   * @return
   */
  @Override
  public String getTemplateName() {
    return "bootstrap-yml.ftl";
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
