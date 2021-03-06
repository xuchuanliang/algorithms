package com.diagrammatize.capter01;

import java.util.Random;

/**
 * 二分查找法：给定有序数组以及需要查找的值，找到该值在数组中的位置，如果没有则返回null
 */
public class BinarySearch {

    public static void main(String[] args) {
        int length = 90000000;
        int[] arr = new int[length];
        for (int i = 0; i < length; i++) {
            arr[i] = i;
        }
        int find = new Random().nextInt(length);
        System.out.println(binarySearch(arr, find));
        System.out.println(normalSearch(arr, find));
    }

    /**
     * 二分查找法的思路：给定一个从小到大排列的数组以及需要查找的值
     * 设定查询的最大索引和最小索引，默认是当前数组的最大索引合最小索引
     * 将值与该数组中间的值进行循环比较
     *
     * @param arr
     * @param val
     * @return
     */
    public static int binarySearch(int[] arr, int val) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            int guss = arr[middle];
            if (guss == val) {
                return middle;
            } else if (guss < val) {
                low = middle;
            } else {
                high = middle;
            }
        }
        return -1;
    }

    /**
     * 普通查找，通过循环列表方式查找数据
     *
     * @param arr
     * @param val
     * @return
     */
    public static int normalSearch(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == val) {
                return i;
            }
        }
        return -1;
    }
}
