package record.niuke;

import java.util.LinkedList;
import java.util.Stack;

public class C4 {
    /*
    从尾到头反过来打印出每个结点的值
    思路1：使用递归方式，只要next不是空，那么就先打印next
    思路2：使用栈实现
     */
    public static void printListFromTailToHead(LinkNode linkNode){
        print1(linkNode);
        System.out.println(" ");
        print2(linkNode);

    }

    /**
     * 递归方式打印
     * @param linkNode
     */
    private static void print1(LinkNode linkNode){
        if(linkNode.getNext()==null){
            System.out.print(linkNode.getVal()+",");
        }
        if (linkNode.getNext()!=null){
            print1(linkNode.getNext());
            System.out.print(linkNode.getVal()+",");
        }
    }

    /**
     * 使用栈实现
     * @param linkNode
     */
    public static void print2(LinkNode linkNode){
        Stack stack = new Stack();
        while (linkNode != null){
            stack.push(linkNode.getVal());
            linkNode = linkNode.getNext();
        }
        while (!stack.empty()){
            System.out.print(stack.pop()+",");
        }
    }

}

