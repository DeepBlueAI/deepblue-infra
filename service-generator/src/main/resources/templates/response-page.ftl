package ${packageName};

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import com.github.pagehelper.PageInfo;
import java.util.List;

<#list imports as import>
    import ${import.name};
</#list>

/**
* ${classRemark}
*
* @author ${author}
* @date ${date}
*/
@Getter
@Setter
@ToString
public class ${className} extends PageInfo<${singleRespClassName}>{

public ${className}(List<${singleRespClassName}> list) {
super(list);
}
}