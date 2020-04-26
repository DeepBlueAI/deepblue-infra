package io.deepblueai.infra.generator.core;

import com.google.common.base.CaseFormat;
import io.deepblueai.infra.generator.model.DataModel;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.JavaBaseClassModel;
import io.deepblueai.infra.generator.model.TableInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * 生成Service接口类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaExceptionBaseClass extends AbstractGeneratedFile {

  /**
   * 生成文件名
   */
  protected String fileName;
  private JavaBaseClassModel model = new JavaBaseClassModel();

  public GeneratedJavaExceptionBaseClass(GeneratorParam generatorParam) {
    super(generatorParam);

    // 日期
    Date now = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = dateFormat.format(now);
    model.setDate(date);

    // 作者
    String author = generatorParam.getPackageInfo().getAuthor();
    model.setAuthor(author);

    // 包名
    String basePackageName = generatorParam.getPackageInfo().getBasePackage();
    String packageName = basePackageName + "." + generatorParam.getPackageInfo().getExceptionPackage();
    model.setPackageName(packageName);
    String projectName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, generatorParam.getProjectInfo().getName().toLowerCase());
    // 类名
    String className = projectName.concat("Exception");
    model.setClassName(className);
    model.setTables(generatorParam.getTables().stream().map(TableInfo::getName).collect(Collectors.toList()));
    // 对象名
    String objectName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, className);
    model.setObjectName(objectName);

    String javaPath = generatorParam.getPackageInfo().getJavaPath();
    String packagePath = packageName.replaceAll("\\.", "/");
    fileName = buildActualDir(javaPath, true)
            .concat("/").concat(packagePath)
            .concat("/").concat(className).concat(".java");

  }

  /**
   * 模板
   *
   * @return
   */
  @Override
  public String getTemplateName() throws Exception {
    return "exception-base.ftl";
  }

  @Override
  public DataModel getDataModel() {
    return model;
  }

  @Override
  public String getFileName() {
    return fileName;
  }
}
