package ${packageName};


<#list imports as import>
    import ${import.name};
</#list>
import java.lang.reflect.InvocationTargetException;

/**
* ${classRemark}
*
* @author ${author}
* @date ${date}
*/
public interface ${className} {

${pageRespClassName} search(${searchReqClassName} ${searchReqObjectName})throws InvocationTargetException, IllegalAccessException;

void create(${createReqClassName} ${createReqObjectName});

void updateById(Long id, ${updateReqClassName} ${updateReqObjectName});

void deleteById(Long id);

${singleRespClassName} getById(Long id);

}
