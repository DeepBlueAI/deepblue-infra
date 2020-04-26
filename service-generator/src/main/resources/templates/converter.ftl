package ${packageName};

<#list imports as import>
    import ${import.name};
</#list>

import org.mapstruct.Mapper;
import java.util.List;
import com.github.pagehelper.PageInfo;

/**
* ${classRemark}接口
*
* @author ${author}
* @date ${date}
*/

@Mapper(componentModel = "spring")
public interface ${className} {

${modelClassName} convert2entity(${createReqClassName} req);

${modelClassName} convert2entity(${updateReqClassName} req);

${singleRespClassName} convert2response(${modelClassName} entity);

${pageRespClassName} convert2pageResponse(PageInfo<${modelClassName}> teacherPageInfo);

}
