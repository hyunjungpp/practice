package Socket.EX;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ex_Client {
	/*
	 * test.exe파일을 서버에게 전송한다.
	 */

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket s = new Socket("127.0.0.1", 9090);
		java.io.OutputStream out = s.getOutputStream();
		byte[] buffer = new byte[4096];
		int readLen;
		
        //get all the files from a directory
        FileInputStream inputStream = new FileInputStream("test.exe");
        while((readLen = inputStream.read(buffer)) != -1) {
        	out.write(buffer,0,readLen);
        }   
        inputStream.close();    
		
        s.close();
        
        System.out.println("test.exe"+" is sent.");
	}

}
