/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月15日
 * 项目： rainhy-webcore
 */
package com.example.demo.commond.context;

import org.springframework.core.Ordered;

/**
 * 请求支撑类选择器
 * 
 * @author smile
 * @version [版本号, 2018年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface CommandRequestSupportSelector extends Ordered {
    
    /**
     * 根据命令请求器 选择一个合适的命令支撑类型<br>
     * 如果返回null，则表明适配所有的支撑类型，会随机取一个合适的支撑器调用
     *
     * @param request 的Class类型
     * 
     * @return CommandSupportType [返回类型说明]
     * @throws [异常类型] [异常说明]
     * @see [类、类#方法、类#成员]
     * @version [版本号, 2018年9月13日]
     * @author smile
     */
    CommandSupportType select(CommandRequest request);
    
    @Override
    default int getOrder() {
        return 0;
    }
    
}
