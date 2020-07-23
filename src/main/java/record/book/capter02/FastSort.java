package record.book.capter02;

import record.U;

public class FastSort {
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int left, int right) {
        if (right <= left) {
            return;
        }
        int j = partition(arr, left, right); // 切分（请见“快速排序的切分”）
        sort(arr, left, j - 1); // 将左半部分a[lo .. j-1]排序
        sort(arr, j + 1, right); // 将右半部分a[j+1 .. hi]排序
    }

    private static int partition(int[] arr, int left, int right) { // 将数组切分为a[lo..i-1], a[i], a[i+1..hi]
        int i = left, j = right + 1; // 左右扫描指针
        int v = arr[left]; // 切分元素
        while (true) { // 扫描左右，检查扫描是否结束并交换元素
            while (arr[++i] < v) {
                if (i == right) {
                    break;
                }
            }
            while (v < arr[--j]) {
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            U.swap(arr, i, j);
        }
        U.swap(arr, left, j);// 将v = a[j]放入正确的位置
        return j; // a[lo..j-1] <= a[j] <= a[j+1..hi] 达成
    }
}
