package com.ant.capter02;

import java.util.Arrays;

/**
 * 选择排序：找到数组中最小的元素与第一个元素交换位置；其次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。如此往复，直到整个数组排序。
 * 选择排序的效率比较低，因为需要拿出来每一个与前面排过序的所有值进行对比，时间复杂度是
 * 选择排序是N²/2次比较和N次交换
 */
public class Selection extends Example{

    /**
     * 选择排序：将整个数组分为两个部分，左边是已经排序完成的部分，右边是待排序的部分，
     * 从数组的左侧开始，依次寻找右侧最小的元素，放在左侧数组的最右边
     * 思路如下：
     * [1,3,2,5,3,7]
     * [1][3,2,5,3,7]
     * [1,2][3,5,3,7]
     * [1,2,3][5,3,7]
     * [1,2,3,3][5,7]
     * [1,2,3,3,5][7]
     * [1,2,3,3,5,7]
     * @param a
     */
    @Override
    public void sort(Comparable[] a){
        for(int i=0;i<a.length;i++){
            for(int j=i+1;j<a.length;j--){
                //如果i位置的元素大于j的元素，那么就交换位置，直到i位置的元素是最小的
                //然后循环向右推动
                if(less(a[j],a[i])){
                    exch(a,i,j);
                }
            }
        }
    }

    public static void main(String[] args){
        //104638482
        Selection selection = new Selection();
        Integer[] data = Example.getData(100000);
        long t = System.nanoTime();
        selection.sort(data);
        System.out.println(System.nanoTime()-t);
        Arrays.stream(data).forEach(a->System.out.print(a+","));
    }
}
