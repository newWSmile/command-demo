/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月14日
 * 项目： rainhy-webcore
 */
package com.example.demo.commond.context;

import com.example.demo.commond.CommandException;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 请求会话容器
 * 
 * @author rain
 * @version [版本号, 2018年3月14日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CommandRequestSessionContext {
    
    /** 当前线程中的操作容器实例 */
    private static ThreadLocal<Stack<CommandRequestSessionContext>> context = new ThreadLocal<Stack<CommandRequestSessionContext>>() {
        @Override
        protected Stack<CommandRequestSessionContext> initialValue() {
            return new Stack<CommandRequestSessionContext>();
        }
    };
    
    /**
     * 关闭一个操作请求会话
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void close() {
        Stack<CommandRequestSessionContext> stack = CommandRequestSessionContext.context.get();
        //将出栈的会话进行清理
        stack.pop().clear();
        //如果堆栈顶部仍然存在交易，则对该交易重新进行持久
        if (stack.isEmpty()) {
            CommandRequestSessionContext.context.remove();
        } else {
            stack.peek().setLockSuspend(false);
        }
    }
    
    /**
     * 获取当前的操作会话
     * 
     * @return [参数说明]
     * 
     * @return ProcessSessionContext [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static CommandRequestSessionContext getSession() {
        Stack<CommandRequestSessionContext> stack = CommandRequestSessionContext.context.get();
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }
    
    /**
     * 挂起一个操作请求会话
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void lockSuspend() {
        //该会话必须在事务中进行执行
        //        AssertUtils.isTrue(TransactionSynchronizationManager.isSynchronizationActive(), "必须在事务中进行执行");
        //获取堆栈
        Stack<CommandRequestSessionContext> stack = CommandRequestSessionContext.context.get();
        Assert.notEmpty(stack, "stack is empty");
        
        //取栈顶值
        stack.peek().setLockSuspend(true);
    }
    
    /**
     * 打开一个操作请求会话<br>
     * 必须在一个事务中执行
     * 
     * @param request 命令请求器
     * @param response 返回实例
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void open(CommandRequest request, CommandResponse response) {
        //该会话必须在事务中进行执行
        //        AssertUtils.isTrue(TransactionSynchronizationManager.isSynchronizationActive(), "必须在事务中进行执行");
        //获取堆栈
        Stack<CommandRequestSessionContext> stack = CommandRequestSessionContext.context.get();
        //如果堆栈中存在原来交易，将堆栈中交易，进行持久后，再将新值进行插入。如果不存在原始堆栈信息，则直接跳过，创建新的session，如果存在，则进行挂起判定
        if (!stack.isEmpty()) {
            CommandRequestSessionContext peek = stack.peek();
            //如果栈顶存在,判断堆栈顶的session是否为锁定挂起状态，如果是的，则抛出异常
            if (peek.isLockSuspend()) {
                MapUtils.debugPrint(System.err, peek.getCommandRequest().getClass().getName(), peek.getAttributes());
                throw new CommandException("当前会话不能进行挂起，或已经被挂起。");
            }
            peek.setLockSuspend(true);
        }
        //构建新的堆栈
        stack.push(new CommandRequestSessionContext(request, response));
    }
    
    /**
     * 解锁一个操作请求会话
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public static void unLockSuspend() {
        //该会话必须在事务中进行执行
        //        AssertUtils.isTrue(TransactionSynchronizationManager.isSynchronizationActive(), "必须在事务中进行执行");
        //获取堆栈
        Stack<CommandRequestSessionContext> stack = CommandRequestSessionContext.context.get();
        Assert.notEmpty(stack, "stack is empty");
        
        //取栈顶值
        stack.peek().setLockSuspend(false);
    }
    
    /** 请求实例 */
    private CommandRequest request;
    
    /** 请求应答实例 */
    private CommandResponse response;
    
    /** 会话中的参数实例 */
    private Map<String, Object> attributes;
    
    /** 锁定挂起功能 会话如果被挂起（压栈），必须先检查对应的逻辑是被锁定挂起，如果已经被锁定挂起，则抛出异常 */
    private boolean lockSuspend = false;
    
    private CommandRequestSessionContext(CommandRequest request, CommandResponse response) {
        super();
        Assert.notNull(request, "request is null.");
        
        this.request = request;
        this.response = response;
        this.attributes = new HashMap<String, Object>();
        this.lockSuspend = false;
    }
    
    /**
     * 从容器中获取值
     * 
     * @param key
     * 
     * @return Object [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public Object getAttribute(String key) {
        if (this.attributes == null) {
            return null;
        }
        Object value = this.attributes.get(key);
        return value;
    }
    
    public Map<String, Object> getAttributes() {
        return attributes;
    }
    
    public CommandRequest getCommandRequest() {
        return request;
    }
    
    public CommandResponse getCommandResponse() {
        return response;
    }
    
    public boolean isLockSuspend() {
        return lockSuspend;
    }
    
    /**
     * 向容器中写入值
     * 
     * @param key
     * @param value [参数说明]
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    public void setAttribute(String key, Object value) {
        if (this.attributes == null) {
            return;
        }
        this.attributes.put(key, value);
    }
    
    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
    
    public void setCommandRequest(CommandRequest request) {
        this.request = request;
    }
    
    public void setCommandResponse(CommandResponse response) {
        this.response = response;
    }
    
    public void setLockSuspend(boolean lockSuspend) {
        this.lockSuspend = lockSuspend;
    }
    
    /**
     * 将当前操作回话容器清空
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    private void clear() {
        this.request = null;
        this.response = null;
        this.attributes = null;
    }
}
