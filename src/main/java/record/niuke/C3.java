package record.niuke;


import java.util.Stack;

public class C3 {
    /*
    将一个字符串中的空格替换成 "%20"。
    本人思路，使用栈来实现
     */
    public static String replaceSpace(String s){
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for(int i=0; i<chars.length; i++){
            if(chars[i] == ' '){
                stack.push('0');
                stack.push('2');
                stack.push('%');
            }else{
                stack.push(chars[i]);
            }
        }

        return join(stack);
    }

    private static String join(Stack<Character> stack){
        StringBuffer stringBuffer = new StringBuffer();
        while (!stack.empty()){
            stringBuffer.append(stack.pop());
        }
        return stringBuffer.toString();
    }
}
