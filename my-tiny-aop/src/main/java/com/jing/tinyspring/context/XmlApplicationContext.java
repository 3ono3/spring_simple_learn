package com.jing.tinyspring.context;

import com.jing.tinyspring.bean.BeanDefinition;
import com.jing.tinyspring.factory.AbstructBeanFactory;
import com.jing.tinyspring.factory.AutowireCapableBeanFactory;
import com.jing.tinyspring.reader.XmlBeanReader;
import com.jing.tinyspring.resource.ResourceLoader;

import java.util.Map;

public class XmlApplicationContext extends AbstractApplicationContext{
    private String configLocation;

    public XmlApplicationContext(String configLocation) throws Exception {
        this(configLocation, new AutowireCapableBeanFactory());
    }
    public XmlApplicationContext(String configLocation, AbstructBeanFactory beanFactory) throws Exception {
        super(beanFactory);
        this.configLocation = configLocation;
        refresh();
    }

    @Override
    public void refresh() throws Exception{
        XmlBeanReader xmlBeanReader = new XmlBeanReader(new ResourceLoader());
        xmlBeanReader.loadBeanDefinitions(configLocation);
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }
    }
}
