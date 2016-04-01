package cn.com.meng.base.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 通过配置文件获取常量值
 * @author meng
 *
 */
public class Constants {

	//数据库连接字符串
	public static String jdbcUrl;
	//驱动类
	public static String jdbcDriver;
	//用户名
	public static String username;
	//密码
	public static String password;

	static {
		Properties prop = new Properties();
		InputStream in = null;
		try {
			in = Object.class.getResourceAsStream("/jdbc.properties");
			prop.load(in);
			jdbcUrl = prop.getProperty("jdbc.url").trim();
			jdbcDriver = prop.getProperty("jdbc.driver").trim();
			username = prop.getProperty("jdbc.username").trim();
			password = prop.getProperty("jdbc.password").trim();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(null != in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
