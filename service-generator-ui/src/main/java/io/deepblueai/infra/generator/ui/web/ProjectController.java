package io.deepblueai.infra.generator.ui.web;


import io.deepblueai.infra.generator.ui.exception.ResponseCode;
import io.deepblueai.infra.generator.ui.service.ProjectGenerator;
import io.deepblueai.infra.generator.consts.ServiceType;
import io.deepblueai.infra.generator.model.GeneratorParam;
import io.deepblueai.infra.generator.ui.util.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 项目生成接口
 *
 * @author lutiehua
 * @date 2017/9/22
 */
@RestController
@RequestMapping("/api/project")
@Slf4j
public class ProjectController extends BaseController {

  @Autowired
  @Qualifier("springCloudBasicProjectGenerator")
  private ProjectGenerator springCloudBasicProjectGenerator;

  @Autowired
  @Qualifier("springCloudGatherProjectGenerator")
  private ProjectGenerator springCloudGatherProjectGenerator;

  @Value("${generator.basic-file-path}")
  private String BASIC_FILE_PATH;

  @RequestMapping(value = "/save", method = RequestMethod.POST)
  public void generate(@RequestBody @Validated GeneratorParam generatorParam, HttpServletResponse response) throws Exception {
    if (generatorParam.getTables().size() == 0) {
      int errorCode = ResponseCode.PARAM_ERROR.code;
      String errorMessage = "待生成列表不能为空，请添加数据库表到待生成列表!";
      log.error(errorMessage);
    }
    ServiceType projectType = generatorParam.getProjectInfo().getServiceType();
    String baseFileDir = BASIC_FILE_PATH.concat(generatorParam.getProjectInfo().getArtifactId());
    generatorParam.getPackageInfo().setProjectPath(baseFileDir);
    if (ServiceType.BASIC.equals(projectType)) {
      // 生成SpringBoot项目
      String message = springCloudBasicProjectGenerator.generateProject(generatorParam);
      log.info(message);
    } else if (ServiceType.GATHER.equals(projectType)) {
      // 生成聚合服务项目
      String message = springCloudGatherProjectGenerator.generateProject(generatorParam);
      log.info(message);
    }
    File zipFile = new File(baseFileDir.concat(".zip"));
    FileOutputStream fos1 = new FileOutputStream(zipFile);
    ZipUtils.toZip(baseFileDir, fos1, true);
    downloadLog(zipFile.getAbsolutePath(), response);
  }


  public void downloadLog(String zipFileName, HttpServletResponse response) throws IOException {
    response.setCharacterEncoding("UTF-8");
    BufferedInputStream fis = new BufferedInputStream(new FileInputStream(zipFileName));
    byte[] buffer = new byte[fis.available()];
    fis.read(buffer);
    fis.close();
    response.reset();
    OutputStream outStream = new BufferedOutputStream(response.getOutputStream());
    response.setContentType("application/octet-stream");
    response.setHeader("Content-Disposition", "attachment;filename=" + new String(zipFileName.getBytes("UTF-8"), "UTF-8"));
    outStream.write(buffer);
    outStream.flush();
    outStream.close();
  }

}