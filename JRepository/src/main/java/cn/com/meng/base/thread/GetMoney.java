package cn.com.meng.base.thread;

public class GetMoney implements Runnable {

	public void run() {
		int n = 0;
		//取钱中
		Bank.status = true;
		while (!Thread.currentThread().isInterrupted() && n++ < 20) {
			synchronized (Bank.class) {
				if (Bank.money < 10) {
					try {
						Bank.class.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					Bank.money -= 10;
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
		//终止取钱
		Bank.status = false;
	}

}
