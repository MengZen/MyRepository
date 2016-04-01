package cn.com.meng.base.convert.number;

import java.util.UUID;

/**
 * UUID工具类
 * 
 * @author meng
 *
 */
public class UUIDUtil {

	/**
	 * 生成全球唯一的ID
	 * 
	 * @return
	 */
	public static String createUUID() {
		return UUID.randomUUID().toString();
	}

	public static void main(String[] args) {
		System.out.println(UUIDUtil.createUUID());
	}
}
