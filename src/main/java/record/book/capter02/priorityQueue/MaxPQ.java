package record.book.capter02.priorityQueue;

import record.U;

/**
 * 优先队列
 *
 * 二叉堆的定义：
 * 数据结构二叉堆能够很好地实现优先队列的基本操作。在二叉堆的数组中，每个元素都要保证大于等于另两个特定位置的元素。
 * 相应地，这些位置的元素又至少要大于等于数组中的另两个元素，以此类推。如果我们将所有元素画成一棵二叉树，将每个较大元素
 * 和两个较小的元素用边连接就可以很容易看出这种结构。
 *
 * 当一棵二叉树的每个结点都大于等于它的两个子结点时，它被称为堆有序。
 *
 * 二叉堆表示法：
 * 如果我们用指针来表示堆有序的二叉树，那么每个元素都需要三个指针来找到它的上下结点（父结点和两个子
 * 结点各需要一个）。但如图 2.4.1 所示，如果我们使用完全二叉树，表达就会变得特别方便。要画出这样一棵完全
 * 二叉树，可以先定下根结点，然后一层一层地由上向下、从左至右，在每个结点的下方连接两个更小的结点，直至
 * 将 N 个结点全部连接完毕。完全二叉树只用数组而不需要指针就可以表示。具体方法就是将二叉树的结点按照层级顺序放入数组中，根结点在位置 1，它
 * 的子结点在位置 2 和 3，而子结点的子结点则分别在位置 4、 5、 6 和 7，以此类推。
 *
 * 二叉堆是一组能够用堆有序的完全二叉树排序的元素，并在数组中按照层级储存（不使用数组的第一个位置）。
 * 下文中我们将二叉堆简称为堆
 *
 * 什么是优先队列：优先队列是一种数据结构，允许用户删除最大的元素和插入元素
 *
 * 使用栈或队列或数组实现优先队列在插入元素和删除最大元素这两个操作上都有可能需要线性的时间来完成，
 * 为了更快的操作优先队列，因此引入了堆。
 *
 * 二叉堆的定义：在二叉堆中每个元素都要保证大于等于另外两个特定位置的元素。也可以理解成当一颗二叉树的每一个节点都大于等于他的两个子节点的情况下，他被称为堆有序。
 *
 * 完全二叉树的特点：叶子节点只能出现在最下层和次下层，且最下层的叶子节点集中在树的左部。说人话就是，将数字从上到下，从左到右，从小到大进行一层一层排成树结构，最终就是一个完全二叉树。
 *
 * 普通二叉堆每一个元素都额外需要三个指针来分别指向他的左子节点、右子节点、父节点。但是如果使用完全二叉树，那么就可以直接使用数组表示就行，也不需要指针了。
 *
 * 注意此处的二叉树只保证从上到下是递增有序的，但是不保证从左到右是有序的。
 *
 * 使用完全二叉树表示的二叉堆我们默认称之为堆，索引为k的元素，其父节点的索引为k/2，其子节点分别是2k和2k+1；
 * 为了方便计算，一般堆中的数组索引为0的元素不会使用，从1开始。
 *
 * 堆的有序化：堆的操作会首先进行一些简单的改动，打破堆的状态，然后再遍历堆并按照要求将堆的状态恢复。我们称这个过程叫做堆的有序化。
 * 在有序化的过程中我们会遇到两种情况。当某个结点的优先级上升（或是在堆底加入一个新的元素）时，我们需要由下至上恢复堆的顺序。
 * 当某个结点的优先级下降（例如，将根结点替换为一个较小的元素）时，我们需要由上至下恢复堆的顺序。
 *
 * 由下至上的堆有序化（上浮）：
 * 如果堆的有序状态因为某个结点变得比它的父结点更大而被打破， 那么我们就需要通过交换它和它的父结点来修复堆。
 * 交换后，这个结点比它的两个子结点都大（一个是曾经的父结点，另一个比它更小，因为它是曾经父结点的子结点），但这个结点仍然可能
 * 比它现在的父结点更大。我们可以一遍遍地用同样的办法恢复秩序，将这个结点不断向上移动直到我们遇到了一个更大的父结点。只要记住位置 k 的结点的父
 *结点的位置是 k/2，这个过程实现起来很简单。 swim() 方法中的循环可以保证只有位置 k 上的结点大于它的父结点时堆的有序状态才会被打破。
 * 因此只要该结点不再大于它的父结点，堆的有序状态就恢复了。至于方法名，当一个结点太大的时候它需要浮（ swim）到堆的更高层。由下至上的堆
 * 有序化的实现代码如右上方所示。
 *
 * 由上至下的堆有序化（下沉）:
 * 如果堆的有序状态因为某个结点变得比它的两个子结点或是其中之一更小了而被打破了， 那么我们可以通过将它和它的两个子结点中的较大者交换来恢复堆。
 * 交换可能会在子结点处继续打破堆的有序状态，因此我们需要不断地用相同的方式将其修复，将结点向下移动直到它的子结点都比它
 * 更小或是到达了堆的底部。由位置为 k 的结点的子结点位于 2k 和 2k+1 可以直接得到对应的代码。当一个结点太小的时候它需要沉（ sink）到堆的更低层。
 *
 * 插入元素。我们将新元素加到数组末尾，增加堆的大小并让这个新元素上浮到合适的位置
 * 删除最大元素。我们从数组顶端删去最大的元素并将数组的最后一个元素放到顶端，减小堆的大小并让这个元素下沉到合适的位置
 *
 * 它对优先队列 API 的实现能够保证插入元素和删除最大元素这两个操作的用时和队列的大小仅成对数关系。
 *
 * 优先队列由一个基于堆的完全二叉树表示，存储于数组 pq[1..N] 中， pq[0] 没有使用。在insert() 中，我们将 N 加一并把新元素添
 * 加在数组最后，然后用 swim() 恢复堆的秩序。在 delMax() 中，我们从 pq[1] 中得到需要返回的元素，然后将 pq[N] 移动到 pq[1]，
 * 将 N 减一并用 sink() 恢复堆的秩序。同时我们还将不再使用的 pq[N+1] 设为 null，以便系统回收它所占用的空间。
 *
 * 多向归并：它将多个有序的输入流归并成一个有序的输出流。
 *
 * 堆排序：我们可以把任意优先队列变成一种排序方法。将所有元素插入一个查找最小元素的优先队列，然后再重复调用
 * 删除最小元素的操作来将它们按顺序删去。一种全新的排序方法！----堆排序
 * 堆排序可以分为两个阶段。在堆的构造阶段中，我们将原始数组重新组织安排进一个堆中；然后在下沉排序阶段，我们从堆中按递减顺
 * 序取出所有元素并得到排序结果。
 *
 *
 */
public class MaxPQ {
    private int[] arr;
    //指向arr中最后一个有效位置的元素
    //由于优先队列使用完全二叉树实现，所以第个元素不存储元素
    private int N = 0;

    public MaxPQ(int maxN) {
        arr = new int[maxN+1];
    }

    public boolean isEmpty(){
        return N==0;
    }

    public int size(){
        return N;
    }

    public void insert(int val){
        if(N>=arr.length-1){
            throw new IllegalArgumentException("队列长度已经用完");
        }
        //插入元素
        arr[++N] = val;
        //上浮元素
        swim(N);
    }

    /**
     * 删除最大的元素
     * 由于使用的是完全二叉树实现的优先队列，默认数组索引为1的元素就是树的根节点，也是树中最大的元素
     * 那么删除第一个元素，并且将数组的最后的一个元素放在第一位，再对第一个元素进行下沉操作，这样就会将整个数组中最大的元素上浮至根节点
     *
     * 这里主要不容易明白的地方是：将第一个索引的元素删除后，怎么将下面的最大的元素给提上来，按理来说根节点下面的两个子节点中最大的应该是根节点，
     * 但是由于是树结构，那么从上向下找最大的要不断地递归找，比较麻烦，所以采取随便将一个节点给交换至根节点，然后再将该数据下沉到合适的位置，自然下沉后的根节点变成了最大的的节点
     *
     * @return
     */
    public int delMax(){
        //取出最大的元素
        int max = arr[1];
        //将最后一个元素放在第一位
        arr[1] = arr[N];
        N--;
        //将第一位的元素下沉
        sink(1);
        return max;
    }

    /**
     * 判断第k位置的元素变动后是否需要上浮
     * 如果大于其父节点，那么就需要上浮
     * 上浮完成后，需要继续递归判断所处的新的位置是否依然大于其父节点，如果仍然大于其父节点，那么需要继续上浮
     * @param k
     */
    public void swim(int k){
        //只要第k位置的元素大于k/2（即其父节点）位置的元素，那么就需要上浮，即交换与其父节点的位置
        //由于堆的元素是从第1个元素开始，所以需判断其不是根节点，如果是根节点，那么就无需上浮
        while (k>1 && arr[k]>arr[k/2]){
            //k位置元素大于其父节点，那么需要上浮
            U.swap(arr,k,k/2);
            k = k/2;
        }
    }

    /**
     * 判断第k个元素是不是比他的子节点更小，如果是，那么就与其两个子节点中最大的交换位置，并且递归查看是否需要继续下沉
     * @param k
     */
    public void sink(int k){
        if(k<arr.length){
            if(2*k > arr.length){
                //没有子节点
                return;
            }
            if(2*k+1 > arr.length){
                //由于堆底层使用完全二叉树表示，那么肯定现有左边叶子节点，再有右边叶子节点
                //说明只有左边叶子节点
                if(arr[2*k] > arr[k]){
                    //只有左边节前，且左边节点大于父节点，则父节点下沉
                    U.swap(arr,k,2*k);
                    return;
                }
            }
            //到此处，则说明既有左节点，又有右节点，与两个子节点中最大的比较，如果小于最大的子节点，则下沉，并且递归下沉
            if(arr[2*k] > arr[2*k+1]){
                if(arr[k] < arr[2*k]){
                    U.swap(arr,k,2*k);
                    sink(2*k);
                }
            }else{
                if(arr[k] < arr[2*k+1]){
                    U.swap(arr,k,2*k+1);
                    sink(2*k+1);
                }
            }
        }
    }
}
