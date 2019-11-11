package IO;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class fileDemo {
    public static void main(String[] args) {
        //文件创建
        File file = new File("D:/test");
        boolean bool = file.mkdirs();//新建多级目录

        //类型判断
        boolean ifdir = file.isDirectory();
        System.out.println("是否是目录"+ifdir);
        boolean iffile = file.isFile();
        System.out.println("是否是文件"+iffile);

        //获取路径信息
        System.out.println("getAbsolutePath:"+file.getAbsolutePath());
        System.out.println("URI:"+file.toURI().toString());
        System.out.println("getName:"+file.getName());
        System.out.println("getParentFile"+file.getParentFile().getName());


        //子文件筛选
        //方法1
        File[] files = file.listFiles(new FileFilter() {
            public boolean accept(File pathname) {
                return pathname.getName().endsWith(".java")||pathname.isDirectory();
            }
        });
        //方法2
        file.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                System.out.println("dir是目录 test"+dir.getName());
                System.out.println("name是子文件的名称"+name);
                return true;
            }
        });

    }
}
