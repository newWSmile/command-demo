package com.example.demo.commond.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Smile(wangyajun)
 * @create 2018-09-13 20:41
 **/
public class CommandContextConfigurator implements ApplicationContextAware, InitializingBean {
    /** 日志 */
    protected Logger logger = LoggerFactory.getLogger(CommandContext.class);

    /** spring容器 */
    protected static ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CommandContextConfigurator.applicationContext = applicationContext;
    }
}
