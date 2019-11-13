package com.tiny.ioc.context;

import com.tiny.ioc.beanFactory.AbstractBeanFactory;

public abstract class AbstractContext {
    public AbstractBeanFactory abstractBeanFactory;

    public AbstractContext(AbstractBeanFactory beanFactory) {
        this.abstractBeanFactory = beanFactory;
    }

    public Object getBean(String beanName) {
        return abstractBeanFactory.getBean(beanName);
    }

}
