package record.book.capter02.sort.v1;

import record.U;

/**
 * 冒泡排序
 * 基本思想：元素两两进行比较，将大的元素向后移动，直到移动到最后，然后进行下一趟，像冒泡一样，每次都将最大的元素冒到上面
 */
public class BubblingSort {
    public static void sort(int[] arr){
        int length = arr.length;
        while (length>0){
            for(int i=1;i<length;i++){
                if(arr[i-1]>arr[i]){
                    //如果左边的比右边的大，则交换左右的位置，即将大的向上冒
                    U.swap(arr,i-1,i);
                }
            }
            length--;
        }
    }
}
