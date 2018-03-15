package ${package_name}.service.impl;

import ${package_name}.model.${table_name};
import ${package_name}.service.I${table_name}Service;
import ${package_name}.dao.${table_name}DAO;
import ${package_name}.dto.${table_name}DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

/**
* 描述：${table_annotation} 服务实现层
* @author ${author}
* @date ${date}
*/
@Service
public class ${table_name}ServiceImpl implements I${table_name}Service {

    @Autowired
    private ${table_name}DAO ${table_name?uncap_first}DAO;

    @Override
    public ${table_name} findById(String id) {
        ${table_name} ${table_name?uncap_first} = ${table_name?uncap_first}DAO.findById(id);
        return ${table_name?uncap_first};
    }

    @Override
    public void create${table_name}(${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        ${table_name} ${table_name?uncap_first} = new ${table_name}();
        BeanUtils.copyProperties(${table_name?uncap_first},${table_name?uncap_first}DTO);
        ${table_name?uncap_first}DAO.insert( ${table_name?uncap_first} );
    }

    @Override
    public void update${table_name}(${table_name}DTO ${table_name?uncap_first}DTO) throws Exception {
        ${table_name} ${table_name?uncap_first} = new ${table_name}();
        BeanUtils.copyProperties(${table_name?uncap_first},${table_name?uncap_first}DTO);
        ${table_name?uncap_first}DAO.update( ${table_name?uncap_first} );
    }

    @Override
    public void delete${table_name}(String id) {
        ${table_name?uncap_first}DAO.delete( id );
    }

    @Override
    public List<${table_name}> find${table_name}Page(Integer pageNum,Integer pageSize) {
        return ${table_name?uncap_first}DAO.findAndPager( pageNum, pageSize );
    }

}



