package com.jing.tinyspring.context;

import com.jing.tinyspring.factory.AbstructBeanFactory;

public abstract class AbstractApplicationContext implements ApplicationContext{
     protected AbstructBeanFactory beanFactory;
     public AbstractApplicationContext(AbstructBeanFactory beanFactory) {
         this.beanFactory = beanFactory;
     }

     public void refresh() throws Exception{
     }

     @Override
     public Object getBean(String name) throws Exception{
         return beanFactory.getBean(name);
     }
}
