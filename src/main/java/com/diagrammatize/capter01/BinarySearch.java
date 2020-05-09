package com.diagrammatize.capter01;

/**
 * 二分查找法：给定有序数组以及需要查找的值，找到该值在数组中的位置，如果没有则返回null
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[10000];
        for(int i=0;i<10000;i++){
            arr[i] = i;
        }
        Integer integer = binarySearch(arr, 4898);
        System.out.print(integer);
    }

    /**
     * 二分查找法的思路：给定一个从小到大排列的数组以及需要查找的值
     * 设定查询的最大索引和最小索引，默认是当前数组的最大索引合最小索引
     * 将值与该数组中间的值进行循环比较
     * @param arr
     * @param val
     * @return
     */
    public static Integer binarySearch(int[] arr,int val){
        int low = 0;
        int high = arr.length-1;
        while (low <= high){
            int middle = (low + high)/2;
            int guss = arr[middle];
            if(guss == val){
                return middle;
            }else if(guss < val){
                low = middle;
            }else{
                high = middle;
            }
        }
        return null;
    }
}
