package ${package_name}.service;

import ${package_name}.model.${table_name};
import ${package_name}.dto.${table_name}DTO;

import java.util.List;

/**
* 描述：${table_annotation} 服务实现层接口
* @author ${author}
* @date ${date}
*/
public interface I${table_name}Service {

    /**
    * 描述：根据Id获取DTO
    * @param id 主键
    * @return 查询的此条记录
    */
    ${table_name} findById(String id);

    /**
    * 描述：创建一条记录
    * @param ${table_name?uncap_first}DTO 前台传递的dto
    */
    void create${table_name}(${table_name}DTO ${table_name?uncap_first}DTO) throws Exception;

    /**
    * 描述：更新一条记录
    * @param ${table_name?uncap_first}DTO 前台传递的dto
    */
    void update${table_name}(${table_name}DTO ${table_name?uncap_first}DTO) throws Exception;

    /**
    * 描述：删除一条记录
    * @param id 主键
    */
    void delete${table_name}(String id);

    /**
    * 描述：查询列表(分页)
    * @param pageNum 当前页号
    * @param pageSize 当前页显示记录条数
    * @return 当前页的数据
    */
    List<${table_name}> find${table_name}Page(Integer pageNum,Integer pageSize);

}
