package record.hanshunping.sort06;

/**
 * 冒泡排序（Bubble Sorting） 的基本思想是： 通过对待排序序列从前向后（从下标较小的元素开始） ,依次比较
 * 相邻元素的值， 若发现逆序则交换， 使值较大的元素逐渐从前移向后部， 就象水底下的气泡一样逐渐向上冒。
 * 说人话就是：假设有数组arr[length]，从头遍历到尾，遇到大的就向后换，换了一遍后，最大的就在最后一个位置；然后接着以同样的方式将倒数第二大的放在倒数第二的位置
 * 主要思想在于冒泡二字：从左到右逐步将最大的元素交换（冒泡）到最右边，从大到小解决顺序
 */
public class BubbleSort extends Sort {
    @Override
    public void sort(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            //每次都拿当前元素与后面一个元素比较，所以只需要比较到倒数第二个元素即可
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]){
                    //如果比后面的一个大，则交换
                    super.swap(arr,j,j+1);
                }
            }
        }
    }
}
