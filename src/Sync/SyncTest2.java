package Sync;
class StaticPrint implements Runnable{
	private static int i= 0; // static 권장 노노

	@Override
	public void run() {
		show();
	}
	public void show() { // 전역변수를 공유한다. 동시에 자원에 접근한다.
		for (; i < 100; i++) {
			if ((Thread.currentThread()).getName().equals("a")) {
				System.out.println("[A" + i + "]");
			} else if ((Thread.currentThread()).getName().equals("b")) {
				System.out.println("[B" + i + "]");
			} else if ((Thread.currentThread()).getName().equals("c")) {
				System.out.println("[C" + i + "]");
			}
		}
	}

	public void show2() {//한 스레드가 들어온다.
		synchronized (StaticPrint.class) { //synchronized있고 없고 차이.(없으면 스레들이 자원을 동시에 나눠 먹어서, 엉망으로 된다....ㅠ)
			for (; i < 100; i++) {
				if ((Thread.currentThread()).getName().equals("a")) {
					System.out.println("[A" + i + "]");
				} else if ((Thread.currentThread()).getName().equals("b")) {
					System.out.println("[B" + i + "]");
				} else if ((Thread.currentThread()).getName().equals("c")) {
					System.out.println("[C" + i + "]");
				}
			}
		}
	}
}

class StaticPrint2 implements Runnable{
	/*
	 * 스레드가 같은 자원을 공유하기(멤버 변수 공유)
	 */
	private int i= 0;

	@Override
	public void run() {
		show();
	}
	
	public void show() { 
		for (; i < 100; i++) {
			if ((Thread.currentThread()).getName().equals("a")) {
				System.out.println("[A" + i + "]");
			} else if ((Thread.currentThread()).getName().equals("b")) {
				System.out.println("[B" + i + "]");
			} else if ((Thread.currentThread()).getName().equals("c")) {
				System.out.println("[C" + i + "]");
			}
		}
	}

}
public class SyncTest2 {

	public static void main(String[] args) {
		/*StaticPrint s1 = new StaticPrint();
		StaticPrint s2 = new StaticPrint();
		StaticPrint s3 = new StaticPrint();

		Thread t1 = new Thread(s1,"a");
		Thread t2 = new Thread(s2,"b");
		Thread t3 = new Thread(s3,"c");

		t1.start();
		t2.start();
		t3.start();*/
		
		//case2 하나의 Runnable생성 후 여러개 thread생성
		//3개의 스레드가 멤버변수를 공유 한다.

		StaticPrint2 ss1 = new StaticPrint2();

		Thread tt1 = new Thread(ss1,"a");
		Thread tt2 = new Thread(ss1,"b");
		Thread tt3 = new Thread(ss1,"c");

		tt1.start();
		tt2.start();
		tt3.start();

	}
}
