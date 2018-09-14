/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月14日
 * 项目： rainhy-webcore
 */
package com.example.demo.commond;

import com.example.demo.commond.context.model.StatusCode;
import com.example.demo.core.exception.SmileException;

/**
 * 命令容器异常
 * 
 * @author rain
 * @version [版本号, 2018年3月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CommandException extends SmileException {

    /** 注释内容 */
    private static final long serialVersionUID = 5404454353928002832L;

    public CommandException() {
        super();
    }

    public CommandException(StatusCode statusCode, String message, Object... parameters) {
        super(statusCode, message, parameters);
    }

    public CommandException(StatusCode statusCode) {
        super(statusCode);
    }

    public CommandException(String message, Object... parameters) {
        super(message, parameters);
    }

    public CommandException(Throwable cause, String message, Object... parameters) {
        super(cause, message, parameters);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }

}
