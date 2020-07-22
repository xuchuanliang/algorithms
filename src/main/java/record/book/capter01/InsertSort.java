package record.book.capter01;

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
public class InsertSort{

    /**
     * 思路：从第二个开始逐步向后遍历，并且插入到前面的指定位置，将该位置的元素向后移动，
     * @param arr
     */
    public static void sort(int[] arr){
        for(int i=1;i<arr.length;i++){
            //将当前元素插入到前面的有序集合中
            //1.定位到位置
            int index = find(arr,0,i-1,arr[i]);
            //2.将该位置的元素后移一位，且将该元素插入到该位置
            insertIndex(arr,index,i-1,arr[i]);
        }
    }

    private static int find(int[] arr,int left,int right,int value){
        //如果比最右边的还大，则位置不变
        if(value >= arr[right]){
            return right+1;
        }
        //如果比最左边的还小，则为最左边位置
        if(value <= arr[left]){
            return left;
        }
        //在中间的情况
        while (right > left){
            if(arr[right]==value || arr[right-1]==value){
                return right;
            }else if(arr[right] > value && arr[right-1] < value){
                return right;
            }
            right--;
        }
        throw new IllegalArgumentException("未找到元素位置");
    }

    private static void insertIndex(int[] arr,int index,int right,int value){
        //从后向前移动
        while (right >= index){
            U.swap(arr,right,right+1);
            right--;
        }
        arr[index] = value;
    }

    public static void sort_simulate(int[] arr){
        for(int i=1;i<arr.length;i++){
            int j = i;
            while (j>0 && arr[j] < arr[j-1]){
                U.swap(arr,j,j-1);
                j--;
            }
        }
    }

    /**
     * 相对于我自己的思路，书上的思路更加优雅，代码量也比较少，
     * 思路：对于 1 到 N-1 之间的每一个 i，将 a[i] 与 a[0] 到 a[i-1] 中比它小的所有元素依次有序地交换。
     * 在索引 i 由左向右变化的过程中，它左侧的元素总是有序的，所以当 i 到达数组的右端时排序就完成了。
     * @param arr
     */
    public static void sort_book(int[] arr){
        for(int i=1;i<arr.length;i++){
            for(int j=i;j>0 && arr[j]<arr[j-1];j--){
                U.swap(arr,j,j-1);
            }
        }
    }

}
