package io.deepblueai.infra.generator.model;

import io.deepblueai.infra.generator.consts.ServiceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Java类数据模型
 *
 * @author lutiehua
 * @date 2017/09/26
 */
@Getter
@Setter
@NoArgsConstructor
public class JavaBaseClassModel extends DataModel {

  /**
   * 包名
   */
  protected String packageName;

  /**
   * 类名
   */
  protected String className;

  /**
   * 对象名
   */
  protected String objectName;

  /**
   * 表名集合
   */
  protected List<String> tables;

  /**
   * 项目类型
   */
  protected ServiceType serviceType = ServiceType.BASIC;


}
