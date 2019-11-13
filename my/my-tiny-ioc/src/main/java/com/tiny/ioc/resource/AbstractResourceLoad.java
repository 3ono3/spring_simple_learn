package com.tiny.ioc.resource;

import com.tiny.ioc.beanFactory.BeanDefinition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractResourceLoad {
    protected Map<String, BeanDefinition> beanMap = new ConcurrentHashMap<>();

    abstract public void registerFactoryMap(String location) throws Exception;

    public Map<String, BeanDefinition> getBeanMap() {
        return beanMap;
    }
}
