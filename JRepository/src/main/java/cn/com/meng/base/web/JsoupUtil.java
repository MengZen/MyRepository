package cn.com.meng.base.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;

/**
 * 实现http请求数据获取
 * 
 * @author meng
 *
 */
public class JsoupUtil {

	/**
	 * post方法
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return 响应的内容
	 */
	public String post(String url, Map<String, String> params) {
		Connection conn = Jsoup.connect(url);
		conn.data(params);
		// 切换成post方法，默认是get
		conn.method(Method.POST);
		// 设置超时时间
		conn.timeout(10000);
		try {
			// return conn.post().html();返回html页面对象
			return conn.execute().body();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * get方法
	 * 
	 * @param url
	 *            请求地址
	 * @return 响应的内容
	 */
	public String get(String url) {
		Connection conn = Jsoup.connect(url);
		conn.timeout(10000);
		try {
			return conn.execute().body();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", "440103001024201510092");
		JsoupUtil jsoupUtil = new JsoupUtil();
		String ret = jsoupUtil.post("http://59.42.21.102:8083/plan/inter/studentwork", params);
		System.out.println(ret);
	}
}
