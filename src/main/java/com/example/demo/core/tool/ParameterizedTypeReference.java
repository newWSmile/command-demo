/*
 * 描述： <描述>
 * 修改人： rain
 * 修改时间： 2017年1月24日
 * 项目： tkm-core
 */
package com.example.demo.core.tool;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型类引用
 *
 * @author rain
 * @version [版本号, 2017年1月24日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class ParameterizedTypeReference<T> {
    
    private final Type type;
    
    protected ParameterizedTypeReference() {
        this.type = getClassRawType(this.getClass());
    }
    
    @Override
    public boolean equals(Object obj) {
        return ((this == obj) || ((obj instanceof ParameterizedTypeReference) &&
                this.type.equals(((ParameterizedTypeReference<?>) obj).type)));
    }
    
    @SuppressWarnings("unchecked")
    public Class<T> getRawClass() {
        return (Class<T>) this.type;
    }
    
    public Type getRawType() {
        return this.type;
    }
    
    @Override
    public int hashCode() {
        return this.type.hashCode();
    }
    
    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "<" + this.type + ">";
    }
    
    private Type getClassRawType(@SuppressWarnings("rawtypes") Class objectClass) {
        Type rawTypeTemp = objectClass.getGenericSuperclass();
        if (rawTypeTemp instanceof ParameterizedType) {
            rawTypeTemp = ((ParameterizedType) rawTypeTemp).getActualTypeArguments()[0];
            if (rawTypeTemp instanceof ParameterizedType) {
                rawTypeTemp = ((ParameterizedType) rawTypeTemp).getRawType();
            }
        } else {
            Class<?> superClass = objectClass.getSuperclass();
            rawTypeTemp = getClassRawType(superClass);
        }
        return rawTypeTemp;
    }
    
}
