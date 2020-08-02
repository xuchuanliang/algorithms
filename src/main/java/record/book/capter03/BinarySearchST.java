package record.book.capter03;

import record.U;

import java.util.Arrays;

/**
 * 二分查找（基于有序数组）
 * 使用一对平行数组，一个存储键，一个存储值
 * 键实现了Comparable接口，键按照顺序存放在数组中，该键对应的值存放在相同索引的
 * 优点是由于K实现了Comparable接口，所以只要保证K有序，那么就可以使用二分法查找对应的元素索引，最终一次性定位到value的位置，比较快
 * 缺点是维护数组有序，同时扩容效率低
 *
 *
 * 一般情况下二分查找都比顺序查找快得多，但是仍然存在明显的弊端：
 * 在键是随机排列的情况下，构造一个基于有序数组的符号表所需要访问数组的次数是数组长度的平方级别
 *
 * @param <K>
 * @param <V>
 */
public class BinarySearchST<K extends Comparable<K>, V> implements ST<K, V> {

    private K[] ks;
    private Object[] vs;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    public BinarySearchST() {
        this(DEFAULT_CAPACITY);
    }

    public BinarySearchST(int capacity) {
        ks = (K[]) new Comparable[capacity];
        vs = new Object[capacity];
    }

    @Override
    public void put(K k, V v) {
        int rank = rank(k);
        if (rank == -1) {
            //所有元素向后移动一位
            ensureCapacity(size + 1);
            int i = size - 1;
            while (i >= 0) {
                ks[i + 1] = ks[i];
                vs[i + 1] = vs[i];
                i--;
            }
            ks[0] = k;
            vs[0] = (Object) v;
            size++;
            return;
        }
        if (rank >= size) {
            //直接添加到最后
            ensureCapacity(size + 1);
            ks[size] = k;
            vs[size++] = (Object) v;
            return;
        }
        if (ks[rank].equals(k)) {
            //找到相同的key，替换v
            vs[rank] = (Object) v;
            return;
        }
        //插入到rank后面
        ensureCapacity(size + 1);
        int i = size - 1;
        while (i > rank) {
            //rank后面的所有元素向后移动一位
            ks[i + 1] = ks[i];
            vs[i + 1] = vs[i];
            i--;
        }
        ks[rank + 1] = k;
        vs[rank + 1] = (Object) v;
        size++;

    }

    @Override
    public V get(K k) {
        int i = indexOfK(k);
        if (-1 != i) {
            return (V) vs[i];
        }
        return null;
    }

    @Override
    public boolean contains(K k) {
        return -1 != indexOfK(k);
    }

    @Override
    public void delete(K k) {
        int i = rank(k);
        if (ks[i].equals(k)) {
            //将元素向前移动
            //为了防止删除的是最后一个元素，先将当前元素置空
            ks[i] = null;
            vs[i] = null;
            //逐个将该元素后面的元素覆盖当前元素
            while (++i < size - 1) {
                ks[i - 1] = ks[i];
                vs[i - 1] = vs[i];
            }
            size--;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println("key is :" + ks[i] + ",value is :" + vs[i]);
        }
    }

    /**
     * 采用二分查找定位key的位置，若不存在，则返回刚好小于key的位置
     * 返回K数组中小于等于key的索引位置
     * 若存在等于key的位置，则返回该位置
     * 若第一个元素比key还要大，则会返回-1
     * 若最后一个元素比key还要小，则会返回size()+1
     *
     * @param key
     * @return
     */
    private int rank(K key) {
        if (size <= 0) {
            return -1;
        }
        int left = 0;
        int right = size - 1;
        int mid = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (ks[mid].equals(key)) {
                //如果刚好找到
                return mid;
            } else if (U.less(ks[mid], key)) {
                //如果中间的比key小，则在右边
                left = mid + 1;
            } else {
                //否则中间的比key大，则在左边
                right = mid - 1;
            }
        }
        //代码执行到此处，说明未找到
        return U.less(ks[mid], key) ? mid : mid - 1;
    }

    /**
     * 获取k所在的索引位置
     *
     * @param k
     * @return
     */
    private int indexOfK(K k) {
        for (int i = 0; i < size; i++) {
            if (ks[i].equals(k)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 判断容量是否够用，如果不够，则将原数组扩大为原来的1.5倍
     *
     * @param targetSize
     * @return
     */
    private void ensureCapacity(int targetSize) {
        if (ks.length >= targetSize) {
            //容量还够
            return;
        }
        int newLength = ks.length + (ks.length >> 1);
        if (newLength < DEFAULT_CAPACITY) {
            newLength = DEFAULT_CAPACITY;
        }
        ks = Arrays.copyOf(ks, newLength);
        vs = Arrays.copyOf(vs, newLength);
    }
}
