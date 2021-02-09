package record.hanshunping.tree10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 霍夫曼树
 *
 * 一、霍夫曼树介绍：
 * 1.给定 n 个权值作为 n 个叶子结点， 构造一棵二叉树， 若该树的带权路径长度(wpl)达到最小， 称这样的二叉树为
 * 最优二叉树， 也称为哈夫曼树(Huffman Tree), 还有的书翻译为霍夫曼树。
 * 2.赫夫曼树是带权路径长度最短的树， 权值较大的结点离根较近
 *
 * 二、霍夫曼树几个重要概念和举例说明
 * 1.路径和路径长度： 在一棵树中， 从一个结点往下可以达到的孩子或孙子结点之间的通路， 称为路径。 通路
 * 中分支的数目称为路径长度。 若规定根结点的层数为 1， 则从根结点到第 L 层结点的路径长度为 L-1
 * 2.结点的权及带权路径长度： 若将树中结点赋给一个有着某种含义的数值， 则这个数值称为该结点的权。 结
 * 点的带权路径长度为： 从根结点到该结点之间的路径长度与该结点的权的乘积
 * 3.树的带权路径长度： 树的带权路径长度规定为所有叶子结点的带权路径长度之和， 记为 WPL(weighted path
 * length) ,权值越大的结点离根结点越近的二叉树才是最优二叉树。
 * 4.WPL 最小的就是赫夫曼树
 *
 * 三、霍夫曼树创建的思路
 * 给你一个数列 {13, 7, 8, 3, 29, 6, 1}， 要求转成一颗赫夫曼树.
 * 1.从小到大进行排序, 将每一个数据， 每个数据都是一个节点 ， 每个节点可以看成是一颗最简单的二叉树
 * 2.取出根节点权值最小的两颗二叉树
 * 3.组成一颗新的二叉树, 该新的二叉树的根节点的权值是前面两颗二叉树根节点权值的和
 * 4.再将这颗新的二叉树， 以根节点的权值大小 再次排序， 不断重复 1-2-3-4 的步骤， 直到数列中， 所有的数
 *   据都被处理， 就得到一颗赫夫曼树
 */
public class HuffmanTree {


    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node huffmanRootNode = buildHuffmanTree(arr);
        huffmanRootNode.preOrder();
    }

    /**
     * 构建一个霍夫曼树，
     * 思路如下：
     * @param arr
     * @return
     */
    public static Node buildHuffmanTree(int[] arr){
        //1.将数组转成一个Node集合
        List<Node> nodes = new ArrayList<>(arr.length);
        for(int i=0;i<arr.length;i++){
            nodes.add(new Node(arr[i]));
        }
        //2.将node集合按照权重从小到大排序
        //3.将最小的两个权重的树合并成一个新的树，新的树的权重等于这两个权重的和
        while (nodes.size()>1){
            Collections.sort(nodes);
            Node left = nodes.get(0);
            Node right = nodes.get(1);
            Node newNode = new Node(left.getValue()+right.getValue());
            newNode.setLeft(left);
            newNode.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(newNode);
        }
        //4.最终只剩下一个节点，这个节点就是霍夫曼树的根节点
        return nodes.get(0);
    }

    /**
     * 树的两个节点
     */
    private static class Node implements Comparable<Node>{
        /**
         * 权重
         */
        private int value;
        /**
         * 左节点
         */
        private Node left;
        /**
         * 右节点
         */
        private Node right;

        public Node(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValue() {
            return value;
        }

        /**
         * 前序遍历
         */
        public void preOrder(){
            System.out.print(this.getValue());
            if(Objects.nonNull(this.getLeft())){
                this.left.preOrder();
            }
            if(Objects.nonNull(this.getRight())){
                this.right.preOrder();
            }
        }

        @Override
        public int compareTo(Node o) {
            return this.value-o.getValue();
        }
    }

}
