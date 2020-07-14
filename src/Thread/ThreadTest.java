package Thread;

class ThreadClass implements Runnable{ 
	public void run(){ 
		System.out.println("thread is running...");
	} 
} 

public class ThreadTest {

	public static void main(String[] args) {
		ThreadClass t1 = new ThreadClass();
		Thread tt1 = new Thread(t1);
		tt1.start();
		
		try {
			tt1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
