package com.example.demo.core.exception;

import com.example.demo.commond.context.model.StatusCode;
import com.example.demo.commond.context.model.StatusCodeEnum;
import com.example.demo.utils.SmileUtils;

/**
 * 系统内部逻辑异常:System Inner Logic Exception 系统内部错误 <br>
 * errorMessage用于异常抛送到页面后，为用户显示错误 <br>
 * 因具体的系统使用者不用太过关心message(详细错误信息) <br>
 * 如果非简体中文系统，则可以通过errorCode得到一个errorCode到errorMessage的映射 以达到系统兼容未来多语言或多显示型式的方式 <br>
 * errorMessage在某种型式上可以看为defaultErrorCodeMessage即当前错误码对应的错误信息<br>
 * 系统内部逻辑异常封装理念： <br>
 * 1、告诉使用者的错误消息尽量精炼。 <br>
 * 2、最终使用者不期望看到一堆错误堆栈，堆栈应当打到后台日志中，显示在前台的应该很简单的语言能够描述。 <br>
 * 3、合法性的提示应该在客户提交以前，就以js的形式进行提示，纠正 <br>
 * 4、如果遇到提交到后台才发现错误，错误反馈到前端，应该能容忍说明不清楚，这个应该算作BUG进行修正。 <br>
 * 5、不应当客户提交信息后，才发现错误存在
 *
 * @author smile
 * @version [版本号, 2016年01月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class SmileException extends RuntimeException {
    private static final long serialVersionUID = -7509341658495290305L;

    /** 状态码 */
    private StatusCode statusCode;

    public SmileException() {
        super();
    }

    public SmileException(StatusCode statusCode) {
        super();
        this.statusCode = statusCode;
    }

    public SmileException(StatusCode statusCode, String message, Object... parameters) {
        super(SmileUtils.toString(message, parameters));
        this.statusCode = statusCode;
    }

    public SmileException(String message, Object... parameters) {
        super(SmileUtils.toString(message, parameters));
    }

    public SmileException(Throwable cause) {
        this(cause, cause.getMessage());
    }

    public SmileException(Throwable cause, String message, Object... parameters) {
        super(SmileUtils.toString(message, parameters), cause);
    }


    /**
     * 获取系统错误编码<br>
     * 表明唯一异常<br>
     * errorMessage用于异常抛送到页面后，为用户显示错误 因具体的系统使用者不用太过关心message(详细错误信息) <br>
     * 如果非简体中文系统，则可以通过errorCode得到一个errorCode到errorMessage的映射 以达到系统兼容未来多语言或多显示型式的方式
     *
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getErrorCode() {
        return doGetErrorCode();
    }

    /**
     * 获取错误信息<br/>
     *
     * @return [参数说明]
     *
     * @return String [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public String getErrorMessage() {
        return doGetErrorMessage();
    }

    /** 状态码 */
    public StatusCode getStatusCode() {
        return statusCode == null ? doGetStatusCode() : statusCode;
    }

    /** 状态码 */
    public void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        String message = super.getMessage();
        String errorCode = getErrorCode();
        String errorMessage = getErrorMessage();
        String errorSimpleName = getClass().getSimpleName();
        String cnStatusCode = statusCode == null ? "" : statusCode.getCnStatusCode();

        //        int length = 2 + 3 + 16 + 19 + 14 + 17 + length(errorSimpleName) + length(errorCode) + length(errorMessage) + length(message) + 1;
        int length = 72 + length(errorSimpleName) + length(cnStatusCode) + length(errorCode) + length(errorMessage) + length(message);
        StringBuilder sb = new StringBuilder(length);
        sb.append("\r\n");  // 2
        sb.append(errorSimpleName).append(":\r\n"); //3
        sb.append("   statusCode: ").append(cnStatusCode).append("\r\n"); // 17
        sb.append("   errorCode: ").append(errorCode).append("\r\n");   //16
        sb.append("   errorMessage: ").append(errorMessage).append("\r\n"); //19
        sb.append("   message: ").append(message).append("\r\n");   //14
        return sb.toString();
    }

    /** 求字符串长度 */
    private int length(String str) {
        return str == null ? 0 : str.length();
    }

    /**
     * 获取系统错误编码<br>
     * errorMessage用于异常抛送到页面后，为用户显示错误 因具体的系统使用者不用太过关心message(详细错误信息)<br>
     * 如果非简体中文系统，则可以通过errorCode得到一个errorCode到errorMessage的映射 以达到系统兼容未来多语言或多显示型式的方式
     *
     * @return String 系统错误编码
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected String doGetErrorCode() {
        return "RAINHY_ERROR";
    }

    /**
     * 获取错误描述（展示）信息<br>
     * 不需要太过详细，用户不用太关注系统内部的错误<br>
     *
     * @return String 给用户的展示信息
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected String doGetErrorMessage() {
        return "RAINHY 系统内部错误";
    }

    /** 状态码 */
    protected StatusCode doGetStatusCode() {
        return StatusCodeEnum.SC_INTERNAL_SERVER_ERROR;
    }
}