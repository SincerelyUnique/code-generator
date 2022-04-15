package com.helper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据库字段封装类
 * Created by Ay on 2017/5/3.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DBColumnDefinition {

    /**
     * 数据库字段名称 id
     **/
    private String columnName;

    /**
     * 数据库字段类型 varchar
     **/
    private String columnType;

    /**
     * 数据库字段类型的长度 varchar
     **/
    private Integer columnLength;

    /**
     * 表示字段类型的精度的总长度，如果为null,表示精度的总长度不固定，最长为Data_Length
     **/
    private Integer columnPrecision;

    /**
     * 表示字段类型的精度范围，如果为0,表示只能存储为整数
     **/
    private Integer columnScale;

    /**
     * 数据库字段首字母小写且去掉下划线字符串
     **/
    private String changeColumnName;

    /**
     * 数据库字段注释
     **/
    private String columnComment;

}
