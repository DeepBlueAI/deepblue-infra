package ${packageName};


<#list imports as import>
    import ${import.name};
</#list>
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.lang.reflect.InvocationTargetException;
import java.beans.PropertyDescriptor;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Slf4j
@Service
public class ${className} implements ${serviceClassName} {


@Resource
${mapperClassName} ${mapperObjectName};

@Resource
${converterClassName} ${converterObjectName};

@Override
public ${pageRespClassName} search(${searchReqClassName} ${searchReqObjectName})throws InvocationTargetException, IllegalAccessException {
PageHelper.startPage(${searchReqObjectName}.getPageNum(), ${searchReqObjectName}.getPageSize(),"id desc");
Example exe = new Example(${modelClassName}.class);
Criteria c = exe.createCriteria();
Map
<String, Object> valueMappings = Maps.newHashMap();
List
<PropertyDescriptor> pds = Lists.newArrayList(BeanUtils.getPropertyDescriptors(${searchReqObjectName}.getClass()));
    for (PropertyDescriptor pd: pds) {
    Object o = pd.getReadMethod().invoke(${searchReqObjectName});
    valueMappings.put(pd.getName(), o);
    }
    valueMappings.forEach((k,v) -> {
    if(Objects.nonNull(v)) {
    c.andEqualTo(k, v);
    }
    });
    return ${converterObjectName}.convert2pageResponse(new PageInfo<>(${mapperObjectName}.selectByExample(exe)));
    }

    @Override
    public void create(${createReqClassName} ${createReqObjectName}) {
    ${mapperObjectName}.insertSelective(${converterObjectName}.convert2entity(${createReqObjectName}));
    }


    @Override
    public void updateById(Long id, ${updateReqClassName} ${updateReqObjectName}) {
    boolean exist = ${mapperObjectName}.existsWithPrimaryKey(id);
    if (!exist) {
    throw new ${notExistExceptionClassName}();
    }
    ${modelClassName} entity = ${converterObjectName}.convert2entity(${updateReqObjectName});
    entity.setId(id);
    ${mapperObjectName}.updateByPrimaryKeySelective(entity);
    }

    @Override
    public void deleteById(Long id) {
    boolean exist = ${mapperObjectName}.existsWithPrimaryKey(id);
    if (!exist) {
    throw new ${notExistExceptionClassName}();
    }
    ${mapperObjectName}.deleteByPrimaryKey(id);
    }

    @Override
    public ${singleRespClassName} getById(Long id) {
    ${modelClassName} entity = ${mapperObjectName}.selectByPrimaryKey(id);
    if (Objects.isNull(entity)) {
    throw new ${notExistExceptionClassName}();
    }
    return ${converterObjectName}.convert2response(entity);
    }

    }