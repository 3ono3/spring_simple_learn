package com.tiny.ioc.resource;

import com.tiny.ioc.beanFactory.BeanReference;
import org.springframework.context.annotation.Bean;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class XmlUtil {

    public InputStream load(String location) throws IOException {
        URL url = this.getClass().getClassLoader().getResource(location);

        URLConnection urlConnection = url.openConnection();
        urlConnection.connect();
        return urlConnection.getInputStream();
    }

    protected List<XmlElement> doLoadBeanDefinitions(InputStream inputStream) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = factory.newDocumentBuilder();
        Document doc = docBuilder.parse(inputStream);
        // 解析bean
        List<XmlElement> result = registerBeanDefinitions(doc);
        inputStream.close();
        return result;
    }

    public List<XmlElement> registerBeanDefinitions(Document doc) {
        Element root = doc.getDocumentElement();

        return parseBeanDefinitions(root);
    }

    public List<XmlElement> parseBeanDefinitions(Element root) {
        List<XmlElement> xmlElements = new LinkedList<>();

        NodeList nl = root.getChildNodes();
        for (int i = 0; i < nl.getLength(); i++) {
            Node node = nl.item(i);
            if (node instanceof Element) {
                Element ele = (Element) node;
                xmlElements.add(processProperty(ele));
            }
        }

        return xmlElements;
    }


    private XmlElement processProperty(Element ele) {
        String className = ele.getAttribute("name");
        String classValue = ele.getAttribute("class");
        Map<String, Object> properties = new HashMap<>();

        NodeList propertyNode = ele.getElementsByTagName("property");
        for (int i = 0; i < propertyNode.getLength(); i++) {
            Node node = propertyNode.item(i);
            if (node instanceof Element) {
                Element propertyEle = (Element) node;
                String name = propertyEle.getAttribute("name");
                String value = propertyEle.getAttribute("value");
                if (value != null && value.length() > 0) {
                    properties.put(name, value);
                } else {
                    String ref = propertyEle.getAttribute("ref");
                    if (ref == null || ref.length() == 0) {
                        throw new IllegalArgumentException("Configuration problem: <property> element for property '"
                                + name + "' must specify a ref or value");
                    }
                    BeanReference beanReference = new BeanReference(ref);
                    properties.put(name, beanReference);
                }
            }
        }

        XmlElement xmlElement = new XmlElement();
        xmlElement.setName(className);
        xmlElement.setClassValue(classValue);
        xmlElement.setProperties(properties);

        return xmlElement;
    }
}
