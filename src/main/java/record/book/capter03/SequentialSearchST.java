package record.book.capter03;

import java.util.Objects;

/**
 * 符号表/字典  基于无序链表的实现
 * <p>
 * 顺序查找（基于无序链表）
 * <p>
 * 顺序查找的效率是N/2,是比较低的，当存在大量数据时，性能达不到要求
 * 缺点是查找比较慢，要从链表的头开始遍历
 *
 * @param <K>
 * @param <V>
 */
public class SequentialSearchST<K, V> implements ST<K, V> {

    /**
     * 链表的首节点
     */
    private Node<K, V> first;
    private int size = 0;

    /**
     * 字典的底层数据结构实现，采用无需链表实现
     *
     * @param <K>
     * @param <V>
     */
    private class Node<K, V> {
        private K k;
        private V v;
        private Node<K, V> next;

        public Node(K k, V v, Node<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }
    }

    @Override
    public void put(K k, V v) {
        for (Node<K, V> node = first; node != null; node = node.next) {
            if (node.k.equals(k)) {
                node.v = v;
                return;
            }
        }
        //如果不存在，则新增，插入到头部
        first = new Node<>(k, v, first);
        size++;
    }

    @Override
    public V get(K k) {
        for (Node<K, V> node = first; node != null; node = node.next) {
            if (node.k.equals(k)) {
                return node.v;
            }
        }
        return null;
    }

    @Override
    public boolean contains(K k) {
        for (Node<K, V> node = first; node != null; node = node.next) {
            if (node.k.equals(k)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delete(K k) {
        if (first.k.equals(k)) {
            first = first.next;
            size--;
        }
        for (Node<K, V> node = first; node.next != null; node = node.next) {
            if (node.next.k.equals(k)) {
                node.next = node.next.next;
                size--;
                return;
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(first);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void print() {
        for (Node<K, V> node = first; node != null; node = node.next) {
            System.out.println("key is :" + node.k + ";value is :" + node.v);
        }
    }
}
