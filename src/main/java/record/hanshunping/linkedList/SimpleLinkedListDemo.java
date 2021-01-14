package record.hanshunping.linkedList;

import java.util.Stack;

/**
 * 01.
 * 链表的特点：有序、不连续的线性存储结构
 * 带头结点的单链表：实现对水浒传中的英雄的管理，提供新增、修改、删除、查询功能
 */
public class SimpleLinkedListDemo {

    public static void main(String[] args) {
        testSimpleLinkedListAlgorithms();
    }

    /**
     * 测试简单链表的普通api
     */
    public static void testSimpleLinkedList(){
        HeroNode h1 = new HeroNode(1,"宋义","及时雨");
        HeroNode h2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode h3 = new HeroNode(3,"吴用","智多星");
        HeroNode h4 = new HeroNode(4,"林冲","豹子头");
        SimpleLinkedList simpleLinkedList = new SimpleLinkedList();
        simpleLinkedList.addHero(h1);
        simpleLinkedList.addHero(h2);
        simpleLinkedList.addHero(h3);
        simpleLinkedList.addHero(h4);
        simpleLinkedList.delHeroNode(2);
        simpleLinkedList.addHero(new HeroNode(5,"徐传良","ant"));
        simpleLinkedList.addHero(new HeroNode(6,"大傻子","snail"));
        simpleLinkedList.delHeroNode(4);
        simpleLinkedList.list();
        System.out.println(simpleLinkedList.findHero(2));
        System.out.println(simpleLinkedList.findHero(6));
    }

    /**
     * 测试算法
     */
    public static void testSimpleLinkedListAlgorithms(){
        HeroNode h1 = new HeroNode(1,"宋义","及时雨");
        HeroNode h2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode h3 = new HeroNode(3,"吴用","智多星");
        HeroNode h4 = new HeroNode(4,"林冲","豹子头");
        SimpleLinkedList simpleLinkedList = new SimpleLinkedList();
        simpleLinkedList.addHero(h1);
        simpleLinkedList.addHero(h2);
        simpleLinkedList.addHero(h3);
        simpleLinkedList.addHero(h4);

        //获取长度
        System.out.println(getLength(simpleLinkedList));

        //获取倒数第二个节点信息
        System.out.println(findLastIndexNode(simpleLinkedList,2));

        //翻转链表
        simpleLinkedList.list();
        reverse(simpleLinkedList);
        simpleLinkedList.list();

        //逆序打印队列
        reversePrint(simpleLinkedList);
    }

    /**
     * 获取单链表的有效节点数量（如果是带头结点的链表，则不需要统计头结点）
     * @return
     */
    public static int getLength(SimpleLinkedList simpleLinkedList){
        if(null==simpleLinkedList || simpleLinkedList.getHead()==null){
            //空链表
            return 0;
        }
        HeroNode head = simpleLinkedList.getHead();
        int length = 0;
        HeroNode temp = head.next;
        while (temp!=null){
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 查找倒数第i个节点
     * 假设一共有10个节点，倒数第3个节点就是，10-3即第七个 的next节点
     * @return
     */
    public static HeroNode findLastIndexNode(SimpleLinkedList simpleLinkedList,int lastIndex){
        if(lastIndex<=0 || lastIndex>simpleLinkedList.size()){
            //说明需要查询的节点在范围之外
            return null;
        }
        if(simpleLinkedList==null || simpleLinkedList.getHead()==null){
            return null;
        }
        int totalNode = simpleLinkedList.size();
        int distance = totalNode-lastIndex;
        HeroNode temp = simpleLinkedList.head.next;
        while (temp!=null && distance!=0){
            distance--;
            temp = temp.next;
        }
        //要么未找到，找到了就是temp.next
        return null!=temp ? temp : null;
    }

    /**
     * 翻转列表，思路如下：
     * 首先创建一个临时的头结点，用作临时链表的head
     * 从原有链表的第一个节点开始遍历，每遍历一个节点就将其插入到临时链表的第一的位置，
     * 这样当原链表遍历完成后，则临时链表的头节点指向了与原链表顺序相反的链表
     * 最后将head指向临时链表的第一个节点，实现翻转
     * @param simpleLinkedList
     */
    public static void reverse(SimpleLinkedList simpleLinkedList){
        if(simpleLinkedList==null || simpleLinkedList.size()<=1){
            //如果链表为空或一个节点都没有或只有一个节点则不需要翻转
            return;
        }
        HeroNode tempHead = new HeroNode(0,null,null);
        HeroNode temp = simpleLinkedList.getHead().next;
        while (temp!=null){
            //只要遍历出来的节点不为空，则将其插入到临时链表的队首
            //首先将当前节点引用存储起来
            HeroNode h = temp;
            //将temp指向下一个节点
            temp = temp.next;
            //将h插入到临时节点的队首：即将h.next指向临时链表的第一个节点，并将临时节点的head指向当前节点
            h.next = tempHead.next;
            tempHead.next = h;
        }
        //执行到此 翻转完毕，需要将原head指向翻转后的第一个节点
        simpleLinkedList.getHead().next=tempHead.next;
    }

    /**
     * 逆序打印队列，思路：利用一个栈的结构，将队列的元素从头到尾压入栈，然后在从栈顶逐步弹出打印即可
     * @param simpleLinkedList
     */
    public static void reversePrint(SimpleLinkedList simpleLinkedList){
        if(null==simpleLinkedList || null==simpleLinkedList.getHead()){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = simpleLinkedList.getHead().next;
        while (temp!=null){
            stack.push(temp);
            temp = temp.next;
        }
        while (!stack.empty()){
            System.out.println(stack.pop());
        }
    }

    /**
     * 单链表
     */
    private static class SimpleLinkedList {
        /**
         * 链表的头结点，只存储指向第一个节点的地址，不保留任何信息
         */
        private HeroNode head = new HeroNode(0, null, null);

        /**
         * 添加节点的思路：从head开始向后寻找，直到找到节点的next域是null，则表示找到的结尾，则将其next域指向新加入的节点
         *
         * @param heroNode
         */
        public void addHero(HeroNode heroNode) {
            HeroNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            //代码执行到这里说明当前temp的next是null，那么将其next域指向形参的节点即可
            temp.next = heroNode;
        }

        /**
         * 根据英雄编号查询某位英雄
         *
         * @param no
         * @return
         */
        public HeroNode findHero(int no) {
            //从head开始遍历，查找编号为no的英雄
            HeroNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
                if (temp.no == no) {
                    return temp;
                }
            }
            //代码执行到这里说明未查询到
            return null;
        }

        /**
         * 根据编号删除英雄节点
         *
         * @param no
         */
        public void delHeroNode(int no) {
            HeroNode temp = head;
            while (temp.next != null) {
                if(temp.next.no==no){
                    //当前节点的下一个节点就是需要删除的节点
                    //直接将当前节点的next域指向要删除节点的next域即可
                    temp.next = temp.next.next;
                    break;
                }else{
                    temp = temp.next;
                }
            }
        }

        public void list(){
            System.out.println();
            HeroNode temp = head;
            while (temp.next!=null){
                System.out.print(temp.next);
                temp = temp.next;
            }
            System.out.println();
        }

        public HeroNode getHead() {
            return head;
        }

        /**
         * 获取链表的长度
         * @return
         */
        public int size(){
            int length = 0;
            HeroNode temp = head.next;
            while (temp!=null){
                length++;
                temp = temp.next;
            }
            return length;
        }
    }

    /**
     * 表示英雄的节点
     */
    private static class HeroNode {
        /**
         * 英雄的编号
         */
        private int no;
        /**
         * 英雄的名称
         */
        private String name;
        /**
         * 英雄的昵称
         */
        private String nickName;
        /**
         * 因为是链表，存储着下一个节点的地址，如果没有则为null
         */
        private HeroNode next;

        public HeroNode(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        public void setNext(HeroNode next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' +
                    '}';
        }
    }
}
