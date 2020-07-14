package FileIO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class FileReadTest {

	public static void main(String[] args) throws IOException {
		//현재 directory
		String currnetDir = System.getProperty("user.dir");
		System.out.println("current Directory :" + currnetDir);
		
		//read file.(한줄씩)
		PrintFile("input.txt");
		
		//write file
		writeFile("write.txt", "파일에 내용을 적어보자.><");
		
		//copy file(buffer size만큼)
		CopyFile("input.txt", "copy.txt");
		
		//file list
		FileDirList();
		
		//read byte
			//byte를 string으로
		byte[] byteResult = readBytes("input.txt", 0, 8); // 한글자 1byte \n길이 계산
		String result = new String(byteResult, 0, byteResult.length);
		System.out.println(result);
		
		//console입력 파일 저장및 읽어오기
		consoleWrint("consoleWrite.txt");
	}

	static void PrintFile(String fileName) { 
		String line = null;

		try { 
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) { //읽어 올때 \n생략 되는듯 stringbbuffer할 때 "\n"추가하기
				System.out.println(line); 
				System.out.println("print read check");
			}
			bufferedReader.close(); 
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace(); 
		}
	}
	
	static void writeFile(String filePath, String content) throws IOException{
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			fileWriter = new FileWriter(filePath);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(content);
		} finally {
			if (fileWriter != null) {
				bufferedWriter.close();
			}
			if (fileWriter != null) {
				fileWriter.close();
			}
		}
		
		
		/*//2-way
		String s = null;
		FileWriter fw = new FileWriter(filePath, true);
		PrintWriter pw = new PrintWriter(fw);
		pw.print(content);*/
	}

	static void CopyFile(String inputFile, String outputFile)
	{
		final int BUFFER_SIZE = 512;
		int readLen;
		try {
			InputStream inputStream = new FileInputStream(inputFile);
			OutputStream outputStream = new FileOutputStream(outputFile);
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((readLen = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, readLen);
				System.out.println("check");
			}
			inputStream.close();
			outputStream.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	static void FileDirList() { 
		File directory = new File("src");
		File[] fList = directory.listFiles();
		for (File file : fList) { 
			if(file.isDirectory()) {
				System.out.println("["+file.getName()+"]");
			} else {
				System.out.println(file.getName()); 
			} 
		} 
	}
	
	static byte[] readBytes(String filePath, int offset, int length) throws IOException {
		 
	    RandomAccessFile randomFile = null;
	    try {
	        randomFile = new RandomAccessFile(filePath, "r"); 
	        randomFile.seek(offset);
	         
	        byte[] dataBytes = new byte[length];
	        randomFile.readFully(dataBytes);
	         
	        return dataBytes;
	         
	    } finally {
	        if (randomFile != null) try { randomFile.close(); } catch (Exception ex) { /* Do Nothing */ }
	    }
	}
	
	static void consoleWrint(String filePath) throws IOException{
		Scanner sc = new Scanner(System.in);
		String msg = null;

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		
		try {
			fileWriter = new FileWriter(filePath);
			bufferedWriter = new BufferedWriter(fileWriter);
			while(sc.hasNext()){
				msg = sc.nextLine();
				bufferedWriter.write(msg);
				bufferedWriter.newLine(); //줄바꿈 넣어야함.
				if (msg.equals("q")) {
					return;
				}
				
			}
		} finally {
			if (fileWriter != null) {
				bufferedWriter.close();
			}
			if (fileWriter != null) {
				fileWriter.close();
			}
		}
	}
}
