package FileIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Ex_FileIO {

	public static void main(String[] args) {
		// file 길이가 2000 이상만 복사하기
		File directory = new File("../INPUT");
		File[] list = directory.listFiles();
		
		for(File f : list){
			if (f.isFile() && f.length() > 2000) {
				File outDir = new File("../OUTPUT/");
				if (!outDir.exists()) {
					outDir.mkdirs();
				}
				CopyFile(f,"../OUTPUT/" + f.getName());
			}
		}

	}
	
	static void CopyFile(File inputFile, String outputFile)
	{
		final int BUFFER_SIZE = 512;
		int readLen;
		try {
			InputStream inputStream = new FileInputStream(inputFile);
			OutputStream outputStream = new FileOutputStream(outputFile);
			byte[] buffer = new byte[BUFFER_SIZE];
			while ((readLen = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, readLen);
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

}
