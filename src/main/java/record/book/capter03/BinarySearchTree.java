package record.book.capter03;

import record.U;

/**
 * 二叉树
 * 二叉树就是每个节点含有两个链接的链表结构。
 * 二叉树由节点构成，结点包含的链接可以为空（ null）或者指向其他结点。在二叉树中，每个结点只能有一个父结点（只有一个例外，也就
 * 是根结点，它没有父结点），而且每个结点都只有左右两个链接，分别指向自己的左子结点和右子结点。尽管链接指向的是结点，但我们可以
 * 将每个链接看做指向了另一棵二叉树，而这棵树的根结点就是被指向的结点。
 * 定义：
 * 一棵二叉查找树（ BST Binary Search Tree）是一棵二叉树，其中每个结点都含有一个 Comparable 的键（以及相关联的值）且每个结点的键都大于其左子树中的
 * 任意结点的键而小于右子树的任意结点的键。
 * <p>
 * 二叉查找树的查找代码和二分查找一样简单，并且插入的实现难度与查找差不多
 * 插入操作：当查找一个不存在于树中的结点并结束于一条空链接时，我们需要做的就是将链接指向一个含有被查找的键的新结点
 * <p>
 * 使用二叉查找树的算法的运行时间取决于树的形状，而树的形状又取决于键被插入的先后顺序。在最好的情况下，
 * 一棵含有 N 个结点的树是完全平衡的，每条空链接和根结点的距离都为～ lgN。在最坏的情况下，搜索路径上可能有 N个结点。
 * <p>
 * 二叉树的缺点是形状取决于元素的插入顺序，不会自平衡，极端情况下会退化成为一个链表。
 * 二叉查找树和快速排序几乎就是“双胞胎”。树的根结点就是快速排序中的第一
 * 个切分元素（左侧的键都比它小，右侧的键都比它大），而这对于所有的子树同样适用，这和快速排序中对子数组的递
 * 归排序完全对应。
 */
public class BinarySearchTree<K extends Comparable<K>, V> implements ST<K, V> {

    private Node root;

    @Override
    public void put(K k, V v) {
        Node node = get(root, k);
        if (null != node) {
            node.v = v;
            return;
        }
        //找不到，则加入到树中
        if (root == null) {
            root = new Node(k, v, 1);
            return;
        }
        put(root, k, v);
    }

    public void put(Node node, K k, V v) {
        node.N = node.N + 1;
        if (U.less(node.k, k)) {
            //放在右边
            if (node.right == null) {
                node.right = new Node(k, v, 1);
            } else {
                put(node.right, k, v);
            }
        } else {
            //放在左边
            if (node.left == null) {
                node.left = new Node(k, v, 1);
            } else {
                put(node.left, k, v);
            }
        }
    }

    @Override
    public V get(K k) {
        Node n = get(root, k);
        return null == n ? null : n.v;
    }

    /**
     * 从指定节点递归树查找以键为k对应的值
     *
     * @param node
     * @param k
     * @return
     */
    private Node get(Node node, K k) {
        if (null == node) {
            return null;
        }
        if (node.k.equals(k)) {
            return node;
        }
        if (U.less(node.k, k)) {
            //如果比node节点大，则向右找，否则向左找
            return get(node.right, k);
        } else {
            return get(node.left, k);
        }
    }

    @Override
    public boolean contains(K k) {
        return get(k) != null;
    }

    @Override
    public void delete(K k) {

    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return sizeOf(root);
    }

    private int sizeOf(Node node) {
        if (null == node) {
            return 0;
        } else {
            return node.N;
        }
    }

    @Override
    public void print() {
        print(root);
    }

    private void print(Node node) {
        //递归打印左边元素
        if (null != node.left) {
            print(node.left);
        }
        //打印自身
        System.out.print(node.k + ",");
        //递归打印右边元素
        if (null != node.right) {
            print(node.right);
        }
    }

    /**
     * 获取最小的k
     */
    public K min(){
        if(null==root){
            return null;
        }
        return min(root);
    }

    private K min(Node node){
        if(null!=node.left){
            return min(node.left);
        }else{
            return node.k;
        }
    }

    /**
     * 获取最大的k
     * @return
     */
    public K max(){
        if(root == null){
            return null;
        }
        return max(root);
    }

    private K max(Node node){
        if(node.right!=null){
            return max(node.right);
        }else {
            return node.k;
        }
    }

    /**
     * 删除树中的最小元素
     * 思路如下：
     * 情况一：没有左子树，那么最小的元素就是当前根节点，直接将根节点删除，原根节点的右节点为新的根节点
     * 情况二：有左子树，那么要删除最左侧的元素；
     * 如果最左侧的元素没有右子树，那么直接将最左侧元素删除
     * 如果最左侧元素有右子树，那么根据二叉树的添加规则可知待删除节点的右节点肯定小于他的父节点，因为若大于他的父节点，那么肯定会被添加到他的父节点右侧，不会在左侧树中
     * 所以将待删除节点的父节点的left指向删除节点的右子树
     */
    public void delMin(){
        if(null==root){
            return;
        }
        if(null==root.left){
            root = root.right;
            return;
        }
        delMin(root);
    }

    private void delMin(Node node){
        if(node.left.left==null){
            node.left = node.left.right;
        }else{
            delMin(node.left);
        }
    }

    /**
     * 删除最大的元素
     * 思路如下：
     * 情况一：如果当前根节点没有右子树，那么根节点就是最大的节点，那么删除根节点
     * 情况二：如果当前节点有右节点，那么删除最右侧的节点，如果最右侧的节点有左节点，那么根据二叉树的插入步骤可知，待删除元素的左节点肯定大于待删除元素父节点，否则在新增时应该插入到
     * 待删除元素父节点的左侧，而不会插入到待删除元素的下面
     *
     */
    public void delMax(){
        if(root == null){
            return;
        }
        if(root.right==null){
            root = root.left;
            return;
        }
        delMax(root);
    }

    private void delMax(Node node){
        if(node.right.right==null){
            node.right = node.right.right;
        }else{
            delMax(node.right);
        }
    }

    private class Node {
        /**
         * 键
         */
        private K k;
        /**
         * 值
         */
        private V v;
        /**
         * 左边节点
         */
        private Node left;
        /**
         * 右边节点
         */
        private Node right;
        /**
         * 以该节点为根的子树中的节点总数
         */
        private int N;

        public Node(K k, V v, int n) {
            this.k = k;
            this.v = v;
            N = n;
        }
    }
}
