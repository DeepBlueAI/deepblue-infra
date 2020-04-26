package io.deepblueai.infra.generator.api;

import io.deepblueai.infra.generator.core.GeneratedJavaClientClass;
import io.deepblueai.infra.generator.core.GeneratedJavaControllerImplClass;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.TableInfo;
import org.springframework.stereotype.Component;

@Component
public class SpringCloudMVCGenerator extends SpringMVCGenerator {

  @Override
  protected void generateController(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {

    // Client
    GeneratedJavaClientClass controllerFacadeClass = new GeneratedJavaClientClass(generatorParam, tableInfo);
    controllerFacadeClass.generateFile();

    // Controller
    GeneratedJavaControllerImplClass controllerImplClass = new GeneratedJavaControllerImplClass(generatorParam, tableInfo);
    controllerImplClass.generateFile();
  }
}