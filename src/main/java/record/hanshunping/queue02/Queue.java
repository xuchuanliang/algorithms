package record.hanshunping.queue02;

public interface Queue {
    /**
     * 队列是否为空
     */
    boolean isEmpty();

    /**
     * 队列是否已满
     */
    boolean isFull();

    /**
     * 添加元素
     */
    void addEle(int ele);

    /**
     * 获取队列头部元素
     * @return
     */
    int take();

    /**
     * 打印队列中的元素
     */
    void print();

    int getFirst();
}
