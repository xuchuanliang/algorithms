package com.ant.capter02;

/**
 * 插入排序
 * 1.数组中每个元素距离它的最终位置都不远
 * 2.一个有序的大数组接一个小数组
 * 3.数组中只有几个元素的位置不正确
 * 缺点：只会交换相邻的两个元素，并且交换顺序，无法一次到位，效率不高
 * 对基本有序或规模较小的排序适用
 */
public class Insertion extends Example {
    /**
     * 插入排序：将整个数组分为两部分，左边是已经排序完成的部分，右边是待排序的部分，
     * 依次把右边数组第一个元素与左侧数组从左向右依次进行比较，若该元素小于比较的元素，则逐步交换位置
     * 思路如下：
     * [1,3,2,5,3,7]
     * [1][3,2,5,3,7]
     * [1,3][2,5,3,7]
     * [1,2,3][5,3,7]
     * [1,2,3,5][3,7]
     * [1,2,3,3,5][7]
     * [1,2,3,3,5,7]

     * @param a
     */
    @Override
    public void sort(Comparable[] a){
        for(int i=1;i<a.length;i++){
            for(int j=i; j>0;j--){
                if(less(a[j],a[j-1])){
                    exch(a,j,j-1);
                }else{
                    //如果已经比左侧元素大，那么就不需要再向后比较了
                    break;
                }
            }
        }
    }



    public static void main(String[] args) {
        //14054633823
        //10856091112
        //14080545123
        Integer[] data = Example.getData(10000);
        Insertion insertion = new Insertion();
        long t = System.nanoTime();
        insertion.sort(data);
        System.out.println(System.nanoTime() - t);
        insertion.show(data);
    }
}
