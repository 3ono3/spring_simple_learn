package com.tiny.ioc.resource;

import com.tiny.ioc.beanFactory.BeanDefinition;
import com.tiny.ioc.beanFactory.BeanFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlResourceLoad extends AbstractResourceLoad {

    private XmlUtil xmlUtil = new XmlUtil();

    @Override
    public void registerFactoryMap(String location) throws Exception {
//        BeanFactory beanFactory = BeanFactory.getInstance();
        List<XmlElement> xmlElements = xmlUtil.doLoadBeanDefinitions(xmlUtil.load(location));
        for (XmlElement xmlElement : xmlElements) {
            String beanName = xmlElement.getName();
            BeanDefinition beanDefinition = new BeanDefinition(xmlElement.getClassValue(), xmlElement.getProperties());
            beanMap.put(beanName, beanDefinition);
        }
    }

}
