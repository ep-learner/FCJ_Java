public class Str {
/**
stringbuffer的API
*/
    public static void main(String[] args) {
        String Str = "hello";
        StringBuffer sb = new StringBuffer(Str);

        sb.replace(3,5,"p");
        // 修改指定位置字符串
        // "help"

        sb.append("!");
        // 增加字符串
        // "help!"

        sb.insert(1,"__insert__");
        // 插入 "h__insert__elp!"
        // 原sb[1] = "e",现在sb[1] ="__insert__"

        sb.indexOf("l");
        // 查找第一次出现的位置
        sb.lastIndexOf("l");
        // 查找最后一次出现的位置

        sb.delete(0,1);
        //删除 第一项'h'
        //"__insert__elp!"

        System.out.println(sb.toString());

    }

}
