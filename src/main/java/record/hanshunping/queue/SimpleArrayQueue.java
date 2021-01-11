package record.hanshunping.queue;

/**
 * 使用数组实现的简单队列，无循环功能
 * 主要思路是：首先需要有个数组存储数据，同时有个值表示队列的最大容量；其次因为队列是先进先出，因此需要记录当前的头和尾
 */
public class SimpleArrayQueue implements Queue{
    /**
     * 用数组模拟队列
     */
    private final int[] arr;
    /**
     * 队列的头 初始情况下是-1
     */
    private int head;
    /**
     * 队列的尾 初始情况下是-1
     */
    private int tail;

    /**
     * 队列的长度
     */
    private final int size;

    public SimpleArrayQueue(int size) {
        this.size = size;
        this.arr = new int[size];
        head = -1;
        tail = -1;
    }

    /**
     * 判断队列是否为空：头和尾指向同一个元素，则表明队列为空
     * @return
     */
    @Override
    public boolean isEmpty(){
        return head==tail;
    }

    /**
     * 判断队列是否已满：尾减头==size
     * @return
     */
    @Override
    public boolean isFull(){
        return (tail-head)==size;
    }

    /**
     * 往队列中添加元素：添加到队尾
     */
    @Override
    public void addEle(int ele){
        //如果已经满了，则不允许增加元素
        if(isFull()){
            throw new IllegalStateException("队列已满");
        }
        //向队尾中增加元素
        arr[++tail] = ele;
    }

    /**
     * 从队列中取出元素：从队头中取
     * @return
     */
    @Override
    public int take(){
        //如果队列已空，则不允许取出元素
        if(isEmpty()){
            throw new IllegalStateException("队列已空");
        }
        //从队首获取元素
        return arr[++head];
    }

    @Override
    public void print() {

    }

    /**
     * 获取第一个元素
     * @return
     */
    @Override
    public int getFirst(){
        //如果队列已空，则无法访问元素
        if(isEmpty()){
            throw new IllegalStateException("队列已空");
        }
        return arr[head+1];
    }
}
