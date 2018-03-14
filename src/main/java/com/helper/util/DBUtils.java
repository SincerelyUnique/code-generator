package com.helper.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * <p>
 * <code>DriverUtils</code>
 * </p>
 * Description:
 *
 * @author Mcchu
 * @date 2018/3/2 15:18
 */
public class DBUtils {

    /**
     * 获取驱动名
     * @param dbType 数据库类型
     * @return 驱动名
     */
    private static String getDBDriver( String dbType ){

        if ( dbType.equals("oracle") ){
            return "oracle.jdbc.driver.OracleDriver";
        }else if ( dbType.equals("sql server") ){
            return "com.microsoft.jdbc.sqlserver.SQLServerDriver";
        }else if ( dbType.equals("mysql") ){
            return "com.mysql.jdbc.Driver";
        }else {
            return null;
        }
    }

    /**
     * 获取数据库地址
     * @param dbType 数据库类型
     * @param ip ip
     * @param port port
     * @param dbName 数据库名
     * @return 地址
     */
    private static String getDBUrl( String dbType,
                                   String ip,
                                   String port,
                                   String dbName ){
        if ( dbType.equals("oracle") ){
            return "jdbc:oracle:thin:@"+ip+":"+port+":"+dbName;
        }else if ( dbType.equals("sql server") ){
            return "jdbc:microsoft:sqlserver://"+ip+":"+port+";DatabaseName="+dbName;
        }else if ( dbType.equals("mysql") ){
            return "jdbc:mysql://"+ip+":"+port+"/"+dbName+"?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        }else {
            return "";
        }
    }


    /**
     * 描述：生成数据库连接
     * @param dbType 数据库类型
     * @param ip ip
     * @param port 端口
     * @param name 数据库名字
     * @param dbUsername 用户名
     * @param dbPassword 密码
     * @return 连接
     */
    public static Connection getConnection( String dbType,
                                            String ip,
                                            String port,
                                            String name,
                                            String dbUsername,
                                            String dbPassword) {
        try {
            String driverName = getDBDriver(dbType);
            Class.forName(driverName);
            String dbUrl = getDBUrl(dbType,ip,port,name);
            return DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        }catch (SQLException se){
            System.out.println("=======SQLException======");
            se.printStackTrace();
            return null;
        }catch (ClassNotFoundException ce){
            System.out.println("=======ClassNotFoundException======");
            ce.printStackTrace();
            return null;
        }
    }

    /**
     * 描述：字符去掉下划线和转化字母的大小写
     * @param str 字符串
     * @return eg: ay_test 变成 AyTest
     */
    public static String replaceUnderLineAndUpperCase(String str){
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        int count = sb.indexOf("_");
        while(count!=0){
            int num = sb.indexOf("_",count);
            count = num + 1;
            if(num != -1){
                char ss = sb.charAt(count);
                char ia = (char) (ss - 32);
                sb.replace(count , count + 1,ia + "");
            }
        }
        String result = sb.toString().replaceAll("_","");
        return StringUtils.capitalize(result);
    }

    public static String replaceUnderLineAndUpperCaseForOA( String str ){
        if ( str.contains("_") ){
            StringBuffer sb = new StringBuffer();
            String[] words = str.split("_");
            for ( String word: words ){
                word = StringUtils.capitalize(word);
                sb.append(word);
            }
            return sb.toString();
        }else {
            str = StringUtils.capitalize(str);
            return str;
        }
    }

    public static void main(String[] args) {
        String str = "formtable_main_187";
        String s = DBUtils.replaceUnderLineAndUpperCaseForOA(str);
        System.out.println(s);

        String str1 = "formtable_main_187";
        String str2 = StringUtils.capitalize(str1);
        System.out.println(str2);
    }
}
