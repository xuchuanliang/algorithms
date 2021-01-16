package record.hanshunping.stack04;

/**
 * 使用数组实现栈结构
 * 栈：先进先出，在程序之间的调用、方法之间的调用、表达式的转换、二叉树的遍历、图形的深度优先搜索法中非常常用
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
//        testStack();
    }

    /**
     * 测试栈
     */
    public static void testStack() {
        ArrayStack arrayStack = new ArrayStack(5);
        arrayStack.pop(1);
        arrayStack.pop(2);
        arrayStack.pop(3);
        arrayStack.pop(4);
        arrayStack.pop(5);
        arrayStack.list();
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.peek());
    }
}
