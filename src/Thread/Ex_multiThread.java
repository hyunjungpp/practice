package Thread;
class ThreadClass2 implements Runnable{
	String name;
	public ThreadClass2(String name) {
		this.name = name;
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("[" + name + "]" + i);
		}
	}
}
public class Ex_multiThread {

	public static void main(String[] args) {
		ThreadClass2 tc1 = new ThreadClass2("Thread1");
		ThreadClass2 tc2 = new ThreadClass2("Thread2");

		Thread t1 = new Thread(tc1);
		t1.start();
		
		Thread t2 = new Thread(tc2);
		t2.start();
		
		for (int i = 0; i < 10; i++) {
			System.out.println("[Main]" + i);
		}
		
		try {
			t1.join(); // �ٸ� �����带 ���߰� �� �����带 ������ ���� (�켱���� ���亸�� �� ��)
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
