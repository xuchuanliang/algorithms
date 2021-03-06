package record.book.capter02.sort.v2;

import record.U;

/**
 * 插入排序：
 *      通常人们整理桥牌的方法是一张一张的来，将每一张牌插入到其他已经有序的牌中的适当位置。
 * 在计算机的实现中，为了给要插入的元素腾出空间，我们需要将其余所有元素在插入之前都向右移
 * 动一位。这种算法叫做插入排序
 *      与选择排序一样，当前索引左边的所有元素都是有序的，但它们的最终位置还不确定，为了给
 * 更小的元素腾出空间，它们可能会被移动。但是当索引到达数组的右端时，数组排序就完成了。
 * 和选择排序不同的是，插入排序所需的时间取决于输入中元素的初始顺序。例如，对一个很大
 * 且其中的元素已经有序（或接近有序）的数组进行排序将会比对随机顺序的数组或是逆序数组进行
 * 排序要快得多。
 *      总的来说，插入排序对于部分有序的数
 * 组十分高效，也很适合小规模数组。这很重要，
 * 因为这些类型的数组在实际应用中经常出现，
 * 而且它们也是高级排序算法的中间过程。我
 * 们会在学习高级排序算法时再次接触到插入
 * 排序
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = U.getArr(100);
        sort(arr);
        U.assertSort(arr);

    }

    public static void sort(int[] arr){
        for(int i=0;i<arr.length-1;i++){
            //第i+1个元素与前i个元素比较，插入到合适的位置
            int j = i+1;
            while (j > 0 && arr[j] < arr[j-1]){
                //只要还没有遍历到头，则始终与它前一个元素比较
                U.swap(arr,j,j-1);
                j--;
            }
        }
    }
}
