package Socket.EX;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


class ThreadClass implements Runnable{
	ServerSocket listener; 
	
	@Override
	public void run() {
		final int BUF_SIZE = 512;
    	int recvLen;   	
    	byte[] buffer = new byte[BUF_SIZE];
    	
 		try {
			listener = new ServerSocket(9090);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		
        try {
            while (true) {
                Socket s = listener.accept();
                InputStream input = s.getInputStream();
                int cnt = 0;
                while ((recvLen = input.read(buffer, 0, BUF_SIZE)) !=  -1) 
                {            	
	               	FileOutputStream fw = new FileOutputStream("recvTest.exe", true);
               		fw.write(buffer, cnt, recvLen);
               		fw.close();
                }
                input.close();
                System.out.println("recvTest.exe is received.");
            }
        } catch (IOException e) {
			//e.printStackTrace();
		}
        finally {
            try {
				listener.close();
			} catch (IOException e) {
				//e.printStackTrace();
			}
        }
		
	}
	
}
public class Ex_Server {
	/*
	 * 클라이언트로부터 파일을 받고 "QUIT"입력까지 대기한다.
	 */

	public static void main(String[] args) throws InterruptedException {
		ThreadClass m1 = new ThreadClass();
		Thread t1 = new Thread(m1);
		t1.start();

		String message;
		Scanner scan = new Scanner(System.in);  

		while (true) {
			message = scan.nextLine();
			System.out.println(message);
			
			if (message.equals("q")) {
				try {
					t1.join();
					scan.close();
					break;
				} catch (InterruptedException e) { 
					e.printStackTrace(); 
				} 
			}
		}

	}

}
