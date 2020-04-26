package io.deepblueai.infra.generator.core;

import com.google.common.base.CaseFormat;
import io.deepblueai.infra.generator.model.DataModel;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.JavaClassModel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 生成Application类
 *
 * @author lutiehua
 * @date 2017/09/28
 */
public class GeneratedJavaAppClass extends AbstractGeneratedFile {

  protected JavaClassModel model;

  private String fileName;

  public GeneratedJavaAppClass(GeneratorParam generatorParam) {
    super(generatorParam);

    model = new JavaClassModel();

    Date now = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String date = dateFormat.format(now);
    String packageName = generatorParam.getPackageInfo().getBasePackage();
    String author = generatorParam.getPackageInfo().getAuthor();
    String projectName = generatorParam.getProjectInfo().getName();
    projectName = CaseFormat.LOWER_HYPHEN.to(CaseFormat.UPPER_CAMEL, projectName.toLowerCase());
    String className = projectName + "Application";
    String classRemark = generatorParam.getProjectInfo().getDescription();
    String mapperPackageName = packageName + "." + generatorParam.getPackageInfo().getDaoPackage();

    model.setBasePackage(generatorParam.getProjectInfo().getGroupId());
    model.setPackageName(packageName);
    model.setAuthor(author);
    model.setDate(date);
    model.setClassName(className);
    model.setClassRemark(classRemark);
    model.setMapperPackage(mapperPackageName);
    model.setServiceType(generatorParam.getProjectInfo().getServiceType());

    String javaPath = generatorParam.getPackageInfo().getJavaPath();
    String packagePath = packageName.replaceAll("\\.", "/");
    fileName = buildActualDir(javaPath, false)
            .concat("/").concat(packagePath)
            .concat("/").concat(className).concat(".java");
  }

  /**
   * 模板
   *
   * @return
   */
  @Override
  public String getTemplateName() {
    return "app.ftl";
  }

  /**
   * 变量数据
   *
   * @return
   */
  @Override
  public DataModel getDataModel() {
    return model;
  }

  /**
   * 文件名称
   *
   * @return
   */
  @Override
  public String getFileName() {
    return fileName;
  }
}
