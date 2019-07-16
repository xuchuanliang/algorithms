package com.ant.capter02;

/**
 * 插入排序
 * 1.数组中每个元素距离它的最终位置都不远
 * 2.一个有序的大数组接一个小数组
 * 3.数组中只有几个元素的位置不正确
 */
public class Insertion extends Example{
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        for(int i=1;i<N;i++){
            //将a[i]插入到a[i-1],a[i-2],a[i-3]...中
            for(int j=i;j>0 && less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
            }
        }
    }

    public static void main(String[] args){
        Integer[] data = Example.getData(10000);
        Insertion insertion = new Insertion();
        insertion.sort(data);
        insertion.show(data);
    }
}
