package record.book.capter03.quarter3;

/**
 * 红黑查找树
 * 红黑二叉查找树背后的基本思想是用标准的二叉查找树（完全由 2- 结点构成）和一些额外的信息（替换３ - 结点）来表示 2-3树。
 * 我们将树中的链接分为两种类型： 红链接将两个 2- 结点连接起来构成一个 3- 结点， 黑链接则是 2-3 树中的普通链接。
 * 确切地说，我们将 3- 结点表示为由一条左斜的红色链接（两个 2-结点其中之一是另一个的左子结点）相连的两个 2- 结点。
 * 这种表示法的一个优点是，我们无需修改就可以直接使用标准二叉查找树的 get() 方法。对于任意的 2-3 树，只要对结点进行转换，
 * 我们都可以立即派生出一棵对应的二叉查找树。我们将用这种方式表示 2-3 树的二叉查找树称为红黑二叉查找树（以下简称为红黑树）。
 *
 * 红黑树的另外一种定义：
 * 红黑树的另一种定义是含有红黑链接并满足下列条件的二叉查找树：
 * 1.红链接均为左链接；
 * 2.没有任何一个结点同时和两条红链接相连；
 * 3.该树是完美黑色平衡的，即任意空链接到根结点的路径上的黑链接数量相同。
 *
 * 关于红黑树我们可以这样理解：
 * 在前面一节的平衡二叉树中指出了二叉查找树的弊端，树的构造与插入元素的顺序关系比较大，树的结构有比较大的概率出现不平衡的问题，
 * 即某一条腿非常长，也就是比较深，并且树中某个元素的查找效率又与树的深度有比较大的关系，树越深则查找次数越多，也就意味着查找效率越低。
 * 因此提出了能够自动平衡的树，即2-3查找树。
 * 2-3查找树的特点是每个节点最大有两个元素，三条腿。这样提高了固定层数下树存储元素的数量，并且当在2-3查找树下，插入元素如果插入到了2个元素的节点中，
 * 该节点会临时变为3个元素并且紧接着分裂
 * 总的来说二叉查找树与2-3查找树最大的区别是二叉树的增长是自上向下生成，2-3查找树的增长是自下向上生长，并且2-3查找树会自平衡，在插入元素的过程中不会导致树的层级过多以及畸形
 * 红黑树就是2-3查找树的一种，只不过红黑树中每个节点不是存储2个元素3条边，还是普通的1个节点2各边，并且使用红色的边来表示红边的连的两个节点是2-3类型的节点，也就是可以将红边相连
 * 的两个节点看成是2-3查找树中的一个节点，黑色边的节点是普通的二叉查找树节点。
 *
 * 红黑树、2-3平衡查找树、二叉查找树三者之间的关系理解：
 * 如果我们将一棵红黑树中的红链接画平，那么所有的空链接到根结点的距离都将是相同的。如果我们将由红链接相连的结点合并，得到的就是一棵 2-3 树。
 * 相反，如果将一棵 2-3 树中的 3- 结点画作由红色左链接相连的两个 2- 结点，那么不会存在能够和两条红链接相连的结点，且树必然是完美黑色平衡的，
 * 因为黑链接即 2-3 树中的普通链接，根据定义这些链接必然是完美平衡的。无论我们选择用何种方式去定义它们，红黑树都既是二叉查找树， 也是 2-3树。
 * 因此，如果我们能够在保持一一对应关系的基础上实现 2-3 树的插入算法，那么我们就能够将两个算法的优点结合起来：二叉查找树中简洁高效的查找方法和 2-3
 * 树中高效的平衡插入算法。
 *
 * 方便起见，因为每个结点都只会有一条指向自己的链接（从它的父结点指向它），我们将链接的颜色保存在表示结点的 Node 数据类型的布尔变量 color 中。
 * 如果指向它的链接是红色的，那么该变量为 true，黑色则为 false。我们约定空链接为黑色。
 *
 *
 *
 */
public class RedBlackBST<K,V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;


    /**
     * 旋转：
     * 在我们实现的某些操作中可能会出现红色右链接或者两条连续的红链接，但在操作完成前这些情况都会被小心地旋转并修复。旋转操作会
     * 改变红链接的指向。首先，假设我们有一条红色的右链接需要被转化为左链接。这个操作叫做左旋转，它对应的方法接受一条指向红黑树
     * 中的某个结点的链接作为参数。假设被指向的结点的右链接是红色的，这个方法会对树进行必要的调整并返回一个指向包含同一组键的子
     * 树且其左链接为红色的根结点的链接。我们只是将用两个键中的较小者作为根结点变为将较大者作为根结点。实现将一个红色左链接转换
     * 为一个红色右链接的一个右旋转的代码完全相同，只需要将 left 和 right 互换即可。
     * 无论左旋转还是右旋转，旋转操作都会返回一条链接。我们总是会用 rotateRight() 或rotateLeft() 的返回值重置父结点（或是根结点）
     * 中相应的链接。返回的链接可能是左链接也可能是右链接，但是我们总会将它赋予父结点中的链接。这个链接可能是红色也可能是黑色——
     * rotateLeft() 和 rotateRight() 都 通 过 将 x.color 设 为 h.color 保 留 它 原 来 的 颜 色。 这 可能会产生两条连续的红链接，
     * 但我们的算法会继续用旋转操作修正这种情况。例如，代码 h =rotateLeft(h); 将旋转结点 h 的红色右链接，使得 h 指向了旋转后的
     * 子树的根结点（组成该子树中的所有键和旋转前相同，只是根结点发生了变化）。这种简洁的代码是我们使用递归实现二叉查找树的各种
     * 方法的主要原因。你会看到，它使得旋转操作成为了普通插入操作的一个简单补充。
     *
     *
     */
    Node rotateLeft(Node h)
    {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        return x;
    }

    Node rotateRight(Node h)
    {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        return x;
    }

    /**
     * 判断当前节点与它父节点之间的链接是不是红色
     * @param node
     * @return
     */
    public boolean isRed(Node node){
        return node!=null && node.color==RED;
    }



    /**
     * 树节点信息
     */
    private class Node{
        /**
         * 当前节点的key
         */
        private K key;
        /**
         * 当前节点的value
         */
        private V node;
        /**
         * 当前节点的左子树
         */
        private Node left;
        /**
         * 当前节点的右子树
         */
        private Node right;
        /**
         * 这颗子树的节点总数
         */
        private int N;
        /**
         * 右其父节点指向他的链接的颜色是不是红色
         */
        private boolean color;

        public Node(K key, V node, int n, boolean color) {
            this.key = key;
            this.node = node;
            N = n;
            this.color = color;
        }
    }
}
