package record.book.capter03;

import record.U;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 树中实现的二叉树
 * 对照{@link BinarySearchTree}
 */
public class BinarySearchTreeBook<K extends Comparable<K>, V> {

    private Node root;

    /**
     * 获取二叉树的节点个数
     *
     * @return
     */
    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (null == node) {
            return 0;
        }
        return node.N;
    }

    /**
     * 获取指定k的value值
     *
     * @param k
     * @return
     */
    public V get(K k) {
        return get(root, k);
    }

    private V get(Node node, K k) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(k)) {
            return node.value;
        }
        if (U.less(node.key, k)) {
            //比当前节点大，向右查询
            return get(node.right, k);
        } else {
            //否则比当前节点小，向左查询
            return get(node.left, k);
        }
    }

    /**
     * 向二叉树中存入新的节点，如果存在相同的k，则替换v
     *
     * @param k
     * @param v
     */
    public void put(K k, V v) {
        root = put(root, k, v);
    }

    private Node put(Node node, K k, V v) {
        if (null == node) {
            return new Node(k, v, 1);
        }
        if (node.key.equals(k)) {
            node.value = v;
        } else if (U.less(node.key, k)) {
            //k大于当前节点的key，放在右侧
            node.right = put(node.right, k, v);
        } else {
            //k小于当前节点的key，放在左侧
            node.left = put(node.left, k, v);
        }
        node.N = size(node.left) + size(node.right);
        return node;
    }

    /**
     * 获取最小的k，也就是获取最左侧节点的key
     *
     * @return
     */
    public K min() {
        if (null == root) {
            return null;
        }
        return min(root).key;
    }

    private Node min(Node node) {
        if (null == node) {
            return null;
        }
        if (null == node.left) {
            return node;
        } else {
            return node.left;
        }
    }

    /**
     * 获取当前树中的最大元素
     * @return
     */
    public K max(){
        if(null==root){
            return null;
        }
        return max(root).key;
    }

    private Node max(Node node){
        if(node.right==null){
            return node;
        }else{
            return max(node.right);
        }
    }

    /**
     * 获取小于等于k的最大元素
     *
     * @param k
     * @return
     */
    public K floor(K k) {
        Node node = floor(root, k);
        if (null != node) {
            return null;
        }
        return node.key;
    }

    private Node floor(Node node, K k) {
        if (null == node) {
            return null;
        }
        if (node.key.equals(k)) {
            return node;
        }
        if (!U.less(node.key, k)) {
            //k比node.key小，在左边，向左递归查找
            return floor(node.left, k);
        }
        //如果在右边，向右递归查询是否有小于k的节点，如果没有则当前节点是小于k的最大节点
        Node floor = floor(node.right, k);
        if (null == floor) {
            return node;
        } else {
            return floor;
        }
    }

    /**
     * 返回排名为k的节点
     *
     * @param k
     * @return
     */
    public K select(int k) {
        Node node = select(root, k);
        return null != node ? node.key : null;
    }

    private Node select(Node node, int k) {
        if (null == node) {
            return null;
        }
        int t = size(node.left);
        if (t == k) {
            return node;
        } else if (t > k) {
            //在左边
            return select(node.left, k);
        } else {
            //在右边
            return select(node.right, k - t - 1);
        }
    }

    /**
     * 返回以x为根结点的子树中小于x.key的键的数量
     *
     * @param k
     * @return
     */
    public int rank(K k) {
        return rank(root, k);
    }

    private int rank(Node node, K k) {
        if (node == null) {
            return 0;
        }
        if (node.key.equals(k)) {
            return size(node.left);
        }
        if (U.less(node.key, k)) {
            //比当前节点大，则向右找再加上左子树的数量
            return size(node) + rank(node.right, k);
        } else {
            //比当前节点小，则向左找
            return rank(node.left, k);
        }
    }

    /**
     * 删除最小的元素
     * 思路是：从左向下递归查询，直到某个节点的左子树为空，那么该节点就是最小的元素
     * 如果该节点的右节点为空，那么就直接将他的父节点的left指向空即可
     * 如果该节点的右节点非空，那个根据二叉树的添加规则说明他的右节点肯定小于他的父节点，否则就会在添加时被添加到他父节点的右边，所以直接将其父节点的left指向要删除节点的right
     *
     * @return
     */
    public void delMin() {
        root = delMin(root);
    }

    private Node delMin(Node node) {
        //如果left是空，那么直接返回该节点的右节点
        if (node.left == null) {
            return node.right;
        }
        //否则递归的向左查找
        node.left = delMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 删除最大元素
     * 思路是：逐步向右下查找，直到找到右节点为空的节点，那么认为该节点最大，则将该节点的父节点指向他的左节点，即没有元素指向他，完成删除最大元素
     */
    public void delMax() {
        root = delMax(root);
    }

    private Node delMax(Node node) {
        if (node.right == null) {
            //如果右侧节点为空，则说明当前元素最大，将其父节点右侧指向他的左节点
            return node.left;
        }
        node.right = delMin(node);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    /**
     * 删除指定元素，思路：
     * 1.找到指定要删除的元素k
     * 2.如果k无左子树，则直接return k的右子树
     * 3.如果k有左子树，无右子树，则直接return k的左子树
     * 4.如果k既有左子树，也有右子树，则删除并取出右子树中的最小节点min，将k节点父节点left指向min，将min的左子树指向k的左子树，将min的右子树指向k的右子树
     * 备注：实际上如果要删除的节点有左子树或右子树，实际上就是把左子树中最大的或者右子树中最小的拿出来替换当前要删除元素的位置
     *
     * @param k
     */
    public void delete(K k) {
        root = delete(root, k);
    }

    private Node delete(Node node, K k) {
        if (node == null) {
            return null;
        }
        if (node.key.equals(k)) {
            //如果当前元素是要删除的元素
            if(node.left==null){
                //左边为空
                return node.right;
            }else if(node.right==null){
                //左边不为空，但右边为空
                return node.left;
            }else{
                //左边不为空，右边也不为空
                //删除并获取右子树中最小的元素
                Node rightMin = delMin(node.right);
                //该元素左边指向要删除节点的左子树
                rightMin.left = node.left;
                //该元素右边指向要删除节点的右子树
                rightMin.right = node.right;
                return rightMin;
            }
        }
        //当前元素不是要删除的元素，则根据大小继续向右或向左查找
        if (U.less(node.key, k)) {
            //如果k比当前节点的key大，则向右查找
            return delete(node.right, k);
        } else {
            return delete(node.left, k);
        }
    }

    /**
     * 遍历二叉树：按照从小到大的方式遍历
     */
    public void printLeft(){
        printLeft(root);
    }

    private void printLeft(Node node){
        //如果左边有，那先输出左边
        if(node.left!=null){
            printLeft(node.left);
        }
        //遍历完左边后，打印当前节点
        System.out.print(""+node.key+"--"+node.value+"\t");
        //打印完中间后，打印右子树
        if(null!=node.right){
            printLeft(node.right);
        }
    }

    /**
     * 提供遍历方法
     * @return
     */
    public Iterable<K> iterator(){
        return iterator(min(),max());
    }

    /**
     * 遍历指定范围的树
     * @param min
     * @param max
     * @return
     */
    public Iterable<K> iterator(K min,K max){
        Queue<K> queue = new LinkedList<>();
        keys(root,min,max,queue);
        return queue;
    }

    /**
     * 将树中的所有在min和max范围内的节点key放在queue中
     * @param node
     * @param min
     * @param max
     * @param queue
     */
    private void keys(Node node,K min,K max,Queue<K> queue){
        if(null==node){
            return;
        }
        if(U.less(min,node.key) && U.less(node.key,max)){
            queue.offer(node.key);
        }
        keys(node.left,min,max,queue);
        keys(node.right,min,max,queue);
    }

    /**
     * 二叉树的内部数据结构
     */
    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private int N;

        public Node(K key, V value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }
}
