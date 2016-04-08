package cn.com.meng.base.string;

import java.io.IOException;
import java.io.StringWriter;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import cn.com.meng.base.example.bean.User;
import cn.com.meng.base.reflect.ReflectUtil;

/**
 * json工具类
 * @author meng
 *
 */
public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();
	private static XmlMapper xmlMapper = new XmlMapper();

	/**
	 * json到List<Map>
	 * 
	 * @param jsonStr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> json2ListMap(String jsonStr) {
		try {
			return objectMapper.readValue(jsonStr, List.class);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * string到json
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static JsonNode str2Json(String jsonStr) {
		try { 
			return objectMapper.readTree(jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * json字符串转bean
	 * @param json
	 * @param classOfT
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> classOfT) {
        try {
            json = URLDecoder.decode(json, "UTF-8");
            return objectMapper.readValue(json, classOfT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	/**
	 * object 转json字符串
	 * @param obj
	 * @return
	 */
    public static String toJson(Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 依赖于stax2-api.jar，java对象到xml的转换
     * @param obj
     * @return
     */
    public static String bean2Xml(Object obj){
    	StringWriter stringWriter = new StringWriter();
    	try {
			xmlMapper.writeValue(stringWriter, obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return stringWriter.toString();
    }
    
    public static void main(String[] args){
		String json = "{\"name\":\"小明\",\"age\":\"13\"}";
		User user = JsonUtil.fromJson(json, User.class);
		ReflectUtil.printBean(user);
		
		String xml = bean2Xml(user);
		System.out.println(xml);
	}
}
