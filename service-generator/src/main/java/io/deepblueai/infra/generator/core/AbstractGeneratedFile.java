package io.deepblueai.infra.generator.core;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import io.deepblueai.infra.generator.consts.ServiceType;
import io.deepblueai.infra.generator.model.DataModel;
import io.deepblueai.infra.generator.model.GeneratorParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * 根据模板生成文件
 * <p>
 * 模板+数据=文件
 *
 * @author jason
 * @date
 */
@Slf4j
public abstract class AbstractGeneratedFile {

  /**
   * 生成参数
   */
  protected GeneratorParam generatorParam;

  public AbstractGeneratedFile(GeneratorParam generatorParam) {
    this.generatorParam = generatorParam;
  }

  /**
   * 模板名称
   *
   * @return
   * @throws Exception
   */
  public abstract String getTemplateName() throws Exception;

  /**
   * 变量数据
   *
   * @return
   */
  public abstract DataModel getDataModel();

  /**
   * 文件名称
   *
   * @return
   */
  public abstract String getFileName();

  /**
   * 生成文件前的准备工作
   */
  public void beforeGenerateFile() {

  }

  /**
   * 生成文件
   *
   * @return
   * @throws Exception
   */
  public boolean generateFile() throws Exception {
    return generateFile(true);
  }

  protected String buildActualDir(String filePath, boolean isClient) {
    String rootDir = generatorParam.getPackageInfo().getProjectPath();
    String actualPath = rootDir;
    if (ServiceType.BASIC.equals(generatorParam.getProjectInfo().getServiceType())) {
      String aid = generatorParam.getProjectInfo().getArtifactId();
      actualPath = rootDir.concat("/").concat(isClient ? aid.concat("-client") : aid);
    }
    if (StringUtils.isNotBlank(filePath)) {
      actualPath = actualPath.concat("/").concat(filePath);
    }
    return actualPath;
  }

  /**
   * 生成文件
   *
   * @param parse 是否解析
   * @return
   * @throws Exception
   */
  public boolean generateFile(boolean parse) throws Exception {

    beforeGenerateFile();

    // 读取模板
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
    cfg.setClassForTemplateLoading(this.getClass(), "/templates/");
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    Template template = cfg.getTemplate(this.getTemplateName());

    // 创建空文件
    String fileName = this.getFileName();
    int pos = fileName.lastIndexOf("/");
    String fileDir = fileName.substring(0, pos + 1);

    File dir = new File(fileDir);
    if (!dir.exists()) {
      dir.mkdirs();
    }

    File file = new File(fileName);
    if (file.exists()) {
      file.delete();
    }
    file.createNewFile();

    // 根据模板生成文件
    Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
    if (parse) {
      template.process(this.getDataModel(), writer);
    } else {
      template.dump(writer);
    }
    writer.flush();
    writer.close();
    return true;
  }
}
