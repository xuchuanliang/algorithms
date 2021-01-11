package record.hanshunping.queue;

/**
 * 队列：使用数组实现队列
 * 队列特点：先进先出
 */
public class QueueTest {
    public static void main(String[] args) {
        testSimpleArrayQueue();
    }

    public static void testSimpleArrayQueue(){
        SimpleArrayQueue simpleArrayQueue = new SimpleArrayQueue(3);
        simpleArrayQueue.addEle(1);
        simpleArrayQueue.addEle(2);
        simpleArrayQueue.addEle(3);
        simpleArrayQueue.take();
        simpleArrayQueue.take();
        simpleArrayQueue.take();
        System.out.println(simpleArrayQueue.isFull());
        System.out.println(simpleArrayQueue.isEmpty());
        simpleArrayQueue.addEle(1);
    }
}

