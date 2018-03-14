package ${package_name}.controller;

import ${package_name}.service.I${table_name}Service;
import ${package_name}.model.${table_name};
import ${package_name}.dto.${table_name}DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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
    public ResponseEntity<${table_name}DTO> findById(@PathVariable("id") String id)throws Exception {
        ${table_name}DTO ${table_name?uncap_first}DTO = ${table_name?uncap_first}Service.findDTOById(id);
        ResponseEntity<${table_name}DTO> responseEntity = new ResponseEntity<${table_name}DTO>(${table_name?uncap_first}DTO, HttpStatus.OK);
        return responseEntity;
    }

    /**
    * 描述:创建${table_annotation}
    * @param ${table_name?uncap_first}DTO  ${table_annotation}DTO
    * @return 以mybatis为例，返回的是一个Integer类型的值
    */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Integer> create(@RequestBody ${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        Integer result = ${table_name?uncap_first}Service.create${table_name}(${table_name?uncap_first}DTO);
        ResponseEntity<Integer> responseEntity = new ResponseEntity<Integer>(result, HttpStatus.OK);
        return responseEntity;
    }

    /**
    * 描述：删除${table_annotation}
    * @param id ${table_annotation}id
    */
    @RequestMapping(value = "/{id}/bulk", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void deleteById(@PathVariable("id") String id) throws Exception {
        ${table_name?uncap_first}Service.deleteById(id);
    }

    /**
    * 描述：更新${table_annotation}
    * @param id ${table_annotation}id
    * @return 以mybatis为例，返回的是一个Integer类型的值
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Integer> update${table_name}(@PathVariable("id") String id,@RequestBody ${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        ${table_name?uncap_first}DTO.setId(id);
        Integer result = ${table_name?uncap_first}Service.update${table_name}(${table_name?uncap_first}DTO);
        ResponseEntity<Integer> responseEntity = new ResponseEntity<Integer>(result, HttpStatus.OK);
        return responseEntity;
    }

}