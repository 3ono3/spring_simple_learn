package com.jing.tinyspring.factory;

public interface BeanFactory {
    Object getBean(String name) throws Exception;
}
