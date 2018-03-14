package com.helper.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * <code>GeneratorPageInfo</code>
 * </p>
 * Description:
 *
 * @author Mcchu
 * @date 2018/3/6 10:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneratorPageInfo {

    // 数据库类型
    private String dbType;

    // 数据库ip
    private String ip;

    // 数据库端口
    private String port;

    // 数据库名字
    private String dbName;

    // 数据库用户名
    private String dbUserName;

    // 数据库密码
    private String dbPassword;

    // 表名
    private String tableName;

    // 注释：作者名
    private String authorName;

    // 注释：表注释
    private String tableAnnotation;

    // 包名
    private String packageName;

    // 存盘路径
    private String diskPath;

    // 数据库语句
    private String sql;
}
