package record;

import record.book.InsertSort;
import record.book.SelectorSort;
import record.niuke.*;

public class TestMain {
    public static void main(String[] args) {
//        testC1();
//        testC2();
//        testC3();
//        testC4();
//        testSelectSort();
        testInsertSort();
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
        InsertSort.sort_book(arr);
        U.print(arr);
        U.assertSort(arr);
    }
}
