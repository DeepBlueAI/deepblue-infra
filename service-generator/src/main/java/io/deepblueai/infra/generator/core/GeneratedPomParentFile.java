package io.deepblueai.infra.generator.core;


import io.deepblueai.infra.generator.model.DataModel;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.PomModel;

/**
 * 生成pom.xml文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedPomParentFile extends AbstractGeneratedFile {

  private PomModel model;

  private String fileName;

  public GeneratedPomParentFile(GeneratorParam generatorParam) {
    super(generatorParam);

    model = new PomModel();
    model.setProjectInfo(generatorParam.getProjectInfo());
    model.setDependencies(generatorParam.getDependencies());
    model.setServiceType(generatorParam.getProjectInfo().getServiceType());
    fileName = generatorParam.getPackageInfo().getProjectPath().concat("/").concat("pom.xml");

  }

  /**
   * 模板
   *
   * @return
   */
  @Override
  public String getTemplateName() {
    return "pom-parent-xml.ftl";
  }

  /**
   * 变量数据
   *
   * @return
   */
  @Override
  public DataModel getDataModel() {
    return model;
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
