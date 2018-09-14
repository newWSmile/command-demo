/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2018年3月15日
 * 项目： rainhy-webcore
 */
package com.example.demo.commond.context.response;

import com.example.demo.commond.CommandException;
import com.example.demo.commond.context.CommandResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认的返回实例
 * 
 * @author rain
 * @version [版本号, 2018年3月15日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DefaultResponse implements CommandResponse {
    
    private Map<String, Object> attribute = new HashMap<>();
    
    public Object getAttribute(String key) {
        return this.attribute.get(key);
    }
    
        @SuppressWarnings("unchecked")
    public <T> T getAttribute(String key, Class<T> clazz) {
        Object object = this.attribute.get(key);
        if (object == null) {
            return null;
        }
        try {
            return (T) object;
        } catch (Exception e) {
            throw new CommandException(e, "不能把 {} 转换成 {} ", clazz, object.getClass());
        }
    }
    
    public Map<String, Object> getAttributes() {
        return this.attribute;
    }
    
    public void setAttribute(String key, Object value) {
        this.attribute.put(key, value);
    }
    
    public void setAttributeAll(Map<String, Object> attributeMap) {
        this.attribute.putAll(attributeMap);
    }
    
}
