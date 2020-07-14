package Sync;

import java.util.concurrent.locks.ReentrantLock;

class ThreadClass implements Runnable{
	String name;
	
	public ThreadClass(String name) {
		this.name = name;
	}
	
	static ReentrantLock lock = new ReentrantLock();
	
	@Override
	public void run() {
		lock.lock();
		try {
			System.out.println(name);
			
			for (int i = 0; i < 30; i++) {
				System.out.print(i + " ");
			}
			System.out.println();
		} finally {
			lock.unlock();
		}
		
	}
	
}
public class SyncTest {

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new ThreadClass("Thread1"));
		Thread t2 = new Thread(new ThreadClass("Thread2"));

		t1.start();
		t2.start();
		
		ThreadClass.lock.lock();
		try {
			System.out.println("Main");
			for (int i = 0; i < 30; i++) {
				System.out.print(i + " ");
			}
			System.out.println();
		} finally {
			ThreadClass.lock.unlock();
		}
		
		t1.join();
		t2.join();
	}

}
