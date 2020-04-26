package io.deepblueai.infra.generator.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 数据库表信息
 *
 * @author lutiehua
 * @date 2017/09/22
 */
@Setter
@Getter
public class DBTable {

  private boolean selected;

  private String name;

  private String type;

  private String remark;
}
