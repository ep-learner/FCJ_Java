
import java.io.*;
import java.util.Scanner;

public class test {
    public static void main(String[] args) throws IOException {

		/**
		控制台的IO
		in：scanner nextLine
		out：print
		*/
		//1.1、从控制台获取输入
		Scanner in = new Scanner(System.in);
        System.out.println("提示信息");
        String Str = in.nextLine();//回车作为分隔符
        in.next();//空格作为分隔符

		//1.2输出到控制台
        System.out.println("输出1" + "输出2");
		
		
		
		/**
		文件IO
		is：二进制 字节数组 任意长度字节数组br.readLine
		os：二进制 字节数组 任意类型数据bw.write
		*/
		
        //2.1读取文件
        //a、确定长度的字符串用byte数组存储
        InputStream fis = new FileInputStream("d:/set");//读取二进制文件
        byte[] b =new byte[1024];
		
		//赋值给byte数组；
        //num是读取到的字节数量
        int num = fis.read(b);

		//使用byte数组生成一个字符串
        String s = new String(b,0,num);


        //b、使用br读取任意长度的字符串
		//最里层是文件输入流 0101；
        //ISR读取FIS的内容获得字节数组
        //BR可以读取任意长度的字节数组
        BufferedReader br =new BufferedReader(new InputStreamReader(fis));

		//按行读取
        String i = br.readLine();


        //2.2写入文件
		
        //输出字节数组
        //1）覆盖输出
        OutputStream os = new FileOutputStream("D:copy");
        os.write("覆盖写入文件D:copy".getBytes("UTF-8"));
        //2）追加输出
        OutputStream os_add = new FileOutputStream("D:/add", true);
        os_add.write("追加写入文件D:/add".getBytes("UTF-8"));
        
        //输出任意数据类型
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:/add", true)));
        bw.write("输出任意数据类型");
    }
}
