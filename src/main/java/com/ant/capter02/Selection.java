package com.ant.capter02;

import java.util.Arrays;

/**
 * 选择排序：找到数组中最小的元素与第一个元素交换位置；其次，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。如此往复，直到整个数组排序。
 * 选择排序是N²/2次比较和N次交换
 */
public class Selection extends Example{

    @Override
    public void sort(Comparable[] a) {
        //将a[]按照升序排列
        int N = a.length;
        for(int i=0;i<N;i++){
            int min = i;
            for(int j=i+1;j<N;j++){
                if(super.less(a[j],a[min])) min = j;
            }
            exch(a,i,min);
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
