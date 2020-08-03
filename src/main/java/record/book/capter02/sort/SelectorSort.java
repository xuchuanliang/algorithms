package record.book.capter02.sort;

import record.U;

/**
 * 选择排序：
 *      首先，找到数组中最小的那个元素，其次，将它和数组的第
 * 一个元素交换位置（如果第一个元素就是最小元素那么它就和自己交换）。再次，在剩下的元素中
 * 找到最小的元素，将它与数组的第二个元素交换位置。如此往复，直到将整个数组排序。这种方法
 * 叫做选择排序，因为它在不断地选择剩余元素之中的最小者
 *
 * 特点：
 *      运行时间和输入无关。为了找出最小的元素而扫描一遍数组并不能为下一遍扫描提供什么信息。
 * 这种性质在某些情况下是缺点，因为使用选择排序的人可能会惊讶地发现，一个已经有序的数组或
 * 是主键全部相等的数组和一个元素随机排列的数组所用的排序时间竟然一样长！我们将会看到，其
 * 他算法会更善于利用输入的初始状态。
 *      数据移动是最少的。每次交换都会改变两个数组元素的值，因此选择排序用了 N 次交换——交
 * 换次数和数组的大小是线性关系。我们将研究的其他任何算法都不具备这个特征（大部分的增长数
 * 量级都是线性对数或是平方级别）。
 */
public class SelectorSort {

    public static void sort(int[] arr){
        if(null==arr)
            throw new IllegalArgumentException("参数错误");
        for(int i=0; i<arr.length; i++){
            //找到i至最后一个元素中最小的元素的所在索引
            int min = i;
            for(int j=i+1;j<arr.length-1;j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            //将最小的元素交换值i的位置
            U.swap(arr,i,min);
        }
    }
}