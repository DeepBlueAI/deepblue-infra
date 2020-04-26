package io.deepblueai.infra.generator.core;


import io.deepblueai.infra.generator.consts.DBType;
import io.deepblueai.infra.generator.model.DataModel;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.model.PropModel;

/**
 * 属性文件生成类的抽象基类
 *
 * @author lutiehua
 * @date 2017/11/10
 */
public abstract class AbstractGeneratedYmlFile extends AbstractGeneratedFile {

  protected PropModel model;

  public AbstractGeneratedYmlFile(GeneratorParam generatorParam) {
    super(generatorParam);

    model = new PropModel();

    int port = generatorParam.getProjectInfo().getPort();
    int managePort = port + 10000;

    String serverAddress = generatorParam.getDatabaseInfo().getDbIP();
    Integer serverPort = generatorParam.getDatabaseInfo().getDbPort();
    String dbName = generatorParam.getDatabaseInfo().getDbName();

    String url = String.format("%s:%d/%s", serverAddress, serverPort, dbName);

    model.setDatasourceUrl(url);
    model.setDatasourceUsername(generatorParam.getDatabaseInfo().getDbUsername());
    model.setDatasourcePassword(generatorParam.getDatabaseInfo().getDbPassword());
    model.setManagePort(managePort);
    model.setServiceName(generatorParam.getProjectInfo().getName());
    model.setServicePort(port);
    model.setEntityPackage(generatorParam.getPackageInfo().getBasePackage().concat(".").concat(generatorParam.getPackageInfo().getEntityPackage()));
    model.setDbType(DBType.valueOf(generatorParam.getDatabaseInfo().getDbType()));
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
}
