package com.helper.util;

import com.helper.model.*;
import freemarker.template.Template;

import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

/**
 * 描述：代码生成器
 * Created by Ay on 2017/5/1.
 */
public class OACodeGenerateUtils {

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
    private String sql;

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

    public OACodeGenerateUtils(GeneratorPageInfo info ){
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
        this.changeTableName = DBUtils.replaceUnderLineAndUpperCaseForOA(info.getTableName());
        this.sql = info.getSql();
    }

    /**
     * 描述：代码生成器的核心方法
     */
    public void generate( GeneratorCheckBoxLabel label, String tableName ) throws Exception{
        StringBuffer sb1 = new StringBuffer();
        sb1.append("SELECT U.COLUMN_NAME,U.DATA_TYPE,U.DATA_LENGTH,U.DATA_PRECISION,U.DATA_SCALE FROM USER_TAB_COLUMNS U WHERE U.TABLE_NAME='");
        sb1.append(tableName.toUpperCase());
        sb1.append("'");
        String sqlStr1 = sb1.toString();

        StringBuffer sb2 = new StringBuffer();
        sb2.append("SELECT B.ID, LOWER(B.FIELDNAME) AS FIELDNAME, H.LABELNAME, B.DETAILTABLE");
        sb2.append("  FROM WORKFLOW_BILLFIELD B");
        sb2.append("  LEFT JOIN WORKFLOW_BASE A ON (A.FORMID = B.BILLID)");
        sb2.append("  LEFT JOIN HTMLLABELINFO H ON (B.FIELDLABEL = H.INDEXID)");
        sb2.append(" WHERE B.BILLID = ? AND DETAILTABLE IS NULL AND H.LANGUAGEID = 7");
        String sqlStr2 = sb2.toString();

        List<OAColumn> columns = new ArrayList<OAColumn>();
        List<OALabel> labels = new ArrayList<OALabel>();

        Connection conn = DBUtils.getConnection(dbType,dbIp,dbPort,dbName,dbUserName,dbPassword);
        PreparedStatement ps1 = conn.prepareStatement(sqlStr1);
        ResultSet rs1 = ps1.executeQuery();
        while (rs1.next()){
            OAColumn column = new OAColumn();
            column.setColumnName(rs1.getString("COLUMN_NAME"));
            column.setDataType(rs1.getString("DATA_TYPE"));
            column.setColumnLength(rs1.getString("DATA_LENGTH"));
            column.setColumnPrecision(rs1.getString("DATA_PRECISION"));
            column.setColumnScale(rs1.getString("DATA_SCALE"));
            columns.add(column);
        }

        PreparedStatement ps2 = conn.prepareStatement(sqlStr2);
        ps2.setInt(1, -(Integer.parseInt(tableName.split("_")[2])));
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()){
            OALabel label1 = new OALabel();
            label1.setFieldName(rs2.getString(2));
            label1.setLabelName(rs2.getString(3));
            labels.add(label1);
        }

        // 封装数据
        List<DBColumnDefinition> columnDefinitionList = getFinalData(columns,labels);

        if (label.isEntityRBtnSelect()){ generateModelFile(columnDefinitionList); }
        if (label.isDaoRBtnSelect()){ generateDaoFile(columnDefinitionList); }

    }

    /**
     * 生成Model实体类
     * @param columnDefinitionList 结果集
     * @throws Exception 异常信息
     */
    private void generateModelFile( List<DBColumnDefinition> columnDefinitionList ) throws Exception{
        //文件的后缀
        String suffix = ".java";
        //文件生成的路径
        String path = diskPath + changeTableName + suffix;
        String templateName = "OAModel.ftl";
        File mapperFile = new File(path);
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("model_column", columnDefinitionList);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    /**
     * 生成Dao类
     * @param columnDefinitionList 结果集
     * @throws Exception 异常信息
     */
    private void generateDaoFile( List<DBColumnDefinition> columnDefinitionList ) throws Exception{
        Map<String,Object> dataMap = new HashMap<String,Object>();
        dataMap.put("model_column", columnDefinitionList);
        String suffix = "DAO.java";
        String path = diskPath + changeTableName + suffix;
        String templateName = "OADAO.ftl";
        File mapperFile = new File(path);
        generateFileByTemplate(templateName,mapperFile,dataMap);
    }

    private static List<DBColumnDefinition> getFinalData( List<OAColumn> columns,List<OALabel> labels ){
        List<DBColumnDefinition> definitions = new ArrayList<DBColumnDefinition>();
        for ( OAColumn column:columns ){
            String columnName = column.getColumnName().toLowerCase();
            for ( OALabel label: labels ){
                String fieldName = label.getFieldName().toLowerCase();
                if ( columnName.equals(fieldName) ){
                    DBColumnDefinition definition = new DBColumnDefinition();
                    definition.setColumnName(columnName);
                    definition.setColumnType(column.getDataType());
                    definition.setColumnLength(Integer.parseInt(column.getColumnLength()));
                    if ( null!=column.getColumnPrecision() ){
                        definition.setColumnPrecision(Integer.parseInt(column.getColumnPrecision()));
                    }else {
                        definition.setColumnPrecision(10000);
                    }
                    if ( null!=column.getColumnScale() ){
                        definition.setColumnScale(Integer.parseInt(column.getColumnScale()));
                    }else {
                        definition.setColumnScale(10000);
                    }
                    definition.setColumnComment(label.getLabelName());
                    definition.setChangeColumnName(DBUtils.replaceUnderLineAndUpperCase(columnName));
                    definitions.add(definition);
                }
            }
        }
        return definitions;
    }

    /**
     * 使用Freemarker模板工具生成代码
     * @param templateName 模板名字
     * @param file 生成文件及文件位置
     * @param dataMap 含有model_column信息的map
     * @throws Exception 异常
     */
    private void generateFileByTemplate(String templateName,File file,Map<String,Object> dataMap) throws Exception{
        Template template = FreeMarkerTemplateUtils.getTemplate(templateName,"/template/tab_2/");
        FileOutputStream fos = new FileOutputStream(file);

        dataMap.put("table_name_small", tableName);         //ay_test
        dataMap.put("table_name", changeTableName);         //AyTest
        dataMap.put("author",authorName);                   //Ay
        dataMap.put("date",currentDate);                    //当前日期
        dataMap.put("package_name", packageName);           //包名
        dataMap.put("table_annotation", tableAnnotation);   //表注释
        dataMap.put("sql", sql);

        Writer out = new BufferedWriter(new OutputStreamWriter(fos, "utf-8"),10240);
        template.process(dataMap,out);
    }

}









