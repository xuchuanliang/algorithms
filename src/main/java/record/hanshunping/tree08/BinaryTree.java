package record.hanshunping.tree08;

/**
 * 简单的二叉树
 */
public class BinaryTree {

    /**
     * 树的一个节点
     */
    class HeroNode{
        /**
         * 编号
         */
        private int no;
        /**
         * 名称
         */
        private String name;
        /**
         * 左子节点
         */
        private HeroNode left;
        /**
         * 右子节点
         */
        private HeroNode right;

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

    }
}
