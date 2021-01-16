package record.hanshunping.stack04;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式实现计算器【逆波兰表达式】
 */
public class CalculatorSuffix {

    public static void main(String[] args) {
        String suffix = "3 4 + 5 * 6 -";
        System.out.println(calBySuffixExpression(suffix));
    }

    /**
     * 根据传入的后缀表达式，基于栈结构（java原生的栈） 计算表达式结果
     * @param suffixExpression 后缀表达式，规则如：(3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 -，那么传入的就是3 4 + 5 × 6 -，各个符号之间用空格区分
     * @return
     */
    public static int calBySuffixExpression(String suffixExpression){
        Stack<String> stack = new Stack<>();
        //为了方便计算，直接将表达式转成集合，而不是通过索引的方式
        List<String> list = Arrays.asList(suffixExpression.split(" "));
        /*
        逆波兰表达式计算思路是：从list开始遍历，如果是数字则直接压入栈中，如果是运算符，则从栈中弹出两个元素，并将计算结果压入栈中
         */
        list.forEach(s->{
            if("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)){
                //是运算符，从栈中弹出两个元素，并将运算结果压入栈中
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                switch (s){
                    case "+":
                        stack.push((num1+num2)+"");
                        break;
                    case "-":
                        stack.push((num1-num2)+"");
                        break;
                    case "*":
                        stack.push((num1*num2)+"");
                        break;
                    case "/":
                        stack.push((num1/num2)+"");
                        break;
                    default:throw new IllegalArgumentException("表达式错误");
                }
            }else{
                //是数字、直接压栈
                stack.push(s);
            }
        });
        //处理完毕后，栈中剩下一个元素就是计算结果
        return Integer.parseInt(stack.pop());
    }

}
