package io.deepblueai.infra.generator.model;

import io.deepblueai.infra.generator.consts.ServiceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * POM文件数据模型
 *
 * @author lutiehua
 * @date 2017/11/10
 */
@Getter
@Setter
@NoArgsConstructor
public class PomModel extends DataModel {

//    private String groupId;
//
//    private String artifactId;
//
//    private String version;
//
//    private String name;
//
//    private String description;
//
//    private String springBootVersion;
//
//    private String javaVersion;

  private ProjectInfo projectInfo;

  private List<DependencyInfo> dependencies;

  /**
   * 项目类型
   */
  private ServiceType serviceType;

}
