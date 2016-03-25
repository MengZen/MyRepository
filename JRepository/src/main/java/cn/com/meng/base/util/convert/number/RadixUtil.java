package cn.com.meng.base.util.convert.number;

/**
 * 提供一系列进制转换工具
 * @author meng
 *
 */
public class RadixUtil {

	/**
	 * 十进制转成八进制
	 * @param n 十进制整数
	 * @return 十六进制字符串
	 */
	public static String decimal2HexString(int n){
		return Integer.toHexString(n);
	}
	
	/**
	 * 十进制转成十六进制
	 * @param n 十进制整数
	 * @return 八进制字符串
	 */
	public static String decimal2OctalString(int n){
		return Integer.toOctalString(n);
	}
	
	/**
	 * 十进制转成二进制
	 * @param n 十进制整数
	 * @return 二进制字符串
	 */
	public static String decimal2BinaryString(int n){
		return Integer.toBinaryString(n);
	}
	
	/**
	 * 十六进制转成十进制
	 * @param n 十六进制字符串
	 * @return 十进制整数
	 */
	public static int hexString2Decimal(String hexString){
		return Integer.valueOf(hexString,16);
	}
	
	/**
	 * 八进制转成十进制
	 * @param n 八进制字符串
	 * @return 十进制整数
	 */
	public static int octalString2Decimal(String octalString){
		return Integer.valueOf(octalString,8);
	}
	
	/**
	 * 二进制转成十进制
	 * @param n 二进制字符串
	 * @return 十进制整数
	 */
	public static int binaryString2Decimal(String binaryString){
		return Integer.valueOf(binaryString,2);
	}
	
	public static void main(String[] args) {
		System.out.println(RadixUtil.hexString2Decimal("34"));
	}
}
