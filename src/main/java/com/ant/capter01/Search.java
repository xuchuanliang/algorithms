package com.ant.capter01;

import java.util.Random;

/**
 * 查找
 */
public class Search {
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
        int start = 0;
        int end = arr.length-1;
        while (start < end){
            int middle = (start+end)/2;
            int middleVal = arr[middle];
            if(middleVal == val){
                //如果刚好查到
                return middle;
            }else if(middleVal > val){
                //如果中间值大于val，则在左边，则二分查询start-->middle中间的数组
                end = middle;
            }else {
                //如果中间值小于val，则在右边，则二分查找middle-->end中间的数组
                start = middle;
            }
        }
        //如果start >= end还未查询到，则未查询到
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
