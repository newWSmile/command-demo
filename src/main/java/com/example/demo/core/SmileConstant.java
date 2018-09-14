/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2016年9月5日
 * 项目： rainhy-core
 */
package com.example.demo.core;

/**
 * Rainhy 常量
 *
 * @author rain
 * @version [版本号, 2016年9月5日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SmileConstant {
    
    /** 字符串_数字字母 */
    public static final char[] COMMON_STRING_CHAR_FULL = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    
    /** 字符串_数字大写字母 */
    public static final char[] COMMON_STRING_CHAR_UPPER = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    
    /** 字符串_数字小写字码 */
    public static final char[] COMMON_STRING_CHAR_LOWER = "0123456789abcdefghijklmnopqrstuvwxyz".toCharArray();
    
    /**
     * 日期时间格式,从上往下尝试转换类型,所以最可能的放到上面<br>
     *
     * <pre>
     * "yyyy-MM-dd HH:mm:ss"
     * "yyyy-MM-dd"
     * "yyyy-MM-dd HH:mm"
     * "yyyyMMddHHmmss"
     * "yyyyMMdd"
     * "HH:mm:ss"
     * "yyyy-MM"
     * "yyyy-MM-dd HH"
     * "HH:mm"
     * "yyyyMMddHHmm"
     * "yyyyMM"
     * "yyyyMMddHH"
     * "HHmm"
     * "HHmmss"
     * "yyyy"
     * </pre>
     *
     */
    public static final String[] COMMON_DATE_PATTERN = new String[] {
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm",
            "yyyyMMddHHmmss",
            "yyyyMMdd",
            "HH:mm:ss",
            
            "yyyy-MM",
            "yyyy-MM-dd HH",
            "HH:mm",
            "yyyyMMddHHmm",
            "yyyyMM",
            "yyyyMMddHH",
            "HHmm",
            "HHmmss",
            "yyyy",
    };
}
