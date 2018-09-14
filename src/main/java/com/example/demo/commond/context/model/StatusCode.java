package com.example.demo.commond.context.model;

/**
 * 状态码<br>
 * 定义统一标准：<br>
 * 200编码在任何情况下都表示 “无异常”、“完成”和“正确”等概念。<br>
 * 500编码在任何情况下都表示 “系统内部异常”等概念。<br>
 * ......(留待以后添加)<br>
 * 小于1000的编码为系统保留编码，请业务编码不要使用
 * @author Smile(wangyajun)
 * @create 2018-09-13 20:50
 **/
public interface StatusCode {

    /** 中文编码 */
    String getCnStatusCode();

    /** 状态描述 */
    String getDescribe();

    /** 英文编码 */
    String getEnStatusCode();

    /** 状态信息 */
    String getMessage();

    /** 数字编码，小于1000的编码为系统保留编码，请业务编码不要使用 */
    int getStatusCode();
}