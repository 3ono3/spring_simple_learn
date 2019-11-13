package com.tiny.ioc.beanFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory extends AbstractBeanFactory{
    //设为单例，保证beanMap唯一性
    private static volatile BeanFactory beanFactory;
    private BeanFactory() {}
    public static BeanFactory getInstance() {
        if (beanFactory == null) {
            synchronized (BeanFactory.class) {
                if (beanFactory == null) {
                    beanFactory = new BeanFactory();
                }
            }
        }
        return beanFactory;
    }

    @Override
    public void setBeanMap(String key, BeanDefinition beanDefinition) {
        createBean(beanDefinition);
        beanMap.put(key, beanDefinition);
    }

    @Override
    public Object getBean(String key) {
        BeanDefinition beanDefinition = beanMap.get(key);
        return beanDefinition.getBean();
    }

    private void createBean(BeanDefinition beanDefinition) {
        String fullName = beanDefinition.getFullName();
        try {
            Class objectClass = Class.forName(fullName);
            beanDefinition.setaClass(objectClass);
            try {
                Object bean = objectClass.newInstance();
                beanDefinition.setBean(bean);
                setBeanField(beanDefinition);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void setBeanField(BeanDefinition beanDefinition) throws NoSuchFieldException, IllegalAccessException {
        Object bean = beanDefinition.getBean();
        Class aClass = beanDefinition.getaClass();
        Map<String, Object> properties = beanDefinition.getProperties();
        for (Map.Entry<String, Object> property : properties.entrySet()) {
            String name = property.getKey();
            Field field = bean.getClass().getDeclaredField(name);
            field.setAccessible(true);
            Object value = property.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                String refBeanName = beanReference.getName();
                value = this.beanMap.get(refBeanName).getBean();
            }
            field.set(bean, value);
        }
    }
}
