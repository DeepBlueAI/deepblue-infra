package io.deepblueai.infra.generator.core;

import com.google.common.base.CaseFormat;
import io.deepblueai.infra.generator.consts.KeyType;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.TableInfo;

/**
 * 生成Controller类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaControllerClass extends AbstractGeneratedJavaTemplateClass {

  public GeneratedJavaControllerClass(GeneratorParam generatorParam, TableInfo tableInfo) {
    super(generatorParam, tableInfo, false);

    String keyFieldName = tableInfo.getKey();
    if (null != keyFieldName) {
      keyFieldName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, keyFieldName);
      model.setKeyFieldName(keyFieldName);
    }
  }

  /**
   * 包名
   *
   * @return
   */
  @Override
  public String getJavaPackageName() {
    return generatorParam.getPackageInfo().getControllerPackage();
  }

  /**
   * 类名后缀
   *
   * @return
   */
  @Override
  public String getJavaClassNamePostfix() {
    return "Controller";
  }

  /**
   * 类说明后缀
   *
   * @return
   */
  @Override
  public String getJavaClassRemarkPostfix() {
    return "";
  }

  /**
   * 模板
   *
   * @return
   */
  @Override
  public String getTemplateName() {
    if (tableInfo.getKeyType().equalsIgnoreCase(KeyType.KEY_TYPE_ID)) {
      return "controller-id.ftl";
    } else if (tableInfo.getKeyType().equalsIgnoreCase(KeyType.KEY_TYPE_UUID)) {
      return "controller-uuid.ftl";
    } else {
      // 默认
      return "controller-id.ftl";
    }
  }
}
