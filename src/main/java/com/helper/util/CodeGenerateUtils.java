package com.helper.util;

import com.helper.model.DBColumnDefinition;
import com.helper.model.GeneratorCheckBoxLabel;
import com.helper.model.GeneratorPageInfo;
import freemarker.template.Template;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 描述：代码生成器
 * Created by Ay on 2017/5/1.
 */
public class CodeGenerateUtils {

    /**
     * 数据库基本信息
     */
    private String dbType;
    private String dbIp;
    private String dbPort;
    private String dbName;
    private String dbUserName;
    private String dbPassword;
    private String tableName;

    /**
     * 注释信息
     */
    private String authorName;
    private String currentDate = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    private String tableAnnotation;

    /**
     * 生成包名
     */
    private String packageName;

    /**
     * 生成路径
     */
    private String diskPath = "D://";

    /**
     * 数据库名转为类名
     */
    private String changeTableName;

    public CodeGenerateUtils(GeneratorPageInfo info) {
        this.dbType = info.getDbType();
        this.dbIp = info.getIp();
        this.dbPort = info.getPort();
        this.dbName = info.getDbName();
        this.dbUserName = info.getDbUserName();
        this.dbPassword = info.getDbPassword();
        this.tableName = info.getTableName();
        this.authorName = info.getAuthorName();
        this.tableAnnotation = info.getTableAnnotation();
        this.packageName = info.getPackageName();
        this.diskPath = info.getDiskPath();
        this.changeTableName = DBUtils.replaceUnderLineAndUpperCase(info.getTableName());
    }

    /**
     * 描述：代码生成器的核心方法
     */
    public void generate(GeneratorCheckBoxLabel label) throws Exception {
        Connection connection = DBUtils.getConnection(dbType, dbIp, dbPort, dbName, dbUserName, dbPassword);
        if (null != connection) {

            DatabaseMetaData databaseMetaData = connection.getMetaData();   // 获取数据库的元数据
            ResultSet resultSet = databaseMetaData.getColumns(null, "%",
                    tableName, "%");    // 获取表的字段结果集

            if (label.isControllerRBtn()) {
                generateControllerFile(resultSet);          // 生成Controller层文件
            }

            if (label.isEntityRBtnSelect()) {
                generateModelFile(resultSet);               // 生成Model文件
            }

            if (label.isDaoRBtnSelect()) {
                generateDaoFile(resultSet);                 // 生成Dao文件
                generateRepositoryFile(resultSet);          // 生成Repository文件
            }

            if (label.isServiceRBtnSelect()) {
                generateServiceInterfaceFile(resultSet);    // 生成服务层接口文件
                generateServiceImplFile(resultSet);         // 生成服务实现层文件
            }

            if (label.isMapperRBtnSelect()) {
                generateMapperFile(resultSet);              // 生成Mapper文件
            }

            if (label.isDtoRBtnSelect()) {
                generateDTOFile(resultSet);                 // 生成DTO文件
            }

            resultSet.close();
            connection.close();
        }
    }

    /**
     * 生成Model实体类
     *
     * @param resultSet 结果集
     * @throws Exception 异常信息
     */
    private void generateModelFile(ResultSet resultSet) throws Exception {
        //文件的后缀
        String suffix = ".java";
        //文件生成的路径
        String path = diskPath + changeTableName + suffix;
        String templateName = "Model.ftl";
        File mapperFile = new File(path);
        List<DBColumnDefinition> columnDefinitionList = new ArrayList<DBColumnDefinition>();
        DBColumnDefinition columnDefinition;
        while (resultSet.next()) {
            //id字段略过
            if (resultSet.getString("COLUMN_NAME").equals("id")) continue;
            columnDefinition = new DBColumnDefinition();
            columnDefinition.setColumnName(resultSet.getString("COLUMN_NAME"));
            columnDefinition.setColumnType(resultSet.getString("TYPE_NAME"));
            columnDefinition.setChangeColumnName(DBUtils.replaceUnderLineAndUpperCase(resultSet.getString("COLUMN_NAME")));
            columnDefinition.setColumnComment(resultSet.getString("REMARKS"));
            columnDefinitionList.add(columnDefinition);
        }
        Map<String, Object> dataMap = new HashMap<String, Object>();
        dataMap.put("model_column", columnDefinitionList);
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    /**
     * 生成DTO类
     *
     * @param resultSet 结果集
     * @throws Exception 异常信息
     */
    private void generateDTOFile(ResultSet resultSet) throws Exception {
        String suffix = "DTO.java";
        String path = "D://" + changeTableName + suffix;
        String templateName = "DTO.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    /**
     * 生成Controller类
     *
     * @param resultSet 结果集
     * @throws Exception 异常信息
     */
    private void generateControllerFile(ResultSet resultSet) throws Exception {
        String suffix = "Controller.java";
        String path = diskPath + changeTableName + suffix;
        String templateName = "Controller.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    /**
     * 生成Service实现类
     *
     * @param resultSet 结果集
     * @throws Exception 异常信息
     */
    private void generateServiceImplFile(ResultSet resultSet) throws Exception {
        String suffix = "ServiceImpl.java";
        String path = diskPath + changeTableName + suffix;
        String templateName = "ServiceImpl.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    /**
     * 生成Service接口类
     *
     * @param resultSet 结果集
     * @throws Exception 异常信息
     */
    private void generateServiceInterfaceFile(ResultSet resultSet) throws Exception {
        String prefix = "I";
        String suffix = "Service.java";
        String path = diskPath + prefix + changeTableName + suffix;
        String templateName = "ServiceInterface.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    /**
     * 生成Repository持久层类
     *
     * @param resultSet 结果集
     * @throws Exception 异常信息
     */
    private void generateRepositoryFile(ResultSet resultSet) throws Exception {
        String suffix = "Repository.java";
        String path = diskPath + changeTableName + suffix;
        String templateName = "Repository.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        generateFileByTemplate(templateName, mapperFile, dataMap);
    }

    /**
     * 生成Dao类
     *
     * @param resultSet 结果集
     * @throws Exception 异常信息
     */
    private void generateDaoFile(ResultSet resultSet) throws Exception {
        String suffix = "DAO.java";
        String path = diskPath + changeTableName + suffix;
        String templateName = "DAO.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        generateFileByTemplate(templateName, mapperFile, dataMap);

    }

    /**
     * 生成Mapper类
     *
     * @param resultSet 结果集
     * @throws Exception 异常信息
     */
    private void generateMapperFile(ResultSet resultSet) throws Exception {
        String suffix = "Mapper.xml";
        String path = diskPath + changeTableName + suffix;
        String templateName = "Mapper.ftl";
        File mapperFile = new File(path);
        Map<String, Object> dataMap = new HashMap<String, Object>();
        generateFileByTemplate(templateName, mapperFile, dataMap);

    }

    /**
     * 使用Freemarker模板工具生成代码
     *
     * @param templateName 模板名字
     * @param file         生成文件及文件位置
     * @param dataMap      含有model_column信息的map
     * @throws Exception 异常
     */
    private void generateFileByTemplate(String templateName, File file, Map<String, Object> dataMap) throws Exception {
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName, "/template/tab_1/");
        FileOutputStream fos = new FileOutputStream(file);

        dataMap.put("table_name_small", tableName);         //ay_test
        dataMap.put("table_name", changeTableName);         //AyTest
        dataMap.put("author", authorName);                   //Ay
        dataMap.put("date", currentDate);                    //当前日期
        dataMap.put("package_name", packageName);           //包名
        dataMap.put("table_annotation", tableAnnotation);   //表注释

        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"), 10240);
        template.process(dataMap, out);
    }

    /**
     * 测试方法
     *
     * @param args 参数
     * @throws Exception 异常
     */
    public static void main(String[] args) throws Exception {
        GeneratorPageInfo info = new GeneratorPageInfo();
        info.setDbType("mysql");
        info.setIp("127.0.0.1");
        info.setPort("3306");
        info.setDbName("demo");
        info.setDbUserName("root");
        info.setDbPassword("root");

        info.setTableName("user");
        info.setAuthorName("mcchu");
        info.setTableAnnotation("用户信息表");
        info.setPackageName("com.demo");
        info.setDiskPath("D://");

        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils(info);
        codeGenerateUtils.generate(new GeneratorCheckBoxLabel());
    }

}









