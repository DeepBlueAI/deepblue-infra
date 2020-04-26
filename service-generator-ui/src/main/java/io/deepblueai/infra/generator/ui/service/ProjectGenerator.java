package io.deepblueai.infra.generator.ui.service;


import io.deepblueai.infra.generator.model.GeneratorParam;

/**
 * 工程生成接口
 *
 * @author lu
 * @date 2018/5/23
 */
public interface ProjectGenerator {

  /**
   * 生成工程
   *
   * @param generatorParam 参数
   * @return
   * @throws Exception
   */
  String generateProject(GeneratorParam generatorParam) throws Exception;

}
