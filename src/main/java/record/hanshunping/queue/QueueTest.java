package record.hanshunping.queue;

/**
 * 模拟队列测试
 */
public class QueueTest {
    public static void main(String[] args) {
        testCycleArrayQueue();
    }

    /**
     * 模拟测试环形队列
     */
    public static void testCycleArrayQueue(){
        CycleArrayQueue cycleArrayQueue  = new CycleArrayQueue(3);
        cycleArrayQueue.print();
        cycleArrayQueue.addEle(1);
        cycleArrayQueue.addEle(2);
        cycleArrayQueue.addEle(3);
        System.out.print(cycleArrayQueue.getEle());
        System.out.print(cycleArrayQueue.getEle());
        System.out.print(cycleArrayQueue.getEle());
        cycleArrayQueue.addEle(1);
        cycleArrayQueue.addEle(2);
        cycleArrayQueue.addEle(3);
        cycleArrayQueue.print();
    }
}
