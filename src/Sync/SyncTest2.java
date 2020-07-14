package Sync;
class StaticPrint implements Runnable{
	private static int i= 0; // static ���� ���

	@Override
	public void run() {
		show();
	}
	public void show() { // ���������� �����Ѵ�. ���ÿ� �ڿ��� �����Ѵ�.
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

	public void show2() {//�� �����尡 ���´�.
		synchronized (StaticPrint.class) { //synchronized�ְ� ���� ����.(������ �������� �ڿ��� ���ÿ� ���� �Ծ, �������� �ȴ�....��)
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
	 * �����尡 ���� �ڿ��� �����ϱ�(��� ���� ����)
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
		
		//case2 �ϳ��� Runnable���� �� ������ thread����
		//3���� �����尡 ��������� ���� �Ѵ�.

		StaticPrint2 ss1 = new StaticPrint2();

		Thread tt1 = new Thread(ss1,"a");
		Thread tt2 = new Thread(ss1,"b");
		Thread tt3 = new Thread(ss1,"c");

		tt1.start();
		tt2.start();
		tt3.start();

	}
}
