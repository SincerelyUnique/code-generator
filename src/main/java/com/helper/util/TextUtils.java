package com.helper.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * <code>TextUtils</code>
 * </p>
 * Description:
 *
 * @author Mcchu
 * @date 2018/3/8 13:47
 */
public class TextUtils {

    /**
     * 大写字母转小写
     * @param text 文本
     * @return 小写文本
     */
    public static String upperToLower( String text ){
        return text.toLowerCase();
    }

    /**
     * 小写字母转大写
     * @param text 文本
     * @return 小写文本
     */
    public static String lowerToUpper( String text ){
        return text.toUpperCase();
    }

    /**
     * 字符串转Unicode
     * @param text 文本
     * @return unicode编码
     */
    public static String stringToUnicode( String text ){
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            unicode.append("\\u" + Integer.toHexString(c));
        }
        return unicode.toString();
    }

    /**
     * Unicode转字符串
     * @param text 文本
     * @return 字符串
     */
    public static String unicodeToString( String text ){
        StringBuffer string = new StringBuffer();
        String[] hex = text.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);
            string.append((char) data);
        }
        return string.toString();
    }

    /**
     * 文本字节长度统计
     * @param text 文本
     * @return 长度
     */
    public static String countByte( String text ){
        return String.valueOf(text.length());
    }

    /**
     * 获取文本中英文字母数量
     * @param text 文本
     * @return 数量
     */
    public static String englishLetterLength( String text ){
        Integer length = 0;
        byte[] bytes = text.getBytes();
        for (int i = 1; i < bytes.length; i++) {
            byte c = bytes[i];
            if( c>='A'&&c<='Z' || c>='a'&&c<='z' ){
                length++;
            }
        }
        return String.valueOf(length);
    }

    /**
     * 统计字母数
     * @param str 文本
     * @return 字母数
     */
    public static int countLetter(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    /**
     * 统计汉字数
     * @param str 文本
     * @return 汉字个数
     */
    public static int countChinese(String str) {
        int count = 0;
        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }

    /**
     * 统计空格数
     * @param str 文本
     * @return 空格数
     */
    public static int countSpace(String str) {
        int count = 0;
        Pattern p = Pattern.compile("\\s");
        Matcher m = p.matcher(str);
        while(m.find()){
            count++;
        }
        return count;
    }
}
