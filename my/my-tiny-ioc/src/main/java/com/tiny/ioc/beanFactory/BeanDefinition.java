package com.tiny.ioc.beanFactory;

import java.util.Map;

public class BeanDefinition {
    /**
     * 实例
     */
    private Object bean;
    /**
     * 全限定名
     */
    private String fullName;
    /**
     * 类
     */
    private Class aClass;
    /**
     * 属性
     */
    private Map<String, Object> properties;

    public BeanDefinition(String fullName, Map<String, Object> properties) {
        this.fullName = fullName;
        this.properties = properties;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
