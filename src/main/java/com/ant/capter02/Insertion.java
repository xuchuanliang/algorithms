package com.ant.capter02;

/**
 * 插入排序
 * 1.数组中每个元素距离它的最终位置都不远
 * 2.一个有序的大数组接一个小数组
 * 3.数组中只有几个元素的位置不正确
 */
public class Insertion extends Example{
    /**
     * 插入排序思想：
     * 从第一个元素开始，假定该元素左侧都是有序的，那么将该元素与左侧的所有有序元素比较，如果左侧相邻元素比他小，那么就交换两者位置，依次向左移动
     * @param a
     */
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

    public void test(Comparable[] a){
        for(int i=0;i<a.length;i++){
            for(int j = i+1; j>0 ;j--){
                if(less(a[j],a[j-1])){
                    exch(a,i,j);
                }
            }
        }
    }

    public static void main(String[] args){
        //14054633823
        //10856091112
        //14080545123
        Integer[] data = Example.getData(100000);
        Insertion insertion = new Insertion();
        long t = System.nanoTime();
        insertion.sort(data);
        System.out.println(System.nanoTime()-t);
        insertion.show(data);
    }
}
