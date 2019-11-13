package com.test;

import com.tiny.ioc.beanFactory.BeanDefinition;
import com.tiny.ioc.beanFactory.BeanFactory;
import com.tiny.ioc.resource.AbstractResourceLoad;
import com.tiny.ioc.resource.XmlResourceLoad;
import org.junit.Test;

import java.util.Map;

public class IocBeanTest {

    @Test
    public void test() throws Exception {
        AbstractResourceLoad xmlResourceLoad = new XmlResourceLoad();
        xmlResourceLoad.registerFactoryMap("tinyIoc.xml");
        Map<String, BeanDefinition> xmlMap = xmlResourceLoad.getBeanMap();

        BeanFactory beanFactory = BeanFactory.getInstance();
        for(Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlMap.entrySet()) {
            beanFactory.setBeanMap(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        HelloWord helloWord = (HelloWord) beanFactory.getBean("helloWord");
        helloWord.say();
        System.out.println(helloWord.getUpup().getText());
    }
}
