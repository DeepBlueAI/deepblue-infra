package io.deepblueai.infra.generator.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.validation.Valid;
import java.util.List;

/**
 * 生成参数
 *
 * @author lutiehua
 * @date 2017/11/09
 */
@Data
public class GeneratorParam {

  /**
   * 工程相关信息
   */
  @Valid
  private ProjectInfo projectInfo;

  /**
   * 数据库相关信息
   */
  @Valid
  private DatabaseInfo databaseInfo;

  /**
   * 程序包相关信息
   */
  @Valid
  private PackageInfo packageInfo;

  /**
   * 项目依赖信息
   */
  @Valid
  @JSONField(serialize = false)
  private List<DependencyInfo> dependencies;

  /**
   * 数据库表信息
   */
  @Valid
  private List<TableInfo> tables;

}
