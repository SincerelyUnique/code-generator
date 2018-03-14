package ${package_name}.repository.mybatis;

import ${package_name}.dto.${table_name}DTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * 描述：${table_annotation}DAO 层
 * @author ${author}
 * @date ${date}
 */
@Repository
public interface ${table_name}DAO {

    /**
    * 描述：根据id查询
    */
    ${table_name}DTO findDTOById(@Param("id")String id);




}
