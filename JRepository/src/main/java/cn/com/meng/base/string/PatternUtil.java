package cn.com.meng.base.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式工具类
 * @author meng
 *
 */
public class PatternUtil {

	// 匹配中文
	public static final String Chinese = "[\u4e00-\u9fa5]+";
	// 匹配手机号码
	public static final String Mobile = "^(0|86)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$";
	// 匹配15位或18位身份证号
	public static final String Id = "(^\\d{15}$)|(^\\d{17}([0-9]|X)$)";
	//匹配Email
	public static final String Email = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
	//匹配IP
	public static final String Ip = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$";
	//匹配中国邮政编码
	public static final String Postcode = "[1-9]{1}(\\d+){5}";
	
	/**
	 * 验证字符串是否匹配指定模式
	 * 
	 * @param patternStr
	 *            需要匹配的模式
	 * @param srcStr
	 *            目标字符串
	 * @return
	 */
	public static boolean match(String patternStr, String srcStr) {
		return Pattern.matches(patternStr, srcStr);
	}

	/**
     * 判断目标字符串是否存在匹配指定的正则表达式的子字符串
     * @param patternStr 指定正则表达式
     * @param srcStr 目标字符串
     * @return true有 false无
     */
    public static boolean isIn(String patternStr,String srcStr){
        Pattern p = Pattern.compile(patternStr);
        Matcher m = p.matcher(srcStr);
        return m.find();
    }
    
	/**
	 * 跟进指定模式分裂字符串
	 * 
	 * @param patternStr
	 *            需要匹配的模式
	 * @param srcStr
	 *            String
	 * @return 分裂后的字符串组
	 */
	public static String[] split(String patternStr, String srcStr) {
		// patternStr ＝ "[年月日]"可以获取日期的年月日
		Pattern p = Pattern.compile(patternStr);
		return p.split(srcStr);
	}

	/**
	 * 将目标字符串里的字母全部大写
	 * 
	 * @param srcStr
	 *            目标字符串
	 * @return 已替换的字符串
	 */
	public static String toUpperCase(String srcStr) {
		// 不区分大小写
		Pattern p = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
		// 用Pattern类的matcher()方法生成一个Matcher对象
		Matcher m = p.matcher(srcStr);
		StringBuffer buf = new StringBuffer();

		// 使用find()方法查找第一个匹配的对象
		// 使用循环找出模式匹配的内容替换之,再将内容加到buf里
		while (m.find()) {
			m.appendReplacement(buf, m.group().toUpperCase());
		}
		// 最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里；
		m.appendTail(buf);
		return buf.toString();
	}

	/**
	 * 将目标字符串里的字母全部小写
	 * 
	 * @param srcStr
	 *            目标字符串
	 * @return 已替换的字符串
	 */
	public static String toLowerCase(String srcStr) {
		// Pattern p = Pattern.compile(patternStr,Pattern.CASE_INSENSITIVE)
		// 不区分大小写
		Pattern p = Pattern.compile("[a-z]+", Pattern.CASE_INSENSITIVE);
		// 用Pattern类的matcher()方法生成一个Matcher对象
		Matcher m = p.matcher(srcStr);
		StringBuffer buf = new StringBuffer();

		// 使用find()方法查找第一个匹配的对象
		// 使用循环找出模式匹配的内容替换之,再将内容加到buf里
		while (m.find()) {
			m.appendReplacement(buf, m.group().toLowerCase());
		}
		// 最后调用appendTail()方法将最后一次匹配后的剩余字符串加到sb里；
		m.appendTail(buf);
		return buf.toString();
	}

	public static void main(String[] args) {
		System.out.println("do.kk=ll.uu".replaceAll("(.*)=(.*)", "$1"));
		String str = "http://localhost:8180/survey/management/questionnaire/list/1";
		System.out.println(PatternUtil.isIn("/management/questionnaire/*", str));
		System.out.println(PatternUtil.match(PatternUtil.Email, "zhengdongxing@bmsoft.com.cn"));
	}
}
