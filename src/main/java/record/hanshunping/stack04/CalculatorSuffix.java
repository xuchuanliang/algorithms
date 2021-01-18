package record.hanshunping.stack04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 后缀表达式实现计算器【逆波兰表达式】
 */
public class CalculatorSuffix {

    public static void main(String[] args) {
        String infixExpression = "(3+4)*5-26";
        //由中缀表达式转成后缀表达式
        String suffixExpression = transferSuffixFromInfix(infixExpression);
        System.out.println(suffixExpression);
        //3 4 + 5 × 6 -
        //使用后缀表达式，按照规则计算值
        System.out.println(calBySuffixExpression(suffixExpression));
    }

    /**
     * 将中缀表达式根据规则转成后缀表达式
     *
     * @param infixExpression
     * @return
     */
    public static String transferSuffixFromInfix(String infixExpression) {
        //将中缀表达式按照顺序切分成数字或运算符，放在一个集合中
        List<String> infixSymbol = splitToSymbol(infixExpression);
        //按照流程将中缀表达式转成后缀表达式
        /*  注意：括号不算运算符
            1.创建两个栈，分别用来存储运算符以及中间结果：运算符栈、中间结果栈
            2.从左向右遍历中缀表达式
            3.如果遇到的是字符，则直接压入中间结果栈
            4.如果遇到的是运算符，则：
                4.1如果当前运算符栈为空，或者运算符栈顶元素为左括号，则直接压入运算符栈
                4.2如果当前运算符比运算符栈顶运算符优先级高，则直接压入运算符栈；
                4.3当前运算符比运算符栈顶的运算符优先级低或者相等，则取出运算符栈顶的运算符压入中间结果栈，并重复第四条，与新的操作符栈顶元素优先级进行比较
            5.如果遇到是括号，则：
                5.1如果是左括号，则直接压入运算符栈
                5.2如果是右括号，则依次从运算符栈弹出栈顶的运算符并压入中间结果栈，直到遇到左括号结束，并丢弃两个括号
            6.重复4和5步骤，直到表达式的最右边
            7.然后将运算符栈中的元素依次弹出并压入中间结果栈
            8.最后依次弹出中间结果栈的元素，组成的表达式就是对应的后缀表达式的**逆序**
            9.最后中间结果栈弹出的元素组成的字符串翻转，结果就是后缀表达式
         */
        //运算符栈
        Stack<String> operateStack = new Stack<>();
        //中间结果栈
        Stack<String> middleStack = new Stack<>();
        for (String s : infixSymbol) {
            if (isExpression(s)) {
                //说明是运算符
                if (operateStack.isEmpty() || "(".equals(s) || "(".equals(operateStack.peek())) {
                    //如果运算符栈为空、当前的运算符是左括号、或当前栈顶元素是左括号，则直接入运算符栈
                    operateStack.push(s);
                    continue;
                }
                if (")".equals(s)) {
                    //如果是右括号，则依次弹出操作数栈中的元素并压入中间结果栈中，直到遇到左括号
                    while (true) {
                        String op = operateStack.pop();
                        if ("(".equals(op)) {
                            break;
                        }
                        middleStack.push(op);
                    }
                    continue;
                }
                //是加减乘除符号，则与操作数栈顶进行比较优先级，如果当前优先级高，则直接压入操作数栈，否则弹出操作数栈顶元素压入中间结果栈，并继续与操作数栈顶元素进行比较
                if (priority(s) > priority(operateStack.peek())) {
                    //如果当前优先级大于操作栈顶优先级，则直接入栈
                    operateStack.push(s);
                } else {
                    //则重复将操作符栈顶元素与当前符号比较，当前符号优先级于栈顶元素优先级相同或低于栈顶符号优先级，则弹出并压入中间结果栈并重复
                    while (!operateStack.isEmpty() && priority(s) <= priority(operateStack.peek())) {
                        //只要操作符栈不为空，且优先级不高于栈顶元素，则重复
                        middleStack.push(operateStack.pop());
                    }
                    //处理完成后，将当前符号压入操作符栈
                    operateStack.push(s);
                }
            } else {
                //是数字，则直接压入中间结果栈
                middleStack.push(s);
            }
        }

        //执行到此处已经解析完中缀表达式，则将操作符栈中的元素逐步弹出到中间结果栈
        while (!operateStack.isEmpty()) {
            middleStack.push(operateStack.pop());
        }
        //最终将中间结果栈依次弹出组成的字符串倒叙就是后缀表达式  3 + 5 6 -   - 6 5 + 3
        String result = "";
        while (!middleStack.isEmpty()) {
            //倒叙，则倒着组成字符串即可，由于存在多位数问题，我们约定后缀表达式之间的字符使用空格隔开
            result = " "+middleStack.pop() + result;
        }
        return result;
    }

    /**
     * 将给定的表达式按照顺序拆分成一个个符号，添加到集合中，考虑多位数情况
     *
     * @param expression
     * @return
     */
    public static List<String> splitToSymbol(String expression) {
        //表示下一个要解析的索引位置
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < expression.length()) {
            if (isExpression(Character.toString(expression.charAt(index)))) {
                //如果是运算符，则将运算符添加到集合中，且index向后移一位
                result.add(Character.toString(expression.charAt(index)));
                index++;
            } else {
                //如果不是运算符，考虑到存在多位数字的情况，则向后扫描整个完整数字
                //起始位置
                int first = index;
                //只要没有到头并且未遇到运算符，则继续向后推动index
                while (index < expression.length() && !isExpression(Character.toString(expression.charAt(index)))) {
                    index++;
                }
                //从first至index之间的是一个数字
                String num = expression.substring(first, index);
                //添加到集合中
                result.add(num);
            }
        }
        //到此说明已经遍历到了expression的末尾，则返回整个拆分后的结果
        return result;
    }

    /**
     * 判断的当前传入的字符是不是运算符
     *
     * @param c
     * @return true表示是运算符；false表示不是运算符
     */
    public static boolean isExpression(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/") || c.equals("(") || c.equals(")");
    }

    /**
     * 获取s的优先级，数字越大，则优先级越高，加减是1 乘除是2
     *
     * @param s
     * @return
     */
    public static int priority(String s) {
        if (s.equals("+") || s.equals("-")) {
            return 1;
        } else if (s.equals("*") || s.equals("/")) {
            return 2;
        } else {
            return 0;
        }
    }

    /**
     * 根据两个数字和运算符，计算结果
     *
     * @param num1
     * @param num2
     * @param ope
     * @return
     */
    public static Integer calByNumAndOpe(Integer num1, Integer num2, String ope) {
        Integer result = null;
        switch (ope) {
            case "-":
                result = num1 - num2;
                break;
            case "+":
                result = num1 + num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
        return result;
    }


    /**
     * 根据传入的后缀表达式，基于栈结构（java原生的栈） 计算表达式结果
     *
     * @param suffixExpression 后缀表达式，规则如：(3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 -，那么传入的就是3 4 + 5 × 6 -，各个符号之间用空格区分
     * @return
     */
    public static int calBySuffixExpression(String suffixExpression) {
        Stack<String> stack = new Stack<>();
        //为了方便计算，直接将表达式转成集合，而不是通过索引的方式
        List<String> list = Arrays.asList(suffixExpression.split(" "));
        /*
        逆波兰表达式计算思路是：从list开始遍历，如果是数字则直接压入栈中，如果是运算符，则从栈中弹出两个元素，并将计算结果压入栈中
         */
        list.forEach(s -> {
            if ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s)) {
                //是运算符，从栈中弹出两个元素，并将运算结果压入栈中
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                switch (s) {
                    case "+":
                        stack.push((num1 + num2) + "");
                        break;
                    case "-":
                        stack.push((num1 - num2) + "");
                        break;
                    case "*":
                        stack.push((num1 * num2) + "");
                        break;
                    case "/":
                        stack.push((num1 / num2) + "");
                        break;
                    default:
                        throw new IllegalArgumentException("表达式错误");
                }
            } else {
                //是数字、直接压栈
                stack.push(s);
            }
        });
        //处理完毕后，栈中剩下一个元素就是计算结果
        return Integer.parseInt(stack.pop());
    }

}
