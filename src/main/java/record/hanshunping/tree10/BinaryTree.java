package record.hanshunping.tree10;

import java.util.Objects;

/**
 * 数组存储结构：
 *  优点： 通过下标方式访问元素， 速度快。 对于有序数组， 还可使用二分查找提高检索速度。
 *  缺点： 如果要检索具体某个值， 或者插入值(按一定顺序)会整体移动， 效率较低
 * 链式存储方式的分析
 *   优点： 在一定程度上对数组存储方式有优化(比如： 插入一个数值节点， 只需要将插入节点， 链接到链表中即可，删除效率也很好)。
 *   缺点： 在进行检索时， 效率仍然较低， 比如(检索某个值， 需要从头节点开始遍历)
 * 树：
 *  树相当于是有序链表+二分法查找
 *  能提高数据存储， 读取的效率, 比如利用 二叉排序树(Binary Sort Tree)， 既可以保证数据的检索速度， 同时也可以保证数据的插入， 删除， 修改的速度。
 * 二叉树：
 *  树有很多种， 每个节点最多只能有两个子节点的一种形式称为二叉树。
 * 二叉树遍历的说明：
 *  前序遍历: 先输出父节点， 再遍历左子树和右子树
 *  中序遍历: 先遍历左子树， 再输出父节点， 再遍历右子树
 *  后序遍历: 先遍历左子树， 再遍历右子树， 最后输出父节点
 *  小结: 看输出父节点的顺序， 就确定是前序， 中序还是后序
 * 二叉树查找的说明：
 *  前序查找思路
 *  先判断当前节点的no是否等于要查找的
 *  如果是相等的，则返回当前节点
 *  如果不等，则判断当前节点的左子树是否为空，如果不为空，则递归前序查找
 *  如果做左递归前序查找，找到节点，则返回，否继续判断，当前节点的右子节点是否为空，如果不空，则继续有递归前序查找
 *  中序查找思路
 *  相对于前序查找，对当前节点的判断放在中间
 *  后序查找思路
 *  相对于前序查找，对当前节点的判断放在最后
 */
public class BinaryTree {
    /**
     * 表示二叉树中的一个节点
     */
    private static class Node{
        private int id;
        private String name;
        private Node left;
        private Node right;

        public Node(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * 前序遍历
         */
        public void preOrder(){
            //先输出当前节点
            System.out.println(this);
            //递归向左前序遍历
            if(Objects.nonNull(this.left)){
                this.left.preOrder();
            }
            //递归向右前序遍历
            if(Objects.nonNull(this.right)){
                this.right.preOrder();
            }
        }

        /**
         * 中序遍历
         */
        public void midOrder(){
            //先中序遍历左节点
            if(Objects.nonNull(this.left)){
                this.left.midOrder();
            }
            //再打印当前节点
            System.out.println(this);
            //再中序遍历右节点
            if(Objects.nonNull(this.right)){
                this.right.midOrder();
            }
        }

        /**
         * 后序遍历
         */
        public void postOrder(){
            //先后序遍历左子树
            if(Objects.nonNull(this.left)){
                this.left.postOrder();
            }
            //再后序遍历右子树
            if(Objects.nonNull(this.right)){
                this.right.postOrder();
            }
            //在打印出当前节点
            System.out.println(this);
        }

        /**
         * 前序查找
         * @param id
         * @return
         */
        public Node preSearch(int id){
            //先判断当前节点是否是查找的元素
            Node result = null;
            if(this.id==id){
                result = this;
            }
            //未找到，则递归向左查找
            if(Objects.isNull(result) && Objects.nonNull(this.left)){
                result = this.left.preSearch(id);
            }
            //未找到，则递归向右查找
            if(Objects.isNull(result) && Objects.nonNull(this.right)){
                result = this.right.preSearch(id);
            }
            return result;
        }

        /**
         * 中序查找
         * @param id
         * @return
         */
        public Node midSearch(int id){
            Node result = null;
            //先向左递归查找
            if(Objects.nonNull(this.left)){
                result = this.left.midSearch(id);
            }
            //未找到，则判断当前节点是否是查找元素
            if(Objects.isNull(result) && this.id==id){
                result = this;
            }
            //未找到，则向右中序查找
            if(Objects.isNull(result) && Objects.nonNull(this.right)){
                result = this.right.midSearch(id);
            }
            return result;
        }

        /**
         * 后序查找
         * @param id
         * @return
         */
        public Node postSearch(int id){
            Node result = null;
            //先向左递归查找
            if(Objects.nonNull(this.left)){
                result = this.left.postSearch(id);
            }
            //未找到，则右递归查找
            if(Objects.nonNull(this.right)){
                result = this.right.postSearch(id);
            }
            //未找到，则与当前节点比较
            if(Objects.isNull(result) && this.id==id){
                result = this;
            }
            return result;

        }



        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
