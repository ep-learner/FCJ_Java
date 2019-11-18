package Stack;

import java.util.LinkedList;

/**
 * 逆波兰表达式
 */
public class nibolan {
    public int evalRPN(String[] tokens) {
        if(tokens.length == 1) return Integer.parseInt(tokens[0]);

        int val = 0 ;
        LinkedList<String> strings = new LinkedList<>();
        for (int i =0;i<tokens.length;i++){
            if(tokens[i].equals("+") ||  tokens[i].equals("-") || tokens[i].equals("*") || tokens[i].equals("/") )
            {
                String left = strings.pop();
                String right = strings.pop();
                val = cal(left,right,tokens[i]);
                strings.push(String.valueOf(val));
                tokens[i] = String.valueOf(val);
            }
            else{
                strings.push(tokens[i]);
            }
        }
        return val;
    }
    public int cal(String b,String a ,String c){
        if(c.equals("/")) return Integer.parseInt(a)/Integer.parseInt(b);
        else if(c.equals("+")) return Integer.parseInt(a)+Integer.parseInt(b);
        else if(c.equals("-")) return Integer.parseInt(a)-Integer.parseInt(b);
        else  return Integer.parseInt(a)*Integer.parseInt(b);
    }

    public static void main(String[] args) {
        System.out.println(new nibolan().evalRPN(new String[]{"4","13","5","/","+"}));
    }
}
