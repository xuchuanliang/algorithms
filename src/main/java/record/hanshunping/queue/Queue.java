package record.hanshunping.queue;

/**
 * 队列接口
 */
public interface Queue{

    /**
     * 队列是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 队列是否已满
     * @return
     */
    boolean isFull();

    /**
     * 添加向队列中添加元素
     * @param ele
     */
    void addEle(int ele);

    /**
     * 从队列中获取元素
     * @return
     */
    int take();

    /**
     * 获取队首的第一个元素
     * @return
     */
    int getFirst();
}
