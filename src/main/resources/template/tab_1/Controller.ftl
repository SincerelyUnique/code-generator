package ${package_name}.controller;

import ${package_name}.service.I${table_name}Service;
import ${package_name}.model.${table_name};
import ${package_name}.dto.${table_name}DTO;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

/**
* 描述：${table_annotation}控制层
* @author ${author}
* @date ${date}
*/
@RestController
public class ${table_name}Controller {

@Autowired
private I${table_name}Service ${table_name?uncap_first}Service;

/**
* 描述：根据Id 查询
* @param id  ${table_annotation}id
*/
@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public ResponseEntity<${table_name}> findById(@PathVariable("id") String id)throws Exception {
${table_name} ${table_name?uncap_first} = ${table_name?uncap_first}Service.findById(id);
ResponseEntity<${table_name}> responseEntity = new ResponseEntity<${table_name}>(${table_name?uncap_first}, HttpStatus.OK);
return responseEntity;
}

/**
* 描述:创建${table_annotation}
* @param ${table_name?uncap_first}DTO  ${table_annotation}DTO
* @return 以mybatis为例，返回的是一个Integer类型的值
*/
@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public void create(@RequestBody ${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
${table_name?uncap_first}Service.create${table_name}(${table_name?uncap_first}DTO);
}

/**
* 描述：删除${table_annotation}
* @param id ${table_annotation}id
*/
@RequestMapping(value = "/{id}/bulk", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public void deleteById(@PathVariable("id") String id) throws Exception {
${table_name?uncap_first}Service.delete${table_name}(id);
}

/**
* 描述：更新${table_annotation}
* @param id ${table_annotation}id
* @return 以mybatis为例，返回的是一个Integer类型的值
*/
@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public void update${table_name}(@PathVariable("id") String id,@RequestBody ${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
${table_name?uncap_first}Service.update${table_name}(${table_name?uncap_first}DTO);
}

@RequestMapping(value = "/findAndPageUsers", method = RequestMethod.GET)
public ResponseEntity
<PageInfo<${table_name}>> findAndPageUsers(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
@RequestParam(required = false, defaultValue = "10") Integer pageSize) throws Exception {
PageHelper.startPage(pageNum,pageSize);
List<${table_name}> list = ${table_name?uncap_first}Service.find${table_name}Page(pageNum,pageSize);
PageInfo<${table_name}> pageInfo = new PageInfo<>(list);
ResponseEntity
<PageInfo<${table_name}>> responseEntity = new ResponseEntity
<PageInfo<${table_name}>>(pageInfo, HttpStatus.OK);
return responseEntity;
}

}