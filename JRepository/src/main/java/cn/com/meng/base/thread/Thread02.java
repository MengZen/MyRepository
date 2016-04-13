package cn.com.meng.base.thread;

public class Thread02 implements Runnable {

	// 共享唯一资源
	private static Object resource = new Object();

	private static int count = 0;

	@Override
	public void run() {
		// 锁定，使同一时间只有一个线程访问，直到运行完毕
		synchronized (resource) {
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread() + ":" + count++);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new Thread02());
		Thread t2 = new Thread(new Thread02());
		t1.start();
		t2.start();
	}

}
