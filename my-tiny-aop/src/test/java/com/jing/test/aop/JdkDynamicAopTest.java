package com.jing.test.aop;

import com.jing.test.HelloWorldService;
import com.jing.tinyspring.aop.AdvisedSupport;
import com.jing.tinyspring.aop.JdkDynamicAopProxy;
import com.jing.tinyspring.aop.TargetSource;
import com.jing.tinyspring.context.XmlApplicationContext;
import org.junit.Test;

import java.lang.reflect.Proxy;

public class JdkDynamicAopTest {
    @Test
    public void test() throws Exception {
        XmlApplicationContext xmlApplicationContext = new XmlApplicationContext("tinyioc.xml");
        HelloWorldService helloWorldService = (HelloWorldService) xmlApplicationContext.getBean("helloWorldService");

        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new TimeMethodInterceptor());
        TargetSource targetSource = new TargetSource(HelloWorldService.class, helloWorldService);
        advisedSupport.setTargetSource(targetSource);

        JdkDynamicAopProxy aopProxy = new JdkDynamicAopProxy(advisedSupport);
        HelloWorldService helloWorldServiceProxy = (HelloWorldService) aopProxy.getProxy();
        helloWorldServiceProxy.helloWorld();

    }
}
