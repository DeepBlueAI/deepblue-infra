package io.deepblueai.infra.generator.api;


import io.deepblueai.infra.generator.core.*;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.TableInfo;
import org.springframework.stereotype.Component;

/**
 * MVC代码生成器
 *
 * @author lutiehua
 * @date 2017/11/14
 */
@Component
public class SpringMVCGenerator implements Generator {

  /**
   * 自动生成代码
   *
   * @param generatorParam
   */
  @Override
  public void generateCode(GeneratorParam generatorParam) throws Exception {
    for (TableInfo tableInfo : generatorParam.getTables()) {
      generateController(generatorParam, tableInfo);
      generateService(generatorParam, tableInfo);
      generateModel(generatorParam, tableInfo);
//            refreshEntity(generatorParam, tableInfo);
//            generateInsertListMapper(generatorParam, tableInfo);
    }
    GeneratedJavaExceptionBaseClass errorCodeClass = new GeneratedJavaExceptionBaseClass(generatorParam);
    errorCodeClass.generateFile();
//        generateConfig(generatorParam);
  }

  protected void generateController(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {
    // Controller
    GeneratedJavaControllerClass javaControllerClass = new GeneratedJavaControllerClass(generatorParam, tableInfo);
    javaControllerClass.generateFile();
  }

  protected void generateService(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {
    // Service
    GeneratedJavaServiceClass javaServiceClass = new GeneratedJavaServiceClass(generatorParam, tableInfo);
    javaServiceClass.generateFile();

    // ServiceImpl
    GeneratedJavaServiceImplClass javaServiceImplClass = new GeneratedJavaServiceImplClass(generatorParam, tableInfo);
    javaServiceImplClass.generateFile();

    // converter
    GeneratedJavaConverterClass javaConverterClass = new GeneratedJavaConverterClass(generatorParam, tableInfo);
    javaConverterClass.generateFile();

    // exception

    GeneratedJavaExceptionNotExistClass errorNotExistClass = new GeneratedJavaExceptionNotExistClass(generatorParam, tableInfo);
    errorNotExistClass.generateFile();
  }

  protected void generateModel(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {
    // req
    GeneratedJavaReqCreateClass javaCreateReqClass = new GeneratedJavaReqCreateClass(generatorParam, tableInfo);
    javaCreateReqClass.generateFile();
    GeneratedJavaReqUpdateClass javaUpdateReqClass = new GeneratedJavaReqUpdateClass(generatorParam, tableInfo);

    javaUpdateReqClass.generateFile();
    GeneratedJavaReqSearchClass javaSearchReqClass = new GeneratedJavaReqSearchClass(generatorParam, tableInfo);
    javaSearchReqClass.generateFile();
    // resp
    GeneratedJavaRespSingleClass javaSingleRespClass = new GeneratedJavaRespSingleClass(generatorParam, tableInfo);
    javaSingleRespClass.generateFile();
    GeneratedJavaRespPageClass javaPageRespClass = new GeneratedJavaRespPageClass(generatorParam, tableInfo);
    javaPageRespClass.generateFile();
  }

//    private void generateConfig(GeneratorParam generatorParam) throws Exception {
//        // WebConfig
//        GeneratedJavaConfigClass javaConfigClass = new GeneratedJavaConfigClass(generatorParam, null);
//        javaConfigClass.generateFile();
//    }
//
//    private void generateInsertListMapper(GeneratorParam generatorParam, TableInfo tableInfo) throws Exception {
//        GeneratedJavaInsertListMapperClass insertListMapperClass = new GeneratedJavaInsertListMapperClass(generatorParam, tableInfo);
//        insertListMapperClass.generateFile();
//    }
}
