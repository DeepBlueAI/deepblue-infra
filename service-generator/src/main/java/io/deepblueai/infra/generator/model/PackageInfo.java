package io.deepblueai.infra.generator.model;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import javax.validation.constraints.NotBlank;

/**
 * 程序包信息
 *
 * @author lutiehua
 * @date 2017/09/25
 */
@Data
@Builder
public class PackageInfo {

  /**
   * 项目路径
   */
  @NotBlank
  private String projectPath;

  /**
   * 基础包名
   */
  @NotBlank
  private String basePackage;

  /**
   * config包名
   */
  private String configPackage;

  /**
   * controller包名
   */
  private String controllerPackage;

  /**
   * facade包名
   */
  private String facadePackage;

  /**
   * service包名
   */
  private String servicePackage;

  /**
   * dao包名
   */
  private String daoPackage;

  /**
   * entity包名
   */
  private String entityPackage;

  /**
   * dto包名
   */
  private String dtoPackage;

  /**
   * vo包名
   */
  private String voPackage;

  /**
   * 请求包名
   */
  private String reqPackage = "request";

  /**
   * 响应包名
   */
  private String respPackage = "resp";

  /**
   * ErrorCode包名
   */
  private String exceptionPackage = "exception";

  /**
   * 转换器包名
   */
  private String converterPackage = "converter";

  /**
   * 作者
   */
  @NotBlank
  private String author;

  /**
   * JAVA源文件路径
   */
  private String javaPath = "src/main/java";

  /**
   * 资源文件路径
   */
  private String resourcePath = "src/main/resources";

  /**
   * 测试类路径
   */
  private String testPath;

  @Tolerate
  public PackageInfo() {
  }
}
