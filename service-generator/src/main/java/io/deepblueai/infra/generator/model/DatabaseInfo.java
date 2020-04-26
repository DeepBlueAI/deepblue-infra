package io.deepblueai.infra.generator.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 数据库连接信息，目前只支持MySQL
 *
 * @author lutiehua
 * @date 2017/09/22
 */
@Data
@Builder
public class DatabaseInfo {

  private String dbType;

  /**
   * MysQL地址
   */
  @NotBlank
  private String dbIP;

  /**
   * MysQL端口
   */
  @NotNull
  private Integer dbPort;

  /**
   * 数据库名称
   */
  @NotBlank
  private String dbName;

  /**
   * MysQL用户名
   */
  @NotBlank
  private String dbUsername;

  /**
   * MysQL密码
   */
  @NotBlank
  private String dbPassword;

  @Tolerate
  public DatabaseInfo() {
  }
}