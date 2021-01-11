package record.hanshunping.queue;

/**
 * {@link SimpleArrayQueue} 的问题就是一次性使用，即使最后把队列take空了后依然无法存放元素
 * 可以考虑将数组以环形的方式利用起来，通过取模的方式填充数据
 * 主要思路如下：
 *
 */
public class CycleArrayQueue implements Queue{

    private final int[] arr;
    private final int size;
    private int head;
    private int tail;
    private int eleNum;

    public CycleArrayQueue(int size) {
        this.size = size;
        this.arr = new int[size];
        this.head = -1;
        this.tail = -1;
        this.eleNum = 0;
    }

    /**
     * 队列是否为空，当
     * @return
     */
    @Override
    public boolean isEmpty() {
        return eleNum==0;
    }

    @Override
    public boolean isFull() {
        return eleNum==size;
    }

    @Override
    public void addEle(int ele) {
        if(isFull()){
            throw new IllegalStateException("队列已满");
        }

    }

    @Override
    public int take() {
        return 0;
    }

    @Override
    public int getFirst() {
        return 0;
    }
}
