package Thread;
/*
 * Thread wait, notifyAll �����ϱ�
 * Cake����
 * 
 */
class CakePlate{
	private int breadCount =0;
	public synchronized void makeBread(){
		if (breadCount >= 10 ) {
			try {
				System.out.println("���� �����ϴ�.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		breadCount++;
		System.out.println("���� �Ѱ� �� �����. �� :" + breadCount + "��");
		this.notifyAll();// ��ٸ��� �ִ� ��� �����带 �����.
	}

	public synchronized void eatBread(){
		if (breadCount <1) {
			try {
				System.out.println("���� ���ڶ� ��ٸ���.");
				wait();
			} catch (InterruptedException e) {
			}
		}
		breadCount--;
		System.out.println("���� ���� �� :" + breadCount+"��");
		this.notifyAll();
	}
}

class CakeMaker extends Thread{
	private CakePlate cake;

	public CakePlate getCake() {
		return cake;
	}
	public void setCake(CakePlate cake) {
		this.cake = cake;
	}
	public CakeMaker(CakePlate cake) {
		setCake(cake);
	}
	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			cake.makeBread();
		}
	}
}

class CakeEater extends Thread{
	private CakePlate cake;

	public CakePlate getCake() {
		return cake;
	}

	public void setCake(CakePlate cake) {
		this.cake = cake;
	}

	public CakeEater(CakePlate cake) {
		setCake(cake);
	}
	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
			cake.eatBread();
		}
	}
}
public class CakeWaitNoifyAllTest {

	public static void main(String[] args) {
		CakePlate cp = new CakePlate();
		CakeEater ce = new CakeEater(cp);
		CakeMaker cm = new CakeMaker(cp);

		ce.start();
		cm.start();

		try {
			ce.join();
			cm.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
