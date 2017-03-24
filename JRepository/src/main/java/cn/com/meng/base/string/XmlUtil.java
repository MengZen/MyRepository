package cn.com.meng.base.string;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import cn.com.meng.base.reflect.ReflectUtil;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * xml工具类
 *
 * @author meng
 */
public class XmlUtil {

    /**
     * 读取XML文件,获得document对象
     *
     * @param xmlFile
     * @return
     */
    public static Document parseXmlFile(File xmlFile) {
        SAXReader reader = new SAXReader();
        Document document = null;
        try {
            document = reader.read(xmlFile);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 解析XML形式的文本,得到document对象
     *
     * @param xmlStr
     * @return
     */
    public static Document parseXmlString(String xmlStr) {
        Document document = null;
        try {
            document = DocumentHelper.parseText(xmlStr);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 主动创建document对象
     *
     * @return
     */
    public static Document creatXml() {
        Document document;
        document = DocumentHelper.createDocument();
        return document;
    }

    /**
     * 将xml字符串转换成map对象，适用于两层结构的xml
     *
     * @param xml
     * @return
     */
    public static Map<String, Object> xml2Map(String xml) {
        Document dom = parseXmlString(xml);
        Element root = dom.getRootElement();
        return Element2Map(root);
    }

    /**
     * Node节点对象转换成map对象，适用于两层结构的xml
     *
     * @param objNode
     * @return
     */
    public static Map<String, Object> node2Map(Node objNode) {
        Element root = (Element) objNode;
        return Element2Map(root);
    }

    /**
     * Element元素对象转换成map对象，适用于两层结构的xml
     *
     * @param root
     * @return
     */
    public static Map<String, Object> Element2Map(Element root) {
        Map<String, Object> map = new HashMap<>();
        List<Element> children = root.elements();
        for (Element e : children) {
            map.put(e.getName(), e.getData());
        }
        return map;
    }

    /**
     * 将xml字符串借Map对象通过反射机制转换成Bean对象 ，使用于属性是非集合类型的bean
     *
     * @param xml xml字符串
     * @param clz 待转换的class
     * @return 转换后的Bean对象
     */
    public static <T> T xml2Bean(String xml, Class<T> clz) {
        Map<String, Object> map = xml2Map(xml);
        Set<String> keys = map.keySet();
        T object = ReflectUtil.newInstance(clz);
        try {
            for (String propertyName : keys) {
                String methodName = "set" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
                Field field = getClassField(clz, propertyName);
                Object value = convertValType(map.get(propertyName), field.getType());

                clz.getMethod(methodName, field.getType()).invoke(object, value);
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
                | SecurityException e) {
            e.printStackTrace();
        }
        return object;
    }

    /**
     * 将Object类型的值，转换成bean对象属性里对应的类型值
     *
     * @param value          Object对象值
     * @param fieldTypeClass 属性的类型
     * @return 转换后的值
     */
    private static Object convertValType(Object value, Class<?> fieldTypeClass) {
        Object retVal;
        if (Long.class == fieldTypeClass || long.class == fieldTypeClass) {
            retVal = Long.parseLong(value.toString());
        } else if (Integer.class == fieldTypeClass || int.class == fieldTypeClass) {
            retVal = Integer.parseInt(value.toString());
        } else if (Float.class == fieldTypeClass || float.class == fieldTypeClass) {
            retVal = Float.parseFloat(value.toString());
        } else if (Double.class == fieldTypeClass || double.class == fieldTypeClass) {
            retVal = Double.parseDouble(value.toString());
        } else {
            retVal = value;
        }
        return retVal;
    }

    /**
     * 获取指定字段名称查找在class中的对应的Field对象(包括查找父类)
     *
     * @param clazz     指定的class
     * @param fieldName 字段名称
     * @return Field对象
     */
    private static Field getClassField(Class<?> clazz, String fieldName) {
        if (Object.class.getName().equals(clazz.getName())) {
            return null;
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null) {// 简单的递归一下
            return getClassField(superClass, fieldName);
        }
        return null;
    }

    public static void main(String[] args) {
        ReflectUtil.printBean(xml2Map("<?xml version=\"1.0\" encoding=\"UTF-8\"?><User xmlns=\"\"><name>小明</name><age>13</age></User>"));
        Document document = parseXmlFile(new File("/Users/meng/Downloads/aa.xml"));
        System.out.println(document.asXML());
        List<Node> list = document.selectNodes("//RESULT/ROW");
        for (Node o : list) {
            System.out.println(node2Map(o));
        }
    }
}
