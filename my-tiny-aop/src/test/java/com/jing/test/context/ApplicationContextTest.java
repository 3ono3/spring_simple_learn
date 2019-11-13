package com.jing.test.context;

import com.jing.test.HelloWorldService;
import com.jing.tinyspring.context.XmlApplicationContext;
import org.junit.Test;

public class ApplicationContextTest {
    @Test
    public void test() throws Exception {
        XmlApplicationContext xmlApplicationContext = new XmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) xmlApplicationContext.getBean("helloWorldService");
        helloWorldService.helloWorld();
    }
}
