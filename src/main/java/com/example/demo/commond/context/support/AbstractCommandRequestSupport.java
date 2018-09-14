/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月14日
 * 项目： rainhy-webcore
 */

package com.example.demo.commond.context.support;

import com.example.demo.commond.CommandException;
import com.example.demo.commond.context.*;
import com.example.demo.core.tool.ParameterizedTypeReference;
import org.apache.commons.lang3.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.util.Assert;

/**
 * 默认的共存请求支撑类实现<br>
 * 一个线程中，允许多个命令支撑器递归执行，新的被执行时，上一个命令会话被挂起，直到新命令执行完成，继续执行。
 *
 * @author rain
 * @version [版本号, 2018年3月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class AbstractCommandRequestSupport<REQUEST extends CommandRequest, RECEIVER extends CommandReceiver<REQUEST>>
        extends ParameterizedTypeReference<REQUEST>
        implements CommandRequestSupport {

    /**
     * 日志记录器
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 操作接收器
     */
    protected RECEIVER receiver;

    /**
     * 是否是排它支撑器<br>
     * true, 排它：一个线程中，只能有一个命令被执行，也就是说一个命令被执行时，另外一个命令如果被执行，则抛出异常。<br>
     * false,共享：一个线程中，允许多个命令支撑器递归执行，新的被执行时，上一个命令会话被挂起，直到新命令执行完成，继续执行。
     */
    protected boolean exclusive;

    public AbstractCommandRequestSupport(boolean exclusive) {
        super();
        this.exclusive = exclusive;
    }

    @Override
    public boolean isSupportRequest(Class<? extends CommandRequest> requestClass) {
        return ClassUtils.isAssignable(requestClass, getRawClass());
    }

    public AbstractCommandRequestSupport(boolean exclusive, RECEIVER receiver) {
        Assert.notNull(receiver, "receiver is null.");
        this.exclusive = exclusive;
        this.receiver = receiver;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void handle(CommandRequest request, CommandResponse response) {
        //判断入参的合法性
        Assert.notNull(request, "request is null.");

        if (!this.receiver.doBeforeOpenSession((REQUEST) request, response)) {
            return;
        }

        //开启会话实例<br/>
        CommandRequestSessionContext.open(request, response);
        CommandException commandException = null;
        //异常接收器
        try {
            //前置处理:一般提供给具体的receiver进行操作的合法性判断
            boolean flag = this.receiver.doAfterOpenSession((REQUEST) request, response);
            if (!flag) {
                return;
            }

            if (exclusive) {
                CommandRequestSessionContext.lockSuspend();
                //实际处理逻辑
                try {
                    doHandle((REQUEST) request, response);
                } finally {
                    CommandRequestSessionContext.unLockSuspend();
                }
            } else {
                doHandle((REQUEST) request, response);
            }

            this.receiver.doBeforeCompletion((REQUEST) request, response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            if (ex instanceof CommandException) {
                commandException = (CommandException) ex;
            } else {
                String error = "异常的容器处理类: " + AopUtils.getTargetClass(this.receiver).getName() + "\r\n" + ex.getMessage();
                commandException = new CommandException(error, ex);
            }
            throw commandException;
        } finally {
            CommandRequestSessionContext.close();

            //会话关闭后执行逻辑
            this.receiver.doAfterCompletion((REQUEST) request, response, commandException);
        }
    }

    /**
     * 操作接收器
     */
    public void setReceiver(RECEIVER receiver) {
        this.receiver = receiver;
    }

    /**
     * 实际处理逻辑
     *
     * @param request  操作请求器
     * @param response 操作返回
     * @return void [返回类型说明]
     * @throws throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    protected abstract void doHandle(REQUEST request, CommandResponse response);
}
