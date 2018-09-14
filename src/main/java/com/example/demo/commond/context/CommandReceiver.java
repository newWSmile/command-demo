/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月14日
 * 项目： rainhy-webcore
 */
package com.example.demo.commond.context;

import org.springframework.core.Ordered;

/**
 * 命令接收处理器
 * 
 * @author smile
 * @version [版本号, 2018年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface CommandReceiver<REQ extends CommandRequest> extends Ordered {
    
    /**
     * 处理完成后的前置处理<br/>
     * 
     * @param request 操作请求器
     * @param response 操作返回对象
     * @param exception 抛出的异常，如果存在异常
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    default void doAfterCompletion(REQ request, CommandResponse response, Exception exception) {
        
    }
    
    /**
     * 处理完成后的后置处理<br/>
     * 在该逻辑中触发相关事件<br/>
     * 
     * @param request 操作请求器
     * @param response 操作返回对象
     * 
     * @return void [返回类型说明]
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    default void doBeforeCompletion(REQ request, CommandResponse response) {
        
    }
    
    /***
     * 
     * 处理命令，会话开启前处理<br>
     *
     * @param request
     * @param response
     * @return
     * 
     * @return boolean [返回类型说明]
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2018年3月15日]
     * @author rain
     */
    default boolean doAfterOpenSession(REQ request, CommandResponse response) {
        return true;
    }
    
    /***
     * 
     * 处理命令，会话开启后处理<br>
     *
     * @param request
     * @param response
     * @return
     * 
     * @return boolean [返回类型说明]
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2018年3月15日]
     * @author rain
     */
    default boolean doBeforeOpenSession(REQ request, CommandResponse response) {
        return true;
    }
    
    /** 排序 */
    @Override
    default int getOrder() {
        return 0;
    }
    
    /**
     * 获取支持的操作请求类型<br/>
     * 
     * @return Class<?> 请求类型
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    Class<REQ> getRequestType();
    
    /**
     * 支持的类型<br>
     * 
     * @return SupportType 支持的类型
     * @exception throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     */
    boolean isSupportTypes(CommandSupportType supportType);
}
