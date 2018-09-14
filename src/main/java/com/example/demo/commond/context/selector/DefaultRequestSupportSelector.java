/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月19日
 * 项目： rainhy-webcore
 */
package com.example.demo.commond.context.selector;

import com.example.demo.commond.context.CommandRequest;
import com.example.demo.commond.context.CommandRequestSupportSelector;
import com.example.demo.commond.context.CommandSupportType;

/**
 * 请求支撑类选择器默认实现
 * 
 * @author smile
 * @version [版本号, 2018年9月13日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DefaultRequestSupportSelector implements CommandRequestSupportSelector {
    
    @Override
    public CommandSupportType select(CommandRequest request) {
        return request.getRequestSupportType();
    }
}
