package record.hanshunping.linkedList;

/**
 * 带有顺序的链表：根据英雄的no按照顺序将英雄插入到指定的位置，如果有这个排名，则添加失败并给出提示
 */
public class SortedLinkedListDemo {

    public static void main(String[] args) {
        HeroNode h2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode h3 = new HeroNode(3,"吴用","智多星");
        HeroNode h1 = new HeroNode(1,"宋义","及时雨");
        HeroNode h4 = new HeroNode(4,"林冲","豹子头");
        SortedLinkedList simpleLinkedList = new SortedLinkedList();
        simpleLinkedList.addHero(h4);
        simpleLinkedList.addHero(h2);
        simpleLinkedList.addHero(h1);
        simpleLinkedList.addHero(h3);
        simpleLinkedList.list();
    }

    /**
     * 单链表
     */
    private static class SortedLinkedList {
        /**
         * 链表的头结点，只存储指向第一个节点的地址，不保留任何信息
         */
        private HeroNode head = new HeroNode(0, null, null);

        /**
         * 添加节点的思路：从头结点开始寻找，找到第一个大于当前节点no的节点，并将新的节点插入到他的前面,如果找到最后next于为空还未找到，则插入到最后
         *
         * @param heroNode
         */
        public void addHero(HeroNode heroNode) {
            HeroNode temp = head;
            while (temp.next!=null){
                if(temp.next.no>heroNode.no || temp.next==null){
                    //如果当前节点的下一个节点no大于要插入节点的no，则说明将新的节点插入到当前节点及其下一个节点之间即可
                    //如果当前节点的下一个节点为空，则说明已经找到队尾还未出现比要插入节点no大的，则将要插入节点插入到最后
                    break;
                }
                if(temp.next.no==heroNode.no){
                    //找到了相同的，不允许再插入
                    throw new RuntimeException("已经存在"+heroNode);
                }
                //如果未找到，则继续向下找
                temp = temp.next;
            }
            if(temp.next==null){
                //插入队尾
                temp.next = heroNode;
            }else{
                //插入temp及temp.next之间
                heroNode.next=temp.next;
                temp.next=heroNode;
            }
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
