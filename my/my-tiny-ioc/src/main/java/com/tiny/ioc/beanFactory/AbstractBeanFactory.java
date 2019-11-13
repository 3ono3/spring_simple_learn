package com.tiny.ioc.beanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBeanFactory {
    protected Map<String, BeanDefinition> beanMap = new ConcurrentHashMap<>();

    abstract public Object getBean(String beanName);

    public void setBeanMap(String key, BeanDefinition beanDefinition) {
        beanMap.put(key, beanDefinition);
    }
}
