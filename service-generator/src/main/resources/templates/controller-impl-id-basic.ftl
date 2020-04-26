package ${packageName};

<#list imports as import>
    import ${import.name};
</#list>
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* ${classRemark}接口
*
* @author ${author}
* @date ${date}
*/
@RestController
@Slf4j
public class ${className} implements ${facadeClassName} {

@Resource
${serviceClassName} ${serviceObjectName};


@Override
public void create(@ApiParam(value = "${classRemark}创建请求", required = true) @Valid @RequestBody ${createReqClassName} ${createReqObjectName}) {
${serviceObjectName}.create(${createReqObjectName});
}

@Override
public void deleteById(@ApiParam(value = "要删除的${classRemark}ID", required = true) @PathVariable Long id) {
${serviceObjectName}.deleteById(id);
}

@Override
public ${singleRespClassName} getById(@ApiParam(value = "${classRemark}ID", required = true) @PathVariable Long id) {
return ${serviceObjectName}.getById(id);
}

@Override
public ${pageRespClassName} search(${searchReqClassName} ${searchReqObjectName})throws InvocationTargetException, IllegalAccessException {
return ${serviceObjectName}.search(${searchReqObjectName});
}

@Override
public void updateById(
@NotNull @ApiParam(value = "要更新的${classRemark}ID", required = true) @Valid @PathVariable("id") Long id,
@ApiParam(value = "更新的${classRemark}", required = true) @Valid @RequestBody ${updateReqClassName} ${updateReqObjectName}) {
${serviceObjectName}.updateById(id, ${updateReqObjectName});
}

}
