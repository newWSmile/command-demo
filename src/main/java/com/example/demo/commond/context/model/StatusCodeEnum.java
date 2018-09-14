/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2017年2月16日
 * 项目： tkm-webcore
 */
package com.example.demo.commond.context.model;

/**
 * 状态编码<br>
 * 1xx~5xx 全部为 http 的 status 编码<br>
 * 6xx~9xx 开始,为 smile 自定义的 status 编码<br>
 * 100xx 开始,为 smile 自定义的 status 编码<br>
 *
 *
 * @author smile
 * @version [版本号, 2018-09-14]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public enum StatusCodeEnum implements StatusCode {
    SC_UNKNOW(-1, "未知", "Unknow"),
    
    // --- 1xx Informational(httpstatus) ---
    
    // --- 2xx Success(httpstatus) ---
    SC_OK(200, "成功", "OK"),
    
    SC_CREATED(201, "创建成功", "Created"),
    
    // --- 3xx Redirection(httpstatus) ---
    
    // --- 4xx Client Error(httpstatus) ---
    SC_NO_ACCESS(403, "禁止访问", "No Access"),
    
    SC_NOT_FOUND(404, "未找到", "Not Found"),
    
    SC_METHOD_NOT_ALLOWED(405, "不允许的方法", "Method Not Allowed"),
    
    // --- 5xx Server Error(httpstatus) ---
    /** 系统内部异常 */
    SC_INTERNAL_SERVER_ERROR(500, "系统内部异常", "Internal Server Error"),
    
    SC_SERVICE_UNAVAILABLE(503, "服务不可用", "Service unavailable"),
    
    // --- 6xx Account Error(tkm)
    /** 账户不存在 */
    SC_ACCOUNT_NOT_FOUND(600, "账户不存在", "Not Found Account"),
    
    /** 账户登录名错误 */
    SC_ACCOUNT_LOGIN_NAME_ERROR(601, "账户登录名错误", "Not Login Name Error"),
    
    /** 账户登录名为空 */
    SC_ACCOUNT_LOGIN_NAME_EMPTY(602, "账户登录名为空", "Not Login Name Empty"),
    
    /** 账户密码错误 */
    SC_ACCOUNT_PASSWORD_ERROR(603, "账户密码错误", "Account Password Error"),
    
    /** 账户密码为空 */
    SC_ACCOUNT_PASSWORD_EMPTY(604, "账户密码为空", "Account Password Empty"),
    
    /** 账户未启用 */
    SC_ACCOUNT_UNENABLE(605, "账户未启用", "Account Unenable"),
    
    /** 登录回调地址为空 */
    SC_ACCOUNT_LOGIN_BACKURL_EMPTY(606, "登录回调地址为空", "Account Login BackUrl Empty"),
    
    /** 错误的登录回调地址 */
    SC_ACCOUNT_LOGIN_BACKURL_ERROR(607, "错误的登录回调地址", "Account Login BackUrl Error"),
    
    /** 账户类型为空 */
    SC_ACCOUNT_TYPE_EMPTY(608, "账户类型为空", "Account Type Empty"),
    
    /** 账户类型错误 */
    SC_ACCOUNT_TYPE_ERROR(609, "账户类型错误", "Account Type ERROR"),
    
    // --- 7xx，token、jwt、session等相关
    /** Token不存在 */
    SC_TOKEN_NOT_FOUND(700, "Token不存在", "Not Found Token"),
    
    /** Token无效 */
    SC_TOKEN_INVALID(701, "Token无效", "Token Invalid"),
    
    /** Token格式错误 */
    SC_TOKEN_FORMAT_ERROR(702, "Token格式错误", "Token Format Error"),
    
    /** Token鉴权失败 */
    SC_TOKEN_AUTHENTICATION_FAILURE(703, "Token鉴权失败", "Token Authentication Failure"),
    
    /** Token超时 */
    SC_TOKEN_TIMEOUT(704, "Token超时", "Token Timeout"),
    
    // --- 8xx, 数据 相关
    SC_DATA_ERROR(801, "数据异常", "DATA ERROR"),
    
    /** 数据丢失 */
    SC_DATA_LOSE(802, "数据丢失", "DATA LOSE")
    
    ;
    public static StatusCodeEnum valueOf(int statusCode) {
        for (StatusCodeEnum statusCodeEnum : StatusCodeEnum.values()) {
            if (statusCode == statusCodeEnum.statusCode) {
                return statusCodeEnum;
            }
        }
        return SC_UNKNOW;
    }
    
    /** 数字编码 */
    private int statusCode;
    
    /** 中文编码 */
    private String cnStatusCode;
    
    /** 英文编码 */
    private String enStatusCode;
    
    StatusCodeEnum(int statusCode, String cnStatusCode, String enStatusCode) {
        this.statusCode = statusCode;
        this.cnStatusCode = cnStatusCode;
        this.enStatusCode = enStatusCode;
    }
    
    /** 中文编码 */
    @Override
    public String getCnStatusCode() {
        return this.cnStatusCode;
    }
    
    @Override
    public String getDescribe() {
        return null;
    }
    
    /** 英文编码 */
    @Override
    public String getEnStatusCode() {
        return this.enStatusCode;
    }
    
    @Override
    public String getMessage() {
        return this.cnStatusCode;
    }
    
    /** 数字编码 */
    @Override
    public int getStatusCode() {
        return this.statusCode;
    }
    
}
