package record.book.capter03;

import record.U;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 符号/字典测试主类
 */
public class STMain {

    public static void main(String[] args) throws InterruptedException {
        testSequentialSearchST();
        testBinarySearchST();
    }


    /**
     * 测试使用链表实现的字典表
     */
    public static void testSequentialSearchST() {
        ST<String, String> st = new SequentialSearchST<>();
        U.fillST(st, 100);
        st.put(U.buildSTKey(3), U.buildSTValue(8));
        st.delete(U.buildSTKey(6));
        st.delete(U.buildSTKey(10));
        st.print();
        System.out.println(st.isEmpty());
        System.out.println(st.size());
    }

    /**
     * 测试使用数组实现的字典项
     */
    public static void testBinarySearchST() {
        ST<Integer, String> st = new BinarySearchST<>();
        for (int i = 0; i < 100; i++) {
            Integer key = new Random().nextInt(2000);
            System.out.println(key);
            st.put(key, "value -->" + key);
        }
        st.print();
    }
}
