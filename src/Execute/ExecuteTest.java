package Execute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecuteTest {
    public static void main(String[] args) throws IOException {
        Process builder = new ProcessBuilder("recvTest.exe","param test" ).start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(builder.getInputStream()));
        String str;
        while ((str = reader.readLine()) != null){
            System.out.println(str);
        }
    }
}
