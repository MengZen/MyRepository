package cn.com.meng.base.string;

/**
 * 字符串工具类
 * @author meng
 *
 */
public class StringUtil {

	/**
	 * 判断字符串是否为空
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return value == null || value.trim().length() == 0;
	}
}
