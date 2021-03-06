package record.hanshunping.hash09;

import java.util.Arrays;
import java.util.Objects;

/**
 * 哈希表，是一种数据结构
 * 散列表（Hash table， 也叫哈希表） ， 是根据关键码值(Key value)而直接进行访问的数据结构。
 * 也就是说， 它通过把关键码值映射到表中一个位置来访问记录， 以加快查找的速度。
 * 这个映射函数叫做散列函数， 存放记录的数组叫做散列表。
 * 哈希表包含了多个链表
 */
public class HashTb {

    public static void main(String[] args) {
        HashTable hashTable = new HashTable(10);
        for(int i=0;i<100;i++){
            hashTable.addNode(i,"松江"+i);
        }
        System.out.println(hashTable);
    }

    /**
     * 哈希表
     */
    private static class HashTable {
        NodeList[] element;

        public HashTable(int size) {
            element = new NodeList[size];
            //初始化每个节点
            for (int i = 0; i < size; i++) {
                element[i] = new NodeList();
            }
        }

        /**
         * 添加元素
         *
         * @param id
         * @param name
         */
        public void addNode(int id, String name) {
            element[getIndex(id)].add(id, name);
        }

        /**
         * 删除节点
         *
         * @param id
         */
        public void delNode(int id) {
            element[getIndex(id)].del(id);
        }

        /**
         * 获取节点
         *
         * @param id
         * @return
         */
        public Node getNode(int id) {
            return element[getIndex(id)].get(id);
        }

        /**
         * 修改节点
         *
         * @param id
         * @param name
         */
        public void modifyNode(int id, String name) {
            element[getIndex(id)].modify(id, name);
        }

        /**
         * 根据id获取该id的元素应该落在哪个链表上
         *
         * @param id
         * @return
         */
        private int getIndex(int id) {
            //采用简单取模方式
            return id % element.length;
        }

        @Override
        public String toString() {
            return "HashTable{" +
                    "element=" + Arrays.toString(element) +
                    '}';
        }
    }

    /**
     * 链表
     */
    private static class NodeList {
        private Node head;

        /**
         * 添加元素，添加到尾部
         */
        public void add(int id, String name) {
            if (head == null) {
                //如果第一个头元素为空，则添加到头元素中
                head = new Node(id, name);
            } else {
                //头元素不为空，则添加到末尾
                Node temp = head;
                while (temp.next != null) {
                    temp = temp.next;
                }
                //temp的next是空，添加到temp的next
                temp.setNext(new Node(id, name));
            }
        }

        /**
         * 删除元素
         */
        public void del(int id) {
            if (head == null) return;
            if (head.getId() == id) head = head.next;
            Node temp = head;
            while (temp.next != null && temp.next.id != id) {
                temp = temp.next;
            }
            //如果temp.next不是空，则删除
            if (null != temp.next) {
                temp.next = temp.next.next;
            }
        }

        /**
         * 查找元素
         */
        public Node get(int id) {
            Node temp = head;
            while (temp != null) {
                if (id == temp.id) {
                    return temp;
                } else {
                    temp = temp.next;
                }
            }
            return null;
        }

        /**
         * 修改元素
         */
        public void modify(int id, String name) {
            Node node = get(id);
            if (Objects.nonNull(node)) {
                node.setName(name);
            }
        }

        @Override
        public String toString() {
            return "NodeList{" +
                    "head=" + head +
                    '}';
        }
    }

    /**
     * 链表节点
     */
    private static class Node {
        private int id;
        private String name;
        private Node next;

        public Node(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", next=" + next +
                    '}';
        }
    }
}
