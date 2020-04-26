package io.deepblueai.infra.generator.core;

import io.deepblueai.infra.generator.model.DataModel;
import io.deepblueai.infra.generator.model.GeneratorParam;

/**
 * @author lu
 * @date 2018/5/24
 */
public class GeneratedDocTemplateFile extends AbstractGeneratedFile {

  private String fileName;

  public GeneratedDocTemplateFile(GeneratorParam generatorParam) {
    super(generatorParam);

    String rootDir = generatorParam.getPackageInfo().getProjectPath();
    String resourcePath = generatorParam.getPackageInfo().getResourcePath();
    fileName = rootDir + "/" + resourcePath + "/templates/" + "doc.ftl";
  }

  /**
   * 不解析变量
   *
   * @return
   * @throws Exception
   */
  @Override
  public boolean generateFile() throws Exception {
    return super.generateFile(false);
  }

  @Override
  public String getTemplateName() throws Exception {
    return "doc.ftl";
  }

  @Override
  public DataModel getDataModel() {
    return new DataModel();
  }

  @Override
  public String getFileName() {
    return fileName;
  }
}
