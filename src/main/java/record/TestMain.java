package record;

import record.book.TestSortCompare;
import record.book.capter01.InsertSort;
import record.book.capter01.SelectorSort;
import record.book.capter01.ShellSort;
import record.book.capter02.Merge;
import record.book.capter02.MyMerge;
import record.book.capter02.MyMergeV2;
import record.niuke.*;

import java.util.concurrent.CompletableFuture;

public class TestMain {
    public static void main(String[] args) throws Exception {
//        testC1();
//        testC2();
//        testC3();
//        testC4();
//        testSelectSort();
//        testInsertSort();
//        testShellSort();
//        testMerge();
//        testMyMerge();
//        testMyMergeV2();

        compareSortTime();
    }

    public static void testC1() {
        //        int[] arr = U.getArr(10);
        int[] arr = U.transfer("0,3,8,8,2,4,6,9,1,7,");
//        int[] arr = U.transfer("0,3,8,8,4,4,6,7,4,7,");
        int[] duplicate = C1.duplicate(arr);
//        U.print(C1._duplicate(arr));
//        boolean duplicate = C1.duplicate(arr, args.length, new int[10]);
//        System.out.println(duplicate);
        U.print(duplicate);
    }

    public static void testC2() {
        int[][] twoArr = U.getTwoArr(10, 20);
        U.print(twoArr);
        boolean b = C2.find(190, twoArr);
        System.out.println(b);
    }

    public static void testC3() {
        String s = "a f s";
        System.out.println(C3.replaceSpace(s));
    }

    public static void testC4() {
        LinkNode linkNode = LinkNode.init(10);
        C4.printListFromTailToHead(linkNode);
    }

    public static void testSelectSort() {
        int[] arr = U.getArr(1000);
        U.assertSort(arr);
        SelectorSort.sort(arr);
        U.assertSort(arr);
        U.print(arr);
    }

    public static void testInsertSort() {
        int[] arr = U.getArr(100);
        U.print(arr);
        InsertSort.sort_simulate(arr);
        U.print(arr);
        U.assertSort(arr);
    }

    public static void testShellSort() {
        int[] arr = U.getArr(100);
        U.print(arr);
        ShellSort.sort(arr);
        U.assertSort(arr);
        U.print(arr);
    }

    public static void testMerge() {
        int[] arr = U.getArr(10);
        U.print(arr);
        Merge.sort(arr);
        U.assertSort(arr);
        U.print(arr);
    }

    public static void testMyMerge() {
        int[] arr = U.getArr(1000);
        U.print(arr);
        MyMerge.sort(arr);
        U.assertSort(arr);
        U.print(arr);
    }

    public static void testMyMergeV2() {
        int[] arr = U.getArr(1000);
        U.print(arr);
        MyMergeV2.sort(arr);
        U.assertSort(arr);
    }

    /**
     * 30W数据测试：
     * 选择排序时间35194
     * 插入排序时间12777
     * 希尔排序时间：43448
     */
    public static void compareSortTime() throws Exception {
        //30W数据排序
        int[] arr = U.getArr(10000000 * 3);
//        //选择排序 35587
        CompletableFuture<Void> c1 = CompletableFuture.runAsync(() -> TestSortCompare.compareSelectorSort(arr));
//        //插入排序 12839
        CompletableFuture<Void> c2 = CompletableFuture.runAsync(() -> TestSortCompare.compareInsertSort(arr));
//        //希尔排序 48862
        CompletableFuture<Void> c3 = CompletableFuture.runAsync(() -> TestSortCompare.compareShellSort(arr));
//        //归并排序 44
        CompletableFuture<Void> c4 = CompletableFuture.runAsync(() -> TestSortCompare.compareMerge(arr));
//        //我自己的V1版本归并排序 78
        CompletableFuture<Void> c5 = CompletableFuture.runAsync(() -> TestSortCompare.compareMyMerge(arr));
        //我自己的V2版本归并排序 41
        CompletableFuture<Void> c6 = CompletableFuture.runAsync(() -> TestSortCompare.compareMyMergeV2(arr));

        c1.get();
        c2.get();
        c3.get();
        c4.get();
        c5.get();
        c6.get();
    }
}
