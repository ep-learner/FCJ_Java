package IO;

import java.io.*;

public class bufferIODemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("/");
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String s ;
        while ((s = br.readLine())!=null){
            System.out.println(s);
        }

        FileOutputStream fos = new FileOutputStream("/output");
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        bw.write("输出这串内容到文件中");
    }
}
