package ${package_name}.repository.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ${package_name}.model.${table_name};
import com.iflytek.oa.util.OADBUtil;

/**
* 描述：${table_annotation}DAO 层
* @author ${author}
* @date ${date}
*/
public class ${table_name}DAO {

/**
* 描述：${table_annotation}-根据RequestId和表名获取数据
* @author ${author}
* @date ${date}
* @param requestId 表单ID
* @param tableName 表名
* @return 查询数据
*/
public ${table_name} get${table_name}ByIdName( String requestId, String tableName ){
${table_name} ${table_name?uncap_first} = new ${table_name}();
String sql = "${sql}";
OADBUtil db = new OADBUtil();

try {
db.setStatementSql(sql.toString());
db.setInt(1, Integer.valueOf(requestId));
db.executeQuery();

if (db.next()) {
map = new HashMap
<String, String>();

<#if model_column?exists>
    <#list model_column as model>
        map.put("${model.changeColumnName?uncap_first}", db.getString("${model.changeColumnName?uncap_first}"));
    </#list>
</#if>
}
} catch (Exception e) {
e.printStackTrace();
} finally {
if (null != db) {
try {
db.close();
} catch (Exception e) {
e.printStackTrace();
}
}
}
return map;
}
}
