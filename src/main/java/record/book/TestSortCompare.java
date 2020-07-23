package record.book;

import record.U;
import record.book.capter02.*;

import java.util.Arrays;

public final class TestSortCompare {

    /**
     * 打印插入排序的时间
     * @param arr
     */
    public static void compareInsertSort(int[] arr){
        int[] c = Arrays.copyOf(arr,arr.length);
        StopWatch.start();
        InsertSort.sort_simulate(c);
        StopWatch.stopAndPrint(InsertSort.class.getName());
        U.assertSort(c);
    }

    /**
     * 打印选择排序的时间
     * @param arr
     */
    public static void compareSelectorSort(int[] arr){
        int[] c = Arrays.copyOf(arr,arr.length);
        StopWatch.start();
        SelectorSort.sort(c);
        StopWatch.stopAndPrint(SelectorSort.class.getName());
        U.assertSort(c);
    }

    /**
     * 打印希尔排序的时间
     * @param arr
     */
    public static void compareShellSort(int[] arr){
        int[] c = Arrays.copyOf(arr,arr.length);
        StopWatch.start();
        ShellSort.sort(c);
        StopWatch.stopAndPrint(ShellSort.class.getName());
        U.assertSort(c);
    }

    /**
     * 打印书中归并排序的时间
     * @param arr
     */
    public static void compareMerge(int[] arr){
        int[] c = Arrays.copyOf(arr,arr.length);
        StopWatch.start();
        Merge.sort(c);
        StopWatch.stopAndPrint(Merge.class.getName());
        U.assertSort(c);
    }

    /**
     * 打印我自己编写的归并排序V1的时间
     * 该版本归并排序的缺点是：每次在合并有序数组的时候都会创建一个临时数组来临时存储有序的元素，对空间有较大的浪费，在v2版本中会解决
     * @param arr
     */
    public static void compareMyMerge(int[] arr){
        int[] c = Arrays.copyOf(arr,arr.length);
        StopWatch.start();
        MyMerge.sort(c);
        StopWatch.stopAndPrint(MyMerge.class.getName());
        U.assertSort(c);
    }

    /**
     * 打印我自己编写的归并排序V2的时间
     * 该版本归并排序的缺点是：每次在合并有序数组的时候都会创建一个临时数组来临时存储有序的元素，对空间有较大的浪费，在v2版本中会解决
     * @param arr
     */
    public static void compareMyMergeV2(int[] arr){
        int[] c = Arrays.copyOf(arr,arr.length);
        StopWatch.start();
        MyMergeV2.sort(c);
        StopWatch.stopAndPrint(MyMergeV2.class.getName());
        U.assertSort(c);
    }

    /**
     * 打印我自己编写的快速排序的时间
     * @param arr
     */
    public static void compareMyFastSort(int[] arr){
        int[] c = Arrays.copyOf(arr,arr.length);
        StopWatch.start();
        MyFastSort.sort(c);
        StopWatch.stopAndPrint(MyFastSort.class.getName());
        U.assertSort(c);
    }

    /**
     * 打印书中的快速排序的时间
     * @param arr
     */
    public static void compareFastSort(int[] arr){
        int[] c = Arrays.copyOf(arr,arr.length);
        StopWatch.start();
        FastSort.sort(c);
        StopWatch.stopAndPrint(FastSort.class.getName());
        U.assertSort(c);
    }

}
