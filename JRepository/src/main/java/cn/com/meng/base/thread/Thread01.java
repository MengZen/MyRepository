package cn.com.meng.base.thread;

/**
 * 通过继承thread类实现
 * @author meng
 *
 */
public class Thread01 extends Thread{

	/**
	 * 重写run方法，处理业务
	 */
	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("这是子线程");
	}
	
	public static void main(String[] args) throws InterruptedException {
		Thread01 t1 = new Thread01();
		//t1.setDaemon(true);//将该线程变成守护进程，后台运行，如果所有线程都变成守护线程，主线程会exit，守护线程继续运行
		t1.start();
		//t1.join();//等待t1执行完毕,join方法能人t1加入主线程main里，相当于合并两个线程，需要等t1执行完run后main才能往下继续执行
		System.out.println("这是主线程");
	}
}
