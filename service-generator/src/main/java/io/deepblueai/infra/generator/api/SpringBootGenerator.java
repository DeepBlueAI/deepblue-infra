package io.deepblueai.infra.generator.api;


import io.deepblueai.infra.generator.consts.ServiceType;
import io.deepblueai.infra.generator.core.*;
import io.deepblueai.infra.generator.model.GeneratorParam;
import org.springframework.stereotype.Component;

/**
 * 普通Spring项目生成器
 *
 * @author lu
 * @date 2018/5/25
 */
@Component
public class SpringBootGenerator implements Generator {

  /**
   * 自动生成工程文件
   *
   * @param generatorParam
   * @throws Exception
   */
  @Override
  public void generateCode(GeneratorParam generatorParam) throws Exception {
    // Application
    GeneratedJavaAppClass generatedJavaAppClass = new GeneratedJavaAppClass(generatorParam);
    generatedJavaAppClass.generateFile();

    // ApplicationTest
    GeneratedJavaAppTestClass javaAppTestClass = new GeneratedJavaAppTestClass(generatorParam);
    javaAppTestClass.generateFile();

    // POM
    if (ServiceType.BASIC.equals(generatorParam.getProjectInfo().getServiceType())) {
      GeneratedPomParentFile pomParentFile = new GeneratedPomParentFile(generatorParam);
      pomParentFile.generateFile();

      GeneratedPomServiceFile pomServiceFile = new GeneratedPomServiceFile(generatorParam);
      pomServiceFile.generateFile();

      GeneratedPomClientFile pomClientFile = new GeneratedPomClientFile(generatorParam);
      pomClientFile.generateFile();
    } else {
      GeneratedPomGatherFile pomGatherFile = new GeneratedPomGatherFile(generatorParam);
      pomGatherFile.generateFile();
    }


    // Properties
    GeneratedBootstrapYmlFile bootPropFile = new GeneratedBootstrapYmlFile(generatorParam);
    bootPropFile.generateFile();

    // log4j2
    GeneratedLogbackFile log4j2File = new GeneratedLogbackFile(generatorParam);
    log4j2File.generateFile();

//    // Git ignore
//    GeneratedGitIgnoreFile gitIgnoreFile = new GeneratedGitIgnoreFile(generatorParam);
//    gitIgnoreFile.generateFile();

    // Doc Template
//    GeneratedDocTemplateFile docTemplateFile = new GeneratedDocTemplateFile(generatorParam);
//    docTemplateFile.generateFile();
//
//    // JSON配置文件
//    GeneratedHipsterConfigFile hipsterConfigFile = new GeneratedHipsterConfigFile();
//    hipsterConfigFile.generateCode(generatorParam);
  }
}
