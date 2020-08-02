package record.book.capter03;

import java.util.Iterator;

/**
 * 符号表的主要目的是将一个键和一个值联系起来，并且能够将一个键值对插入到符号表中，且能够高效的根据一个键查到对应的值
 * @param <K>
 * @param <V>
 */
public interface ST<K,V> {
    /**
     * 向字典中存入一个元素
     * 如果k已经存在，则替换
     * @param k
     * @param v
     */
    void put(K k,V v);

    /**
     * 从字典中获取一个元素
     * @param k
     * @return
     */
    V get(K k);

    /**
     * 判断字典中是否包含键为K的值
     * @param k
     * @return
     */
    boolean contains(K k);

    /**
     * 删除键为K的值
     * @param k
     */
    void delete(K k);

    /**
     * 判断当前字典是否为空
     * @return
     */
    boolean isEmpty();

    /**
     * 判断当前字典的大小
     * @return
     */
    int size();

    /**
     * 打印所有的键值对
     */
    void print();
}
