package record.hanshunping.queue;

/**
 * 使用数组模拟队列：先进先出
 * 思路：队列的特征是先进先出，数组要循环利用，那么我们需要记录队列的头、尾、队列存储的元素数量
 */
public class CycleArrayQueue {
    /**
     * 队列存储元素的数组
     */
    private final int[] arr;
    /**
     * 队列的初始容量
     */
    private final int maxSize;
    /**
     * 队列的头的位置：指向的当前队列第一个元素的位置，初始为0
     */
    private int head;
    /**
     * 队列尾的位置：指向的当前队列第一个为空的位置，初始为0
     */
    private int tail;
    /**
     * 队列当前存放的元素数量
     */
    private int size;

    public CycleArrayQueue(int maxSize) {
        this.arr = new int[maxSize];
        this.maxSize = maxSize;
        this.head = 0;
        this.tail = 0;
        this.size = 0;
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 队列是否已满
     *
     * @return
     */
    public boolean isFull() {
        return size == maxSize;
    }

    /**
     * 添加元素
     *
     * @param ele
     */
    public void addEle(int ele) {
        if (isFull()) {
            //如果已满，则不允许添加
            throw new RuntimeException("队列已满");
        }
        //添加元素：将当前队列中元素数量+1，将需要添加的元素添加到tail位置，并且将tail+1
        // 注意，此处由于模拟的是循环队列，那么无论是tail或者head递增时都需要对maxSize取模
        size++;
        arr[tail] = ele;
        tail = (tail + 1) % maxSize;
    }

    /**
     * 获取元素
     *
     * @return
     */
    public int getEle() {
        if (isEmpty()) {
            //如果队列为空，那么则不允许获取元素
            throw new RuntimeException("队列已空");
        }
        //从队列中获取元素：将当前元素数量-1，递增head值
        //注意，此处由于模拟的是环形队列，那么当head递增时，实际上是针对maxSize取模
        size--;
        int result = arr[head];
        head = (head + 1) % maxSize;
        return result;
    }

    public void print() {
        if (isEmpty()) {
            System.out.println("空队列");
        }
        System.out.print("[");
        //将当前队列中元素个数存储在临时变量中
        int temp = size - 1;
        for (int i = 0; i < size; i++) {
            System.out.print(arr[(head + i) % maxSize]+",");
        }
        System.out.print("]");
    }
}
