package record.book.capter03;

import record.U;

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
     * @param k
     * @param v
     */
    public void put(K k,V v){
        root = put(root,k,v);
    }

    private Node put(Node node,K k,V v){
        if(null==node){
            return new Node(k,v,1);
        }
        if(node.key.equals(k)){
            node.value = v;
        }else if(U.less(node.key,k)){
            //k大于当前节点的key，放在右侧
            node.right = put(node.right,k,v);
        }else {
            //k小于当前节点的key，放在左侧
            node.left = put(node.left,k,v);
        }
        node.N = size(node.left)+size(node.right);
        return node;
    }

    /**
     * 获取最小的k，也就是获取最左侧节点的key
     * @return
     */
    public K min(){
        if(null==root){
            return null;
        }
        return min(root).key;
    }

    private Node min(Node node){
        if(null==node){
            return null;
        }
        if(null==node.left){
            return node;
        }else{
            return node.left;
        }
    }

    /**
     * 获取小于等于k的最大元素
     * @param k
     * @return
     */
    public K floor(K k){
        Node node = floor(root,k);
        if(null!=node){
            return null;
        }
        return node.key;
    }

    private Node floor(Node node,K k){
        if(null==node){
            return null;
        }
        if(node.key.equals(k)){
            return node;
        }
        if(!U.less(node.key,k)){
            //k比node.key小，在左边，向左递归查找
            return floor(node.left,k);
        }
        //如果在右边，向右递归查询是否有小于k的节点，如果没有则当前节点是小于k的最大节点
        Node floor = floor(node.right, k);
        if(null==floor){
            return node;
        }else{
            return floor;
        }
    }

    /**
     * 返回排名为k的节点
     * @param k
     * @return
     */
    public K select(int k){
        Node node = select(root,k);
        return null!=node ? node.key : null;
    }

    private Node select(Node node,int k){
        if(null==node){
            return null;
        }
        int t = size(node.left);
        if(t==k){
            return node;
        }else if(t>k){
            //在左边
            return select(node.left,k);
        }else{
            //在右边
            return select(node.right,k-t-1);
        }
    }

    /**
     * 返回以x为根结点的子树中小于x.key的键的数量
     * @param k
     * @return
     */
    public int rank(K k){
        return rank(root,k);
    }

    private int rank(Node node,K k){
        if(node==null){
            return 0;
        }
        if(node.key.equals(k)){
            return size(node.left);
        }
        if(U.less(node.key,k)){
            //比当前节点大，则向右找再加上左子树的数量
            return size(node)+rank(node.right,k);
        }else{
            //比当前节点小，则向左找
            return rank(node.left,k);
        }
    }

    /**
     * 删除最小的元素
     * 思路是：从左向下递归查询，直到某个节点的左子树为空，那么该节点就是最小的元素
     * 如果该节点的右节点为空，那么就直接将他的父节点的left指向空即可
     * 如果该节点的右节点非空，那个根据二叉树的添加规则说明他的右节点肯定小于他的父节点，否则就会在添加时被添加到他父节点的右边，所以直接将其父节点的left指向要删除节点的right
     * @return
     */

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
