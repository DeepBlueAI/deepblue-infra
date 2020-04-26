package io.deepblueai.infra.generator;


import io.deepblueai.infra.generator.api.MybatisGenerator;
import io.deepblueai.infra.generator.api.SpringBootGenerator;
import io.deepblueai.infra.generator.api.SpringCloudMVCGenerator;
import io.deepblueai.infra.generator.api.SpringMVCGenerator;
import io.deepblueai.infra.generator.consts.KeyType;
import io.deepblueai.infra.generator.consts.ServiceType;
import io.deepblueai.infra.generator.model.*;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Demo project for Spring Cloud
 *
 * @author waterlu
 * @date 2018-05-24
 */
@RunWith(SpringRunner.class)
public class GeneratorApplicationTests {

  @Test
  public void contextLoads() {

  }

  @Test
  public void testGenerateService() throws Exception {
    GeneratorParam generatorParam = new GeneratorParam();
    generatorParam.setProjectInfo(
            ProjectInfo.builder()
                    .groupId("com.deepblueai.generator.demo")
                    .artifactId("basic-demo")
                    .name("basic-demo")
                    .port(8010)
                    .version("0.0.1-SNAPSHOT")
                    .javaVersion("1.8")
                    .springBootVersion("2.1.8.RELEASE")
                    .springCloudVersion("Greenwich.SR5")
                    .description("Demo project for Spring Boot")
                    .serviceType(ServiceType.BASIC)
                    .build());
    generatorParam.setDatabaseInfo(
            DatabaseInfo.builder()
                    .dbType("MySQL")
                    .dbIP("10.16.33.117")
                    .dbPort(3308)
                    .dbUsername("root")
                    .dbPassword("123456")
                    .dbName("edu_device")
                    .build());
    generatorParam.setPackageInfo(
            PackageInfo.builder()
                    .author("Jason")
                    .projectPath("/Users/jasonlee/files4work/demo-basic")
                    .javaPath("src/main/java")
                    .resourcePath("src/main/resources")
                    .testPath("src/test/java")
                    .basePackage("com.deepblueai.generator.demo")
                    .configPackage("config")
                    .controllerPackage("web")
                    .servicePackage("service")
                    .daoPackage("domain.mapper")
                    .entityPackage("domain.entity")
                    .dtoPackage("dto")
                    .voPackage("vo")
                    .facadePackage("client")
                    .converterPackage("domain.converter")
                    .reqPackage("request")
                    .respPackage("response")
                    .exceptionPackage("exception")
                    .build());

    List<DependencyInfo> dependencies = Lists.newArrayList(
//            DependencyInfo.builder()
//                    .name("waterloo-web")
//                    .groupId("cn.waterlu")
//                    .artifactId("waterloo-starter-web")
//                    .version("1.0.3")
//                    .build(),
//            DependencyInfo.builder()
//                    .name("waterloo-hipster")
//                    .groupId("cn.waterlu")
//                    .artifactId("waterloo-hipster")
//                    .version("1.0.5")
//                    .build(),
            DependencyInfo.builder()
                    .name("swagger2")
                    .groupId("io.springfox")
                    .artifactId("springfox-swagger2")
                    .version("2.9.2")
                    .build(),
            DependencyInfo.builder()
                    .name("swagger-ui")
                    .groupId("io.springfox")
                    .artifactId("springfox-swagger-ui")
                    .version("2.9.2")
                    .build()
    );
    generatorParam.setDependencies(dependencies);

    List<TableInfo> tableList = Lists.newArrayList(
            TableInfo.builder()
                    .name("rack")
                    .remark("实验台")
                    .type("Single")
                    .key("id")
                    .keyType(KeyType.KEY_TYPE_ID)
                    .query(Lists.emptyList())
                    .build()

    );
    generatorParam.setTables(tableList);

    SpringBootGenerator projectGenerator = new SpringBootGenerator();
    MybatisGenerator mybatisGenerator = new MybatisGenerator();
    SpringMVCGenerator springMVCGenerator = ServiceType.BASIC.equals(generatorParam.getProjectInfo().getServiceType()) ? new SpringCloudMVCGenerator() : new SpringMVCGenerator();

    projectGenerator.generateCode(generatorParam);
    mybatisGenerator.generateCode(generatorParam);
    springMVCGenerator.generateCode(generatorParam);

  }
}