package com.jing.tinyspring.reader;

import com.jing.tinyspring.bean.BeanDefinition;
import com.jing.tinyspring.resource.ResourceLoader;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBeanReader implements BeanReader {

    private Map<String, BeanDefinition> registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanReader(ResourceLoader resourceLoader) {
        this.registry = new HashMap<>();
        this.resourceLoader = resourceLoader;
    }

    public Map<String, BeanDefinition> getRegistry() {
        return registry;
    }

    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
