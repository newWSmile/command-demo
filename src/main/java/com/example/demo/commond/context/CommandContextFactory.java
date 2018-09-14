/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月14日
 * 项目： rainhy-webcore
 */
package com.example.demo.commond.context;

import org.springframework.beans.factory.FactoryBean;

/**
 * 命令容器工厂
 * 
 * @author smile
 * @version [版本号, 2018年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CommandContextFactory extends CommandContext implements FactoryBean<CommandContext> {
    
    @Override
    public CommandContext getObject() throws Exception {
        return this;
    }
    
    @Override
    public Class<?> getObjectType() {
        return CommandContext.class;
    }
    
    @Override
    public boolean isSingleton() {
        return true;
    }
    
}
