package record.hanshunping.linkedList03;

/**
 * 03.
 * 双向链表
 */
public class DoubleLinkedListDemo {

    /**
     * 单链表
     */
    private static class SimpleLinkedList {
        /**
         * 链表的头结点，只存储指向第一个节点的地址，不保留任何信息
         */
        private HeroNodeDouble head = new HeroNodeDouble(0, null, null);

        /**
         * 双向链表添加与单链表添加的唯一区别在于需要将新添加节点的pre指向前一个节点
         *
         * @param heroNode
         */
        public void addHero(HeroNodeDouble heroNode) {
            HeroNodeDouble temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            //代码执行到这里说明当前temp的next是null，那么将其next域指向形参的节点即可
            //将temp节点指向当前节点
            temp.next = heroNode;
            //将新添加节点的pre指向temp
            heroNode.pre=temp;
        }

        /**
         * 根据英雄编号查询某位英雄
         *
         * @param no
         * @return
         */
        public HeroNodeDouble findHero(int no) {
            //从head开始遍历，查找编号为no的英雄
            HeroNodeDouble temp = head;
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
         * 双向链表的删除只需要找到需要删除的节点，并且将他的前后两个节点链接到一起即可
         *
         * @param no
         */
        public void delHeroNode(int no) {
            HeroNodeDouble temp = head.next;
            while (temp!=null){
                if(temp.no==no){
                    //找到需要删除的节点
                    //将他的前后两个节点链接到一起即可
                    temp.pre.next = temp.next;
                    temp.next.pre = temp.pre;
                    temp = null;
                }
            }
        }

        public void list(){
            System.out.println();
            HeroNodeDouble temp = head;
            while (temp.next!=null){
                System.out.print(temp.next);
                temp = temp.next;
            }
            System.out.println();
        }

        public HeroNodeDouble getHead() {
            return head;
        }

        /**
         * 获取链表的长度
         * @return
         */
        public int size(){
            int length = 0;
            HeroNodeDouble temp = head.next;
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
    private static class HeroNodeDouble {
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
         * 指向当前节点的后一个节点
         */
        private HeroNodeDouble next;

        /**
         * 指向当前节点的前一个节点
         */
        private HeroNodeDouble pre;

        public HeroNodeDouble(int no, String name, String nickName) {
            this.no = no;
            this.name = name;
            this.nickName = nickName;
        }

        public void setNext(HeroNodeDouble next) {
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
