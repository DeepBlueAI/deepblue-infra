package io.deepblueai.infra.generator.ui.service.impl;


import io.deepblueai.infra.generator.api.MybatisGenerator;
import io.deepblueai.infra.generator.api.SpringBootGenerator;
import io.deepblueai.infra.generator.api.SpringMVCGenerator;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.ui.service.ProjectGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生成SpringBoot项目
 *
 * @author lutiehua
 * @date 2018-12-03
 */
@Component("springCloudGatherProjectGenerator")
public class SpringCloudGatherProjectGenerator implements ProjectGenerator {

  @Autowired
  SpringBootGenerator springBootGenerator;

  @Autowired
  MybatisGenerator mybatisGenerator;

  @Autowired
  SpringMVCGenerator springMVCGenerator;

  @Override
  public String generateProject(GeneratorParam generatorParam) throws Exception {
    // 生成项目
    springBootGenerator.generateCode(generatorParam);

    // 生成CRUD
    mybatisGenerator.generateCode(generatorParam);

    // 生成Controller/Service/Model
    springMVCGenerator.generateCode(generatorParam);

    String message = String.format("成功生成项目到\"%s\"目录下", generatorParam.getPackageInfo().getProjectPath());
    return message;
  }
}