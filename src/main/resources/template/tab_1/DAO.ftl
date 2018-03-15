package ${package_name}.repository.mybatis;

import ${package_name}.model.${table_name};

import java.util.List;
import org.springframework.stereotype.Repository;


/**
* 描述：${table_annotation}DAO 层
* @author ${author}
* @date ${date}
*/
@Repository
public interface ${table_name}DAO {

    /**
    * 描述：插入单条记录
    * @param ${table_name?uncap_first} 实体类
    * @return 插入的数据实体
    */
    void insert(${table_name} ${table_name?uncap_first});

    /**
    * 描述：根据id查询单条记录
    * @param id 主键
    * @return 返回单条记录
    */
    ${table_name} findById(String id);

    /**
    * 描述：查询所有记录
    * @return 返回所有记录
    */
    List<${table_name}> findAll();

    /**
    * 描述：更新单条记录
    * @param ${table_name?uncap_first} 实体类
    * @return 更新的数据实体
    */
    void update(${table_name} ${table_name?uncap_first});

    /**
    * 描述：根据id删除
    * @param id 主键
    * @return 删除成功返回1，失败返回0
    */
    void delete(String id);

    /**
    * 描述：分页查询
    * @param pageNum 当前页号
    * @param pageSize 当前页显示多少条数据
    * @return 数据集
    */
    List<${table_name}> findAndPager(Integer pageNum,Integer pageSize);
}
