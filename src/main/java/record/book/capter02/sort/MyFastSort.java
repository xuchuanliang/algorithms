package record.book.capter02.sort;

import record.U;

/**
 * 快速排序
 *
 * 快速排序流行的原因是它实现简单、
 * 适用于各种不同的输入数据且在一般应用中比其他排序算法都要快得多。快速排序引人注目的特点包
 * 括它是原地排序（只需要一个很小的辅助栈），且将长度为 N 的数组排序所需的时间和 NlgN 成正比。
 * 我们已经学习过的排序算法都无法将这两个优点结合起来。另外，快速排序的内循环比大多数排序算
 * 法都要短小，这意味着它无论是在理论上还是在实际中都要更快。
 *
 * 快速排序是一种分治的排序算法。它将一个数组分成两个子数组，将两部分独立地排序。快速排
 * 序和归并排序是互补的：归并排序将数组分成两个子数组分别排序，并将有序的子数组归并以将整个
 * 数组排序；而快速排序将数组排序的方式则是当两个子数组都有序时整个数组也就自然有序了。在第
 * 一种情况中，递归调用发生在处理整个数组之前；在第二种情况中，递归调用发生在处理整个数组之后。
 * 在归并排序中，一个数组被等分为两半；在快速排序中，切分（ partition）的位置取决于数组的内容。
 *
 *
 * 个人观点：
 * 了解快速排序就需要和归并排序做对比才好理解：
 * 归并排序的基本思想是对原数组进行切分，只要左边的数组是有序的，右边的数组也是有序的，那么将左右两边的数组进行合并就能合并成为一个有序的数组；
 * 合并过程很简单，就是将左右两个有序数组都从第一个元素进行遍历，然后对比，那个小则那个元素在前，直到将两个有序数组合并成为一个有序数组
 * 递归的临界条件就是当需要排序的数组递归到还剩一个元素的时候，那么就没有必要再切分为左右两个数组进行递归排序，默认1个元素就是有序的
 * 归并排序的缺点就是需要一个临时数组用来存储临时合并的有序数组，对空间的占用与原数组有关系，原数组越大，则空间占用会越大
 *
 * 快速排序的基本思想是借鉴了一部分归并排序的思想，但是解决了临时数组的问题，让原数组进行原地排序
 * 主要思想是先确定一个需要切分的分界点，然后找到分界点的位置，将分界点左侧的比分界点大的元素放在右边，将右边比分界点小的元素放在左边，然后对左右两边的数据进行排序，
 * 如果左右两边的数组有序，那么整个数组也就有序了，相对于归并排序，少了最后的归并操作
 */
public class MyFastSort {
    public static void sort(int[] arr){
        sort(arr,0,arr.length-1);
    }

    private static void sort(int[] arr,int left,int right){
        //如果left==right，则说明需要排序的只有一个元素，则默认有序，也是递归的边界条件
        if(left >= right){
            return;
        }
        int index = sharding(arr,left,right);
        //递归将分界点左侧和右侧的数据进行排序
        sort(arr,left,index);
        sort(arr,index+1,right);
    }

    /**
     * 对arr[left-right]的元素进行切分，主要思想是拿第一个元素作为边界，然后从第二个元素向后推，同时最后一个元素向前推，
     * 将左边比第一个元素大的与右边比第一个元素小的进行交换，直到相遇，那么相遇的位置就是第一个元素的位置，并且将第一个元素交换到该位置
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int sharding(int[] arr, int left, int right) {
        int index = left;
        //确定基准元素
        int val = arr[left];
        //确定最左边的指针和最右边的指针，即left+1和right，只要两个指针没有相遇，那么就从从两头向中间推进，
        int temp_left = left+1;
        int temp_right = right;
        while (temp_left<temp_right){
            //先从左边开始向右找到第一个比var大的元素，即只要还在范围内并且left元素小于val，则向右推进
            while (arr[temp_left]<=val && temp_left<temp_right){
                temp_left++;
            }
            while (arr[temp_right]>=val && temp_left<temp_right){
                temp_right--;
            }
            //如果此时相遇，则说明左侧元素都小于val，右侧元素都大于val，那么如果val大于相遇的位置，则放在右边，否则放在左边
            if(temp_left==temp_right){
                if(arr[temp_left]<val){
                    //temp_left小于基准值，则交换temp_left与val的位置
                    U.swap(arr,left,temp_left);
                    index = temp_left;
                }else{
                    //否则说明temp_left的元素比val大，交换temp_left-1与val的位置
                    U.swap(arr,left,temp_left-1);
                    index = temp_left-1;
                }
            }else{
                //否则，则说明左边有比val大的元素，右侧有比val小的元素，则交换两者位置
                U.swap(arr,temp_left,temp_right);
            }
        }
        return index;
    }
}
