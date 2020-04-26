package io.deepblueai.infra.generator.model;

import io.deepblueai.infra.generator.consts.DBType;
import io.deepblueai.infra.generator.consts.ServiceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * application.properties信息
 *
 * @author lutiehua
 * @date 2017/11/10
 */
@Getter
@Setter
@NoArgsConstructor
public class PropModel extends DataModel {

  /**
   * 服务名称 spring.application.name
   */
  private String serviceName;

  /**
   * 服务端口 server.port
   */
  private int servicePort;

  /**
   * 监控端口
   */
  private int managePort;

  /**
   * spring.datasource.url
   */
  private String datasourceUrl;

  /**
   * spring.datasource.username
   */
  private String datasourceUsername;

  /**
   * spring.datasource.password
   */
  private String datasourcePassword;

  /**
   * 实体类包名 mybatis.type-aliases-package
   */
  private String entityPackage;

  /**
   * 项目类型
   */
  private ServiceType serviceType;

  private DBType dbType;

  private String baseMapperClass = "io.deepblueai.base.mapper.CommonMapper";

}
