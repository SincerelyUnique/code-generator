package ${package_name}.model;

import java.util.Date;
import java.math.BigDecimal;

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
    <#if (model.columnType = 'VARCHAR2' || model.columnType = 'TEXT')>
    private String ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnType = 'TIMESTAMP' >
    private Date ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'NUMBER' && model.columnLength > 18 && model.columnScale >0 && model.columnPrecision != 10000) >
    private BigDecimal ${model.changeColumnName?uncap_first};

    </#if>
    <#if (model.columnType = 'NUMBER' && model.columnLength = 22 && model.columnPrecision = 10000 && model.columnScale = 0) >
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnType = 'INT' >
    private Integer ${model.changeColumnName?uncap_first};

    </#if>
    <#if model.columnType = 'CHAR' >
    private char ${model.changeColumnName?uncap_first};

    </#if>
    </#list>
    </#if>
    <#if model_column?exists>
    <#list model_column as model>
    <#if (model.columnType = 'VARCHAR2' || model.columnType = 'TEXT')>
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
    <#if (model.columnType = 'NUMBER' && model.columnLength = 22 && model.columnPrecision = 10000 && model.columnScale = 0) >
    public Integer get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(Integer ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

    </#if>
    <#if (model.columnType = 'NUMBER' && model.columnLength > 18 && model.columnScale >0 && model.columnPrecision != 10000) >
    public BigDecimal get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(BigDecimal ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

    </#if>
    <#if model.columnType = 'CHAR' >
    public char get${model.changeColumnName}() {
        return this.${model.changeColumnName?uncap_first};
    }

    public void set${model.changeColumnName}(char ${model.changeColumnName?uncap_first}) {
        this.${model.changeColumnName?uncap_first} = ${model.changeColumnName?uncap_first};
    }

    </#if>
</#list>
</#if>

}
