package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class printDemo {
    public static void main(String[] args) throws IOException {
        InputStream is  =System.in;
        Scanner scanner = new Scanner(is);//用法类似于iterator
        String next = scanner.next();

        System.out.println(next);

    }
}
