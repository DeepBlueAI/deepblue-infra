package ${packageName};

<#list imports as import>
    import ${import.name};
</#list>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;

/**
* ${classRemark}接口
*
* @author ${author}
* @date ${date}
*/

@Api(value = "/${classMapping}", tags= "${classRemark}", description= "${classRemark}API接口")
@RequestMapping(value = "/${classMapping}")
@Slf4j
public class ${className} {

@Resource
${serviceClassName} ${serviceObjectName};

@ApiOperation(value = "多条件搜索${classRemark}", nickname = "search", notes = "分页条件", response = ${pageRespClassName}.class, tags = "${classRemark}")
@ApiResponses(value = {
@ApiResponse(code = 200, message = "OK", response = ${pageRespClassName}.class)})
@GetMapping
public ${pageRespClassName} search(${searchReqClassName} ${searchReqObjectName}) throws InvocationTargetException, IllegalAccessException  {
return ${serviceObjectName}.search(${searchReqObjectName});
}

@ApiOperation(value = "通过ID获取${classRemark}信息", nickname = "getById", notes = "返回${classRemark}详细信息", response = ${singleRespClassName}.class, tags = "${classRemark}")
@ApiResponses(value = {
@ApiResponse(code = 200, message = "OK", response = ${singleRespClassName}.class)})
@GetMapping("{id}")
public ${singleRespClassName} getById(@PathVariable Long id) {
return ${serviceObjectName}.getById(id);
}

@ApiOperation(value = "通过ID删除${classRemark}", nickname = "deleteById", notes = "删除${classRemark}", tags = "${classRemark}")
@ApiResponses(value = {
@ApiResponse(code = 200, message = "OK")})
@DeleteMapping("{id}")
public void deleteById(@ApiParam(value = "id", required = true) @PathVariable Long id) {
${serviceObjectName}.deleteById(id);
}

@ApiOperation(value = "更新${classRemark}信息", nickname = "updateById", notes = "", tags = "${classRemark}")
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
@PutMapping("{id}")
public void updateById(
@NotNull @ApiParam(value = "要更新的${classRemark}ID", required = true) @Valid Long id,
@ApiParam(value = "更新的${classRemark}", required = true) @Valid @RequestBody ${updateReqClassName} ${updateReqObjectName}) {
${serviceObjectName}.updateById(id, ${updateReqObjectName});
}

@ApiOperation(value = "创建${classRemark}", nickname = "create", notes = "创建${classRemark}", tags = "${classRemark}")
@ApiResponses(value = {
@ApiResponse(code = 200, message = "OK")
})
@PostMapping
public void create(@ApiParam(value = "${classRemark}", required = true) @Valid @RequestBody ${createReqClassName} ${createReqObjectName}) {
${serviceObjectName}.create(${createReqObjectName});
}

}
