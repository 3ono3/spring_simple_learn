package com.test.context;

import com.test.HelloWord;
import com.tiny.ioc.context.AbstractContext;
import com.tiny.ioc.context.XmlContext;
import org.junit.Test;

public class ContextBeanTest {
    @Test
    public void test() {
        AbstractContext context = new XmlContext("tinyIoc.xml");
        HelloWord helloWord = (HelloWord) context.getBean("helloWord");
        helloWord.say();
        System.out.println(helloWord.getUpup().getText());
    }
}
