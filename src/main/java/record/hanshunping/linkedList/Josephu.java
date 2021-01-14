package record.hanshunping.linkedList;

/**
 * 约瑟夫问题：设编号为 1， 2， … n 的 n 个人围坐一圈， 约定编号为 k（1<=k<=n） 的人从 1 开始报数， 数到
 * m 的那个人出列， 它的下一位又从 1 开始报数， 数到 m 的那个人又出列， 依次类推， 直到所有人出列为止， 由此
 * 产生一个出队编号的序列。
 * 例如：1、2、3、4、5组成环形，从1开始报数，报到2的出列，则一次是2、4、1、5，最后留了一个3
 * 解决思路：首先有一个单向环形队列、有一个head指针指向了第一个元素，然后当从第k个元素开始数时，即每循环到m时，此时将当前元素出队，
 * 并将该元素的前一个元素指向后一个元素，若队列只剩下一个元素时，则表示游戏结束
 */
public class Josephu {

    public static void main(String[] args) {
//        SingleLinkedList singleLinkedList = new SingleLinkedList();
        //初始化长度为5的环形队列
//        Person person = singleLinkedList.initSingleLinkedList(5);
        //测试约瑟夫问题
        josephu(50,10,5);
    }

    /**
     * @param linkedLength 环形队列的长度
     * @param startNum     开始报数的编号
     * @param distance     报数到多少出队
     */
    public static void josephu(int linkedLength, int startNum, int distance) {
        if (linkedLength <= 1 || startNum <= 0 || startNum > linkedLength) {
            throw new IllegalArgumentException("参数错误");
        }
        //构建环形队列
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.initSingleLinkedList(linkedLength);
        //定义两个指针，分别指向当前报数的节点以及他的前一个节点，主要是在删除当前报数节点时，需要能够将他的前一个节点与下一个节点连接起来
        //首先默认cur指向第一个节点；pre指向最后一个节点，即first的第一个节点
        Person cur = singleLinkedList.first;
        //为pre赋值为最后一个节点
        Person pre = singleLinkedList.first;
        while (pre.getNext() != singleLinkedList.first) {
            //只要pre.next不是first，说明还未到最后一个节点
            pre = pre.next;
        }
        //将pre和cur分别指向第startNum-1和startNum节点，表示从该节点开始报数
        for (int i = 1; i < startNum; i++) {
            cur = cur.next;
            pre = pre.next;
        }

        //开始报数，并每隔distance-1数量就出队
        //结束调试是还剩最后一个节点，即cur和pre重合的情况
        while (cur != pre) {
            for (int i = 1; i < distance; i++) {
                //将pre和cur向前移动，表示报数了,由于cur算作一次报数，所以只需要移动distance-1次即可
                cur = cur.next;
                pre = pre.next;
            }
            //将cur出队，也就是将cur的前一个节点与后一个节点连接起来，表示cur出队
            System.out.println("需要出队的是："+cur);
            //首先将pre的next指向cur的next
            pre.next = cur.next;
            //其次将cur指向cur的next，此时就已经将cur的前一个和后一个节点连接起来
            cur = cur.next;
        }
        //循环出队完成后，只剩下一个节点
        System.out.println("最终剩下的节点是："+cur);
    }


    /**
     * 单项环形队列，由于本例的重点是解决约瑟夫问题，而不是关于环形队列的数据结构，此时我们不考试实现复杂的队列api
     */
    private static class SingleLinkedList {
        //指向第一个节点的指针
        private Person first;

        /**
         * 初始化指定长度的队列，在不断增加节点的同时，总是将最后节点的next指向first指向的元素
         */
        public Person initSingleLinkedList(int length) {
            if (length <= 1) {
                throw new IllegalArgumentException("长度不能小于1");
            }
            //记录当前初始化的节点指针，也就是最后一个节点，用来继续向其后面添加元素
            Person cur = null;
            for (int i = 1; i <= length; i++) {
                //初始化元素
                if (i == 1) {
                    cur = new Person(i);
                    //初始化第一个元素时，需要将first指向第一个元素
                    first = cur;
                    cur.setNext(first);
                    continue;
                }
                //当不是第一个元素时，需要将cur.next指向最新的指针，并且再将最后指针的next指向first
                cur.setNext(new Person(i));
                cur = cur.getNext();
                cur.setNext(first);
            }
            return first;
        }
    }

    /**
     * 单项环形队列的节点数据结构，表示一个人
     */
    private static class Person {
        private int no;
        private Person next;

        public Person(int no) {
            this.no = no;
        }

        public void setNext(Person next) {
            this.next = next;
        }

        public Person getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "no=" + no +
                    '}';
        }
    }
}
