package ${packageName};

<#list imports as import>
    import ${import.name};
</#list>
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
* ${classRemark}接口
*
* @author ${author}
* @date ${date}
*/

@Api(value = "/${classMapping}", tags= "${classRemark}", description= "${classRemark}API接口")
@FeignClient(url = "${r'${feign.url.'}${serviceName}${r'}'}", name = "${objectName}")
@RequestMapping(value = "/${classMapping}")
public interface ${className} {

@ApiOperation(value = "创建${classRemark}", nickname = "create", notes = "创建${classRemark}", tags = "${classRemark}")
@ApiResponses(value = {
@ApiResponse(code = 200, message = "OK")})
@PostMapping
void create(@ApiParam(value = "${classRemark}", required = true) @Valid @RequestBody ${createReqClassName} ${createReqObjectName});


@ApiOperation(value = "通过ID删除${classRemark}", nickname = "deleteById", notes = "删除${classRemark}", tags = "${classRemark}")
@ApiResponses(value = {
@ApiResponse(code = 200, message = "OK")})
@DeleteMapping("{id}")
void deleteById(@ApiParam(value = "要删除的${classRemark}ID", required = true) @PathVariable("id") Long id);

@ApiOperation(value = "通过ID获取${classRemark}信息", nickname = "getById", notes = "返回${classRemark}详细信息", response = ${singleRespClassName}.class, tags = "${classRemark}")
@ApiResponses(value = {
@ApiResponse(code = 200, message = "OK", response = ${singleRespClassName}.class)})
@GetMapping("{id}")
${singleRespClassName} getById(@ApiParam(value = "学校ID", required = true) @PathVariable("id") Long id);

@ApiOperation(value = "多条件搜索${classRemark}", nickname = "search", notes = "分页条件", response = ${pageRespClassName}.class, tags = "${classRemark}")
@ApiResponses(value = {
@ApiResponse(code = 200, message = "OK", response = ${pageRespClassName}.class)})
@GetMapping
${pageRespClassName} search(${searchReqClassName} ${searchReqObjectName}) throws InvocationTargetException, IllegalAccessException ;


@ApiOperation(value = "更新${classRemark}信息", nickname = "updateById", notes = "", tags = "${classRemark}")
@ApiResponses(value = {@ApiResponse(code = 200, message = "OK")})
@PutMapping("{id}")
void updateById(
@NotNull @ApiParam(value = "要更新的${classRemark}ID", required = true) @Valid @PathVariable("id") Long id,
@ApiParam(value = "更新的${classRemark}", required = true) @Valid @RequestBody ${updateReqClassName} ${updateReqObjectName});

}