package io.deepblueai.infra.generator.model;

import com.alibaba.fastjson.annotation.JSONField;
import io.deepblueai.infra.generator.consts.ServiceType;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 项目信息
 *
 * @author lutiehua
 * @date 2017/09/26
 */
@Data
@Builder
public class ProjectInfo {

  @NotBlank
  private String groupId;

  @NotBlank
  private String artifactId;

  /**
   * 服务端口
   */
  @NotNull
  @JSONField(serialize = false)
  private Integer port;

  @JSONField(serialize = false)
  private String version;

  private String name;

  @JSONField(serialize = false)
  private String description;

  /**
   * JDK 版本
   */
  @JSONField(serialize = false)
  private String javaVersion;

  /**
   * Spring Boot 版本
   */
  @JSONField(serialize = false)
  private String springBootVersion;

  /**
   * Spring Cloud 版本
   */
  @JSONField(serialize = false)
  private String springCloudVersion;

  /**
   * 项目类型
   */
  private ServiceType serviceType;

  @Tolerate
  public ProjectInfo() {
  }
}
