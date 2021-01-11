package record.hanshunping.linkedList;

/**
 * 链表的特点：有序、不连续的线性存储结构
 * 带头结点的单链表：实现对水浒传中的英雄的管理，提供新增、修改、删除、查询功能
 */
public class SimpleLinkedListDemo {

    public static void main(String[] args) {
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
            HeroNode temp = head;
            while (temp.next!=null){
                System.out.print(temp.next);
                temp = temp.next;
            }
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
