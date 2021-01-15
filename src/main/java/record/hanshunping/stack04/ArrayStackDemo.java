package record.hanshunping.stack04;

/**
 * 使用数组实现栈结构
 * 栈：先进先出，在程序之间的调用、方法之间的调用、表达式的转换、二叉树的遍历、图形的深度优先搜索法中非常常用
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
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
        System.out.println(arrayStack.peek());
    }

    /**
     * 数据结构栈的思路：
     * 维护一个数组，用来存储数据结构
     * top用来维护栈顶指针，初始化为-1
     * 需要提供的api：队列是否空、是否满、压栈、弹栈、遍历栈
     */
    private static class ArrayStack {
        private final int maxSize;
        private final int[] arr;
        private int top;

        public ArrayStack(int maxSize) {
            this.maxSize = maxSize;
            this.arr = new int[maxSize];
            top = -1;
        }

        /**
         * 判断栈是否是空，如果top==-1则是空
         *
         * @return
         */
        public boolean isEmpty() {
            return top == -1;
        }

        /**
         * 判断栈是否满，如果top==maxSize-1则是满
         * @return
         */
        public boolean isFull() {
            return top == maxSize-1;
        }

        /**
         * 将ele压入栈顶
         * @param ele
         */
        public void pop(int ele){
            if(isFull()){
                throw new IllegalArgumentException("队里已满");
            }
            arr[++top] = ele;
        }

        /**
         * 弹出栈顶的一个元素
         */
        public int peek(){
            if(isEmpty()){
                throw new IllegalArgumentException("队列已空");
            }
            return arr[top--];
        }

        /**
         * 从栈顶开始遍历元素，并打印出来
         */
        public void list(){
            if(isEmpty()){
                System.out.println("栈为空");
            }
            int temp = top;
            while (temp!=-1){
                System.out.print(arr[temp--]+",");
            }
        }
    }
}
