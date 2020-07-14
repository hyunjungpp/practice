package Thread;
/*
 * Thread wait, notifyAll 이해하기
 * Cake예시
 * 
 */
class CakePlate{
	private int breadCount =0;
	public synchronized void makeBread(){
		if (breadCount >= 10 ) {
			try {
				System.out.println("빵이 남습니다.");
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		breadCount++;
		System.out.println("빵을 한개 더 만든다. 총 :" + breadCount + "개");
		this.notifyAll();// 기다리고 있는 모든 스레드를 깨운다.
	}

	public synchronized void eatBread(){
		if (breadCount <1) {
			try {
				System.out.println("빵이 모자라 기다리기.");
				wait();
			} catch (InterruptedException e) {
			}
		}
		breadCount--;
		System.out.println("빵을 먹음 총 :" + breadCount+"개");
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
