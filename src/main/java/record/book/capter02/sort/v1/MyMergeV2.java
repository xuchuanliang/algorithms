package record.book.capter02.sort.v1;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 归并排序我自己编写的V2版本，
 * V1版本的缺点是每次合并都需要创建一个临时数组，当需要排序的数量非常大时，会产生创建和销毁大量的临时数组，导致空间的浪费
 * 因此V2版本创建一个与原数组长度一致的临时数组，例如left-mid mid+1-right这两个数组，因为临时数组长度与原数组一致，
 * 所以将其合并的元素放入到临时数组的left-right位置中，然后再复制到原数组的相同位置中，这样就做到只有会创建一个临时数组
 */
public class MyMergeV2 {
    private static int[] tempArr;

    public static void sort(int[] arr) {
        tempArr = new int[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 将给定left-right长度的数组分成左边有右边两个数组，并且递归将其排好序后，合并成为一个有序数组
     * @param arr
     * @param left
     * @param right
     */
    private static void sort(int[] arr, int left, int right) {
        //如果left==right，则认为该数组的长度只有1，则已经为有序数组
        if(left == right){
            return;
        }
        int mid = (left+right)/2;
        sort(arr,left,mid);
        sort(arr,mid+1,right);
        if(arr[mid]>arr[mid+1]){
            //****优化：只有左侧数组最后一个元素大于右侧第一个元素，才认为需要进行合并操作，否则left-right数组本身就是有序，无需进行合并操作
            merge(arr,left,mid,right);
        }
    }

    /**
     * 将左侧有序的数组以及右侧有序的数组合并成为一个大的有序数组
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] arr,int left,int mid,int right){
        //只要左右两个数组都还没有被遍历完，则始终对比左侧和右侧数组最左侧的元素，将最小的数据推到队列中
        int temp_left = left;
        int temp_right = mid+1;
        int temp_index = left;
        while (temp_left<=mid && temp_right<=right){
            //说明左右两个数组元素都不空
            if(arr[temp_left]<arr[temp_right]){
                //如果左边第一个元素小，则左边的放入临时数组中
                tempArr[temp_index++] = arr[temp_left++];
            }else{
                //如果右边元素小，则右边第一个元素放入临时数组中
                tempArr[temp_index++] = arr[temp_right++];
            }
        }
        //到此为止，则要么左侧元素已经遍历完成，要么右侧元素已经遍历完成，则只剩下一个非空的数组，将未遍历到的剩余数据依次放入到临时数组中
        while (temp_left<=mid){
            //则左侧数组非空
            tempArr[temp_index++] = arr[temp_left++];
        }
        while (temp_right<=right){
            //则右侧数组非空
            tempArr[temp_index++] = arr[temp_right++];
        }
        //将合并后有序的临时数组中的元素依次重新放入到原数组中
        for(int i=left;i<=right;i++){
            arr[i] = tempArr[i];
        }
    }
}
