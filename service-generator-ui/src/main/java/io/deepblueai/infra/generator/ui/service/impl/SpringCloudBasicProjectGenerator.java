package io.deepblueai.infra.generator.ui.service.impl;

import io.deepblueai.infra.generator.api.SpringCloudMVCGenerator;
import io.deepblueai.infra.generator.ui.service.ProjectGenerator;
import io.deepblueai.infra.generator.api.MybatisGenerator;
import io.deepblueai.infra.generator.api.SpringBootGenerator;
import io.deepblueai.infra.generator.api.SpringMVCGenerator;
import io.deepblueai.infra.generator.model.GeneratorParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生成SpringBoot项目
 *
 * @author lutiehua
 * @date 2017/11/10
 */
@Component("springCloudBasicProjectGenerator")
public class SpringCloudBasicProjectGenerator implements ProjectGenerator {

    @Autowired
    private SpringBootGenerator projectGenerator;

    @Autowired
    private MybatisGenerator mybatisGenerator;

    @Autowired
    private SpringCloudMVCGenerator springCloudMVCGenerator;

    @Override
    public String generateProject(GeneratorParam generatorParam) throws Exception {
        // 生成项目
        projectGenerator.generateCode(generatorParam);

        // 生成CRUD
        mybatisGenerator.generateCode(generatorParam);

        // 生成Controller/Service/Model
        springCloudMVCGenerator.generateCode(generatorParam);

        String message = String.format("成功生成项目到\"%s\"目录下", generatorParam.getPackageInfo().getProjectPath());
        return message;
    }
}