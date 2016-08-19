package cn.com.meng.base.reflect;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import cn.com.meng.base.example.bean.User;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuhanliang on 16/4/18.
 */
public class BeanMapUtil {
	
	/**
	 * 将map转化为bean
	 * @param map
	 * @param beanClass
	 * @return
	 * @throws Exception
	 */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();
        ConvertUtils.register(new Converter()
        {
            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(Class arg0, Object arg1)
            {
                if(arg1 == null)
                {
                    return null;
                }
                if(!(arg1 instanceof String))
                {
                    throw new ConversionException("只支持字符串转换 !");
                }
                String str = (String)arg1;
                if(str.trim().equals(""))
                {
                    return null;
                }

                SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                try{
                    return sd.parse(str);
                }
                catch(ParseException e)
                {
                    throw new RuntimeException(e);
                }

            }

        }, java.util.Date.class);

        try {
            BeanUtils.populate(obj, map);
        } catch (Exception e){
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 将bean转化为map
     * @param obj
     * @return
     */
    public static Map<?, ?> objectToMap(Object obj) {
        if(obj == null)
            return null;

        return new org.apache.commons.beanutils.BeanMap(obj);
    }
    
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("age",12);
		map.put("name","刘诗诗");
		User user = new User();
		BeanUtils.populate(user,map);
		ReflectUtil.printBean(user);
		
		System.out.println(new BeanMap(user));
	}
}
