package cn.com.meng.base.convert.number;

import java.util.Date;
import java.util.UUID;
import java.util.zip.CRC32;

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

	/**
	 * 将uuid字符串转换成数字
	 * @param uuid字符串
	 * @return
	 */
	public static long convertNumber(String uuid){
		CRC32 crc32 = new CRC32();
		crc32.update(uuid.getBytes());
		return crc32.getValue();
	}
	
	public static void main(String[] args) {
		String uuid = UUIDUtil.createUUID();
		int d = (int)convertNumber(uuid);
		if(d < 0){
			d = -1 - d;
		}
		System.out.println(d + ":" + (-1-(-2147483648)));
	}
}
