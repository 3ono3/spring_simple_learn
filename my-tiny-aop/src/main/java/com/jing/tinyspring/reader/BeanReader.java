package com.jing.tinyspring.reader;

/**
 * 从配置中读取BeanDefinition
 */
public interface BeanReader {
    void loadBeanDefinitions(String location) throws Exception ;
}
