package record.hanshunping.stack04;

import java.util.Scanner;

/**
 * 基于栈的结构实现计算器：中缀表达式实现
 * 实现思路：
 * 1.创建两个栈，分别用来存储数字和表达式
 * 2.逐步解析数学表达式，遇到数字则将数字压入数字栈顶，
 * 如果遇到的是运算符，则与前一个运算符进行优先级比较，如果当前运算符的优先级大，则不进行计算，将当前运算符压入符号栈；
 * 如果当前运算符优先级小，前一个运算符优先级大，则说明前面是乘法，当前是减法，则需要先进行乘法运算，则需弹出数字栈中的两个数字以及符号栈顶的运算符进行计算，将计算结果压入数字栈，再将当前运算符低的符号压入符号栈
 * 3.当所有的处理完毕后，则此时符号栈中的符号都是优先级一样的，则逐步取出一个符号以及两个数字进行计算，将结果压入数字栈中
 * 4.最终符号栈空了，则数字栈只有一个数字，则最终的这个数字就是结果
 */
public class CalculatorInfix {
    public static void main(String[] args) {
        calculator();
    }

    public static void calculator() {
        //获取计算表达式
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要计算的表达式");
        String expression = scanner.nextLine();

        //初始化两个栈：数字栈和表达式栈
        ArrayStack numStack = new ArrayStack(20);
        ArrayStack expressionStack = new ArrayStack(20);

        //从第1个字符开始逐步遍历，直到最后一个字符
        int index = 0;
        while (index < expression.length()) {
            //获取下一个字符
            String nexSymbol = nextSymbol(expression, index);
            //更新当前的索引位置
            index = index + nexSymbol.length();
            //判断当前获取到的是不是表达式
            if (isExpression(nexSymbol)) {
                //如果是表达式
                if (expressionStack.isEmpty()) {
                    //如果表达式栈是空，则直接入栈
                    expressionStack.pop(nexSymbol.charAt(0));
                    continue;
                } else {
                    //说明当前符号是表达式，并且表达式栈有表达式，则获取前一个表达式并比较优先级
                    char exp1 = (char) expressionStack.top();
                    if (priority(Character.toString(exp1), nexSymbol)) {
                        //说明栈顶的表达式优先级比较高，则需要先计算，注意顺序
                        int n2 = numStack.peek();
                        int n1 = numStack.peek();
                        int result = calToNum(n1, n2, (char) expressionStack.peek());
                        //将计算的结果压入数字栈
                        numStack.pop(result);
                        //将当前的运算符压入运算符栈
                        expressionStack.pop(nexSymbol.charAt(0));
                    } else {
                        //说明栈顶的表达式优先级低于或与当前表达式优先级相同，则将符号直接入栈
                        expressionStack.pop(nexSymbol.charAt(0));
                    }
                }
            } else {
                //如果是数字，则直接压栈
                numStack.pop(Integer.parseInt(nexSymbol));
            }
        }
        //到此处，说明运算符栈中的所有运算符优先级相同，则将剩下的进行计算
        while (!expressionStack.isEmpty()) {
            int n2 = numStack.peek();
            int n1 = numStack.peek();
            int exp = expressionStack.peek();
            int result = calToNum(n1, n2, (char) exp);
            numStack.pop(result);
        }

        //到此处说明计算结束
        System.out.println(expression + "的计算结果是：" + numStack.top());
    }

    /**
     * 获取下一个字符，可能是运算符，可能是数字
     * 考虑到多位数字，所以每次都需要找到下一个运算符位置或者字符串末，才能确定获取到的是一个完整数字
     *
     * @param expression 运算表达式
     * @param index      开始运算的索引起始位置
     * @return
     */
    public static String nextSymbol(String expression, int index) {
        char[] chars = expression.toCharArray();
        if (isExpression(chars[index])) {
            //如果是表达式，则直接返回
            return Character.toString(chars[index]);
        }
        //如果是数字，则将数字的所有位数补全
        String num = "";
        //只要没有遍历到表达式并且没有遍历到头，则继续拼接，知道补齐当前数字的所有位数
        while (index < chars.length && !isExpression(chars[index])) {
            num = num + chars[index];
            index++;
        }
        return num;
    }

    /**
     * 判断是否是表达式
     *
     * @return
     */
    public static boolean isExpression(char c) {
        String s = Character.toString(c);
        return "+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s);
    }

    public static boolean isExpression(String s) {
        return "+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s);
    }

    /**
     * 比较两个符号的优先级，如果符号1的优先级大于符号2，则返回true；如果优先级相同或符号1的优先级小于符号2的优先级则均返回false
     *
     * @param exp1
     * @param exp2
     * @return
     */
    public static boolean priority(String exp1, String exp2) {
        return getPriority(exp1) > getPriority(exp2);
    }

    /**
     * 返回优先级，定义：加减 优先级是1  乘除优先级是2
     *
     * @param exp
     * @return
     */
    public static int getPriority(String exp) {
        if ("+".equals(exp) || "-".equals(exp)) {
            //加 减则是1
            return 1;
        } else if ("*".equals(exp) || "/".equals(exp)) {
            return 2;
        }
        return 0;
    }

    /**
     * 计算根据表达式计算两个数字计算结果
     *
     * @param num1
     * @param num2
     * @param exp
     * @return
     */
    public static int calToNum(int num1, int num2, char exp) {
        int result = 0;
        switch (exp) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
        }
        return result;
    }
}
