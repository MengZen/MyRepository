package cn.com.meng.base.thread;

/**
 * 共享资源
 * 
 * @author Administrator
 *
 */
public class Bank {
	//银行存款
	public static Integer money = 100;
	//存款状态，true：取款中，false:没在取款
	public static Boolean status = false;
}
