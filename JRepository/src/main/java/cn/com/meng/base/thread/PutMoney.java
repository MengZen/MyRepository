package cn.com.meng.base.thread;

public class PutMoney implements Runnable {

	public void run() {
		int n = 0;
		while (!Thread.currentThread().isInterrupted() && n++ < 20) {
			synchronized (Bank.class) {
				if (Bank.money >= 10 && Bank.status) {
					try {
						Bank.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					Bank.money += 50;
					System.out.println(n + "：" + Thread.currentThread().getName() + "; money=" + Bank.money);
					Bank.class.notify();
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
