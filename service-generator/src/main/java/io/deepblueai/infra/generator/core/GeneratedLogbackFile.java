package io.deepblueai.infra.generator.core;

import io.deepblueai.infra.generator.model.DataModel;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.LogbackModel;

/**
 * 生成log4j2.xml文件
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public class GeneratedLogbackFile extends AbstractGeneratedFile {

  private LogbackModel model;

  private String fileName;

  public GeneratedLogbackFile(GeneratorParam generatorParam) {
    super(generatorParam);
    model = new LogbackModel();
    model.setGroupId(generatorParam.getProjectInfo().getGroupId());
    String resourcePath = generatorParam.getPackageInfo().getResourcePath();
    fileName = buildActualDir(resourcePath, false).concat("/").concat("spring-logback.xml");
  }

  /**
   * 模板
   *
   * @return
   */
  @Override
  public String getTemplateName() {
    return "spring-logback-xml.ftl";
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
