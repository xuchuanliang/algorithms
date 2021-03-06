package com.ant.capter02;

import com.ant.StopWatch;
import com.ant.Util;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class Shell extends Example{

    /**
     * 前提：数组名称为arr
     * 希尔排序是建立在插入排序的基础上，放大有序数组的范围，插入排序是每次和相邻的元素比较，希尔排序可能是以h为跨度进行比较，然后逐渐缩小h，直到h变为1，整个数组也从无序到逐渐有序到完全有序
     * 希尔排序的基本思路与插入排序类似，
     * 不同点在于，
     * 插入排序的基本思想：将arr分为左侧和右侧两个数组a和b，其中a为有序数组，最初只有第一个元素，b为无序数组；
     * 假设当前索引为index，那么初始情况index=1，将当前索引元素逐步与a数组从右向左的元素比较，只要小于数组中元素就交换位置，逐步交换位置（即向左推动），直到左侧小于右侧
     * 希尔排序：
     * 希尔排序：
     * 插入排序对于大规模乱序数组插入排序很慢，因为他只会交换相邻的元素，因此元素只能一点一点的从数组的一段移动到另一端。
     * 希尔排序的思想是使数组中任意间隔为h的元素都是有序的。这样的数组被称为h有序数组。换句话说，一个h有序数组就是h个
     * 互相独立的有序数组编织在一起组成一个数组。在进行排序时，如果h很大，我们就能将元素移到很远的地方。
     * 如下方所示，假设a的长度是10，第一次循环h=5，那么将索引9-4，8-3，7-2，6-1，5-0这5个逻辑数组的顺序使用插入排序排好；
     * 第二次循环h=2，那么将索引9-7，8-6，7-5，6-4，5-3，4-2，3-1，2-0这8个逻辑数组的顺序使用插入排序排好
     * 第三次循环h=1，那么将索引9-8，8-7，7-6，6-5，5-4，4-3，3-2，2-1，1-0这9个数组的顺序排好，那么整个数组的顺序就排序完成
     *
     * @param a
     */
    @Override
    public void sort(Comparable[] a) {
        int h = 1;
        while (h<a.length/3){
            h = 3*h+1;
        }
        while ( h>0 ){
            for(int i=h;i<a.length;i++){
                for(int j=i; j>=h; j-=h){
                    if(less(a[j],a[j-h])){
                        exch(a,j,j-h);
                    }
                }
            }
            h = h/3;
        }
    }




    public static void main(String[] args){
        Integer[] arr = Util.getArr(50000);
        Integer[] a1 = Arrays.copyOf(arr,arr.length);
        Integer[] a2 = Arrays.copyOf(arr,arr.length);
        Integer[] a3 = Arrays.copyOf(arr,arr.length);
        Selection selection = new Selection();
        Insertion insertion = new Insertion();
        Shell shell = new Shell();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        selection.sort(a1);
        stopWatch.stopAndPrint();
        stopWatch.start();
        insertion.sort(a2);
        stopWatch.stopAndPrint();
        stopWatch.start();
        shell.sort(a3);
        stopWatch.stopAndPrint();
        selection.show(a1);
        System.out.println("");
        selection.show(a2);
        System.out.println("");
        selection.show(a3);
        System.out.println("");

    }
}
