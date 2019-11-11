package IO;

import java.io.*;
import java.util.Scanner;

public class fileIODemo {
    public static void main(String[] args) throws IOException {
        byte[] bytes = new byte[1024];

        FileInputStream fis = new FileInputStream("D:/input");
        int len = fis.read(bytes);
        String res = new String(bytes,0,len);

        FileOutputStream fos = new FileOutputStream("D:/output");
        fos.write(bytes);//输入前面从输入流读取的内容
        fos.write("输出到文件D:/output".getBytes());


    }
}
