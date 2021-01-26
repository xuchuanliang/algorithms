package record.hanshunping.sort;

/**
 * 选择排序：从数组中找出最小的放在第一个位置，然后逐步找出剩余元素最小的放在后面的位置
 * 主要思想在于选择二字：选出最小的，从小到大解决顺序
 */
public class SelectSort extends Sort {
    @Override
    public void sort(int[] arr) {
        int start = 0;
        //从第一个位置开始，找出后面小的放在当前位置，然后继续向后推动，直到倒数第二个最小的元素选出来后，最后一个元素自然有序
        while (start < arr.length - 1) {
            //最小的索引位置
            int smallIndex = start;
            for (int i = start + 1; i < arr.length; i++) {
                if (arr[i] < arr[smallIndex]) {
                    smallIndex = i;
                }
            }
            //将最小的元素与start交换
            swap(arr,smallIndex,start);
            //start向后推
            start++;
        }
        //排序结束
    }
}
