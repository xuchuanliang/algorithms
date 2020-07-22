package record.book.capter02;

/**
 * 归并排序
 */
public class Merge {

    /**
     * 实现归并的一种直截了当的办法是将两个不同的有序数组归并到第三个数组中，
     * 但是，当用归并将一个大数组排序时，我们需要进行很多次归并，因此在每次归并时都创建一
     * 个新数组来存储排序结果会带来问题。我们更希望有一种能够在原地归并的方法，这样就可以先将
     * 前半部分排序，再将后半部分排序，然后在数组中移动元素而不需要使用额外的空间。
     * merge(a, lo,mid, hi)，它会将子数组 a[lo..mid] 和 a[mid+1..hi] 归并成一个有序的数组并将结果存放在 a[lo..hi] 中。
     *
     */
    private static void merge(int[] a,int lo,int mid,int hi){
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) // 将a[lo..hi]复制到aux[lo..hi]
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++) // 归并回到a[lo..hi]
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi ) {
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
    }


    private static int[] aux; // 归并所需的辅助数组

    public static void sort(int[] a)
    {
        aux = new int[a.length]; // 一次性分配空间
        sort(a, 0, a.length - 1);
    }
    private static void sort(int[] a, int lo, int hi)
    { // 将数组a[lo..hi]排序
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid); // 将左半边排序
        sort(a, mid+1, hi); // 将右半边排序
        merge(a, lo, mid, hi); // 归并结果（代码见“原地归并的抽象方法”）
    }
}
