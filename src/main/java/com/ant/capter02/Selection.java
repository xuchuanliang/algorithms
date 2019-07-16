package com.ant.capter02;

import java.util.Arrays;

/**
 * 选择排序
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
        Selection selection = new Selection();
        Integer[] data = Example.getData(10000);
        selection.sort(data);
        Arrays.stream(data).forEach(a->System.out.print(a+","));
    }
}
