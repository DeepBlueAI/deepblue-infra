package ${packageName};

import io.deepblueai.base.exception.AbstractBaseException;

public class ${className} extends AbstractBaseException {

public ${className}(${className}.ExceptionEnum exp) {
super(exp.getCode(), exp.getStatus());
}


enum ExceptionEnum {
<#list tables as table>
    ${table?upper_case}_NOT_EXIST(404, "${table?upper_case}_NOT_EXIST"),
</#list>
;

private String code;
private int status;

ExceptionEnum(int status, String code) {
this.code = code;
this.status = status;
}

public String getCode() {
return code;
}

public int getStatus() {
return status;
}
}
}
