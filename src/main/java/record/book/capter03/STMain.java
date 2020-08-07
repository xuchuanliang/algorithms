package record.book.capter03;

import record.U;

import java.util.Random;

/**
 * 符号/字典测试主类
 */
public class STMain {

    public static void main(String[] args) throws InterruptedException {
//        testSequentialSearchST();
//        testBinarySearchArrayST();
        tesBinarySearchTree();

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
     * 测试使用数组实现的基于二分查找的符号表
     */
    public static void testBinarySearchArrayST() {
        ST<Integer, String> st = new BinarySearchArrayST<>();
        for (int i = 0; i < 100; i++) {
            Integer key = new Random().nextInt(2000);
            System.out.println(key);
            st.put(key, "value -->" + key);
        }
        st.print();
    }

    /**
     * 测试使用二叉树实现的符号表
     */
    public static void tesBinarySearchTree(){
        BinarySearchTreeBook<Integer,String> st = new BinarySearchTreeBook<>();
        for(int i=0;i<100;i++){
            int rand = new Random().nextInt(1000);
            st.put(rand,"value:"+rand);
        }
        Iterable<Integer> iterator = st.iterator(200,600);
        iterator.forEach(i->{
            System.out.print(i+",");
        });
    }
}
