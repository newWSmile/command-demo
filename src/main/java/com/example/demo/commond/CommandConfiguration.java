package com.example.demo.commond;

import com.example.demo.commond.context.CommandContext;
import com.example.demo.commond.context.CommandContextFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Smile(wangyajun)
 * @create 2018-09-13 20:40
 **/
@Configuration
@ComponentScan
public class CommandConfiguration {

    /** 实例化命令容器 */
    @Bean
    public CommandContext createCommandContext() {
        return new CommandContextFactory();
    }
}
