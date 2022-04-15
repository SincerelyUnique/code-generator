package ${package_name}.model;

import java.util.Date;

/**
* 描述：${table_annotation}模型
* @author ${author}
* @date ${date}
* @table ${table_name_small}
*/
public class ${table_name} {

<#if model_column?exists>
    <#list model_column as model>
        /**
        *${model.columnComment!''}
        */
        <#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
            private String ${model.changeColumnName?uncap_first};

        </#if>
        <#if model.columnType = 'TIMESTAMP' >
            private Date ${model.changeColumnName?uncap_first};

        </#if>
        <#if model.columnType = 'NUMERIC' >
            private Float ${model.changeColumnName?uncap_first};

        </#if>
        <#if model.columnType = 'INT' >
            private Integer ${model.changeColumnName?uncap_first};

        </#if>
    </#list>
</#if>
<#if model_column?exists>
    <#list model_column as model>
        <#if (model.columnType = 'VARCHAR' || model.columnType = 'TEXT')>
            public String get${model.changeColumnName}() {
            return this.${model.changeColumnName?uncap_first};
            }

            public void set${model.changeColumnName}(String ${model.changeColumnName?uncap_first}) {
            this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
            }

        </#if>
        <#if model.columnType = 'TIMESTAMP' >
            public Date get${model.changeColumnName}() {
            return this.${model.changeColumnName?uncap_first};
            }

            public void set${model.changeColumnName}(Date ${model.changeColumnName?uncap_first}) {
            this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
            }

        </#if>
        <#if model.columnType = 'NUMERIC' >
            public Float get${model.changeColumnName}() {
            return this.${model.changeColumnName?uncap_first};
            }

            public void set${model.changeColumnName}(Float ${model.changeColumnName?uncap_first}) {
            this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
            }

        </#if>
        <#if model.columnType = 'INT' >
            public Integer get${model.changeColumnName}() {
            return this.${model.changeColumnName?uncap_first};
            }

            public void set${model.changeColumnName}(Integer ${model.changeColumnName?uncap_first}) {
            this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
            }

        </#if>
    </#list>
</#if>

}
