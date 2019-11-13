package com.tiny.ioc.context;

import com.tiny.ioc.beanFactory.AbstractBeanFactory;
import com.tiny.ioc.beanFactory.BeanDefinition;
import com.tiny.ioc.beanFactory.BeanFactory;
import com.tiny.ioc.resource.AbstractResourceLoad;
import com.tiny.ioc.resource.XmlResourceLoad;

import javax.xml.XMLConstants;
import java.util.Map;

public class XmlContext extends AbstractContext {
    private String xmlLocation;

    public XmlContext(String xmlLocation) {
        this(xmlLocation, BeanFactory.getInstance());
    }

    public XmlContext(String xmlLocation,AbstractBeanFactory beanFactory) {
        super(beanFactory);
        this.xmlLocation = xmlLocation;
        this.registerBeanFactory();
    }

    private void registerBeanFactory() {
        AbstractResourceLoad resourceLoad = new XmlResourceLoad();
        Map<String, BeanDefinition> resourceBeanDefinitionMap = null;
        try {
            resourceLoad.registerFactoryMap(xmlLocation);
            resourceBeanDefinitionMap = resourceLoad.getBeanMap();
            for(Map.Entry<String, BeanDefinition> beanDefinitionEntry : resourceBeanDefinitionMap.entrySet()) {
                abstractBeanFactory.setBeanMap(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
