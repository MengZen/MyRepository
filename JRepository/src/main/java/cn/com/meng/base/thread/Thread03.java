package cn.com.meng.base.thread;

/**
 * 共享资源，同步（等待唤醒），模拟银行取钱和存钱，保证money的一致性
 * 
 * @author Administrator
 *
 */
public class Thread03 {
	public static void main(String[] args) {
		Thread t1 = new Thread(new GetMoney(), "GetMoney");
		Thread t2 = new Thread(new PutMoney(), "PutMoney");
		t1.start();
		t2.start();
	}
}
