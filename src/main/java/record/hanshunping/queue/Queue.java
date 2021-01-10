package record.hanshunping.queue;

public interface Queue {
    /**
     * 队列是否为空
     */
    void isEmpty();

    /**
     * 队列是否已满
     */
    void isFull();

    /**
     * 添加元素
     */
    void addEle();

    /**
     * 获取队列头部元素
     * @return
     */
    int getEle();

    /**
     * 打印队列中的元素
     */
    void print();
}
