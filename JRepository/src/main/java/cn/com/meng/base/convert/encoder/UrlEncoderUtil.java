package cn.com.meng.base.convert.encoder;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * URL编码解码工具
 * @author meng
 *
 */
public class UrlEncoderUtil {

	/**
	 * 编码
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String text) throws UnsupportedEncodingException{
		return URLEncoder.encode(text,"utf-8");
	}
	
	/**
	 * 解码
	 * @param text
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode(String text) throws UnsupportedEncodingException{
		return URLDecoder.decode(text,"utf-8");
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(UrlEncoderUtil.decode(UrlEncoderUtil.encode("_ 中国&?")));
	}
}
