/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月14日
 * 项目： rainhy-webcore
 */
package com.example.demo.commond.context;

import com.example.demo.commond.CommandException;

/**
 * 命令请求支撑器
 * 
 * @author smile
 * @version [版本号, 2018年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface CommandRequestSupport {
    
    /** 支撑的类型 */
    CommandSupportType supportType();
    
    /** 是否支持特定的请求器 */
    default boolean isSupportRequest(Class<? extends CommandRequest> requestClass) {
        return true;
    }
    
    /** 处理请求 */
    void handle(CommandRequest request, CommandResponse response) ;
}