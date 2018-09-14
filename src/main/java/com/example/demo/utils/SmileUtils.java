/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2016年1月21日
 * 项目： rainhy-cores-core
 */
package com.example.demo.utils;

import com.example.demo.core.SmileConstant;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.MessageFormatter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.NumberFormat;
import java.util.*;

/**
 * Rainhy 工具类
 *
 * @author rain
 * @version [版本号, 2016年1月21日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class SmileUtils {
    
    /**
     *
     * 获取传入列表中所有元素的组合(不进行排序)<br>
     * 经过小量测试。20个元素以内，速度很快，超过20个元素，速度曾现指数变慢
     *
     * @param list
     *
     * @return List<List<T>> [返回类型说明]
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2017年9月4日]
     * @author rain
     */
    public static <T> List<List<T>> combines(List<T> list) {
        List<List<T>> value = new ArrayList<>();
        for (int i = 1; i <= list.size(); i++) {
            combine(value, list, new ArrayList<>(), 0, list.size() - 1, i);
        }
        return value;
    }
    
    /**
     *
     * 混淆身份证号码
     *
     * @param idcard 身份证号码
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2016年3月15日]
     * @author rain
     */
    public static String confuseIdCard(String idcard) {
        if (StringUtils.isEmpty(idcard)) {
            return idcard;
        }
        int length = idcard.length();
        if ((length == 15) || (length == 18)) {
            String star = null;
            if (length == 15) {
                star = "*****";
            } else {
                star = "********";
            }
            return StringUtils.left(idcard, 6) + star + StringUtils.right(idcard, 4);
        } else {
            return idcard;
        }
    }
    
    /**
     *
     * 混淆移动电话
     *
     * @param telephone 移动电话号码
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2016年3月15日]
     * @author rain
     */
    public static String confuseTelephone(String telephone) {
        if (StringUtils.isEmpty(telephone)) {
            return telephone;
        }
        int length = telephone.length();
        if (length == 11) {
            return StringUtils.left(telephone, 3) + "****" + StringUtils.right(telephone, 4);
        } else {
            return telephone;
        }
    }
    
    /** 返回set中第一个元素 */
    public static <T> T first(Set<T> sets) {
        Iterator<T> iterator = sets.iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
    
    /**
     *
     * 金钱格式化
     *
     * @param money
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2015年10月9日]
     * @author rain
     */
    public static String formatMoney(double money) {
        return NumberFormat.getCurrencyInstance(Locale.CHINA).format(money);
    }
    
    /**
     *
     * 格式化数字
     *
     * @param number 数字
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2015年10月19日]
     * @author rain
     */
    public static String formatNumber(double number) {
        return NumberFormat.getNumberInstance(Locale.CHINA).format(number);
    }
    
    /**
     *
     * 获取堆栈信息
     *
     * @param e 错误异常
     *
     * @return String 堆栈信息
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2015年10月20日]
     * @author rain
     */
    public static String getStackTraceMessage(Throwable e) {
        try (StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);) {
            
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
            
            return sw.toString();
        } catch (IOException e1) {
            return e.getMessage();
        }
    }
    
    /**
     *
     * 替换特殊字符<br>
     * 这里的特殊字符串,特指:数字和大小写字符之外的任何字符
     *
     * @param str 被替换的字符串
     * @param replacement 替换的字符
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2016年9月5日]
     * @author rain
     */
    public static String replaceSpecialCharacter(String str, char replacement) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            boolean contains = ArrayUtils.contains(SmileConstant.COMMON_STRING_CHAR_FULL, c);
            if (contains) { // 不包含特殊字符
                sb.append(c);
            } else {    // 包含特殊字符
                sb.append(replacement);
            }
        }
        return sb.toString();
    }
    
    /**
     *
     * 拼接字符串<br>
     * "拼接[{}]: {}" => 参数1:模板,参数2:abcd => "拼接[模板]: abcd"<br>
     *
     * @param message 拼接模板
     * @param objects 拼接参数
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see MessageFormatter#arrayFormat(String, Object[])
     * @version [版本号, 2016年5月21日]
     * @author rain
     */
    public static String toString(String message, Object... objects) {
        if (ArrayUtils.isEmpty(objects)) {
            return message;
        }
        return MessageFormatter.arrayFormat(message, objects).getMessage();
    }
    
    private static <T> void combine(List<List<T>> value, List<T> specimenList, List<T> tempList, int start, int end, int length) {
        
        if (length == 0) {
            value.add(new ArrayList<>(tempList));
            return;
        }
        
        for (int i = start; i <= ((end - length) + 1); i++) {
            tempList.add(specimenList.get(i));
            combine(value, specimenList, tempList, i + 1, end, length - 1);
            tempList.remove((specimenList.get(i)));
        }
        
    }
    
}
