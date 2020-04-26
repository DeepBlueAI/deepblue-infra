package ${packageName};

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
public class ${className} {

<#list fields as field>
    /**
    * ${field.remark}
    *
    */
    <#list field.annotations as annotation>
        @${annotation.show}
    </#list>
    <#if field.value?exists>
        private ${field.type} ${field.name} = ${field.value};
    <#else>
        private ${field.type} ${field.name};
    </#if>

</#list>
}