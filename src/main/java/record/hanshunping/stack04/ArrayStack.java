package record.hanshunping.stack04;

/**
 * 数据结构栈的思路：
 * 维护一个数组，用来存储数据结构
 * top用来维护栈顶指针，初始化为-1
 * 需要提供的api：队列是否空、是否满、压栈、弹栈、遍历栈
 */
public class ArrayStack {
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
        System.out.println();
    }

    /**
     * 获取栈顶的元素，并且不弹出
     * @return
     */
    public int top(){
        if(isEmpty()){
            throw new IllegalArgumentException("栈为空");
        }
        return arr[top];
    }
}
