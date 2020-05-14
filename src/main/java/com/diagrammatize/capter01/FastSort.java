package com.diagrammatize.capter01;

import java.util.Random;
import java.util.logging.Level;

/**
 * 快速排序
 * 思路：选中一个基准点，将数组分成基准点左边是比基准点小，右边是比基准点大的两个数组，采用递归的思想，直到数组长度为1或空的数组表示不用排序
 */
public class FastSort {
    public static void main(String[] args) {
        int[] arr = buildArr(10000000);
//        printArr(arr);
        fastSort(arr);
//        printArr(arr);

    }

    public static void fastSort(int[] arr){
        fastSort(arr, 0,arr.length-1);
    }

    public static void fastSort(int[] arr,int left,int right){
        if(arr.length<2){
            return;
        }
        if(left >= right){
            return;
        }
        //取给定数组范围的左边第一个作为基准数值
        int base = arr[left];
        //循环将left和right向中间推进
        //若left遇到比基准值大的则停止
        //若right遇到比基准值小的则停止
        //若left和right没有相遇则交换二者之间的位置
        //与基准值相同的放在右边
        int tempLeft = left + 1;
        int tempRight = right;
        while (tempLeft < tempRight){
            while (arr[tempLeft] <= base && tempLeft < tempRight ){
                tempLeft++;
            }
            while (arr[tempRight] > base && tempLeft < tempRight){
                tempRight--;
            }
            //只要当前还未相遇，则交换left和right的位置
            if(tempLeft<tempRight){
                swap(arr,tempLeft,tempRight);
            }
        }
        //将基准数放在数组中间
        //经过上方循环到此处，则left和right已经相遇
        //对比此处的数字与基准数字的大小，若基准数大则放在右边，若基准数小则将基准值与当前值互换即可，因为左侧存放比基准值小的数，不要求顺序，只要求分类
        //记录此时左边数组和右边数组的分界点，即基准点的索引
        int index;
        if(arr[tempLeft] > base){
            //放在左边
            swap(arr,left,tempLeft-1);
            index = tempLeft-1;
        }else{
            swap(arr,left,tempLeft);
            index = tempLeft;
        }
        //递归将两边的数组按照相同的办法进行排序

        fastSort(arr,left,index-1);
        fastSort(arr,index+1,right);
    }

    /**
     * 交换数组中两个元素的位置
     * @param arr
     * @param left
     * @param right
     */
    public static void swap(int[] arr,int left,int right){
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    /**
     * build给定length的数组
     * @param length
     * @return
     */
    public static int[] buildArr(int length){
        int[] arr = new int[length];
        Random random = new Random();
        for(int i=0;i<length;i++){
            arr[i] = random.nextInt(length);
        }
        return arr;
    }

    /**.
     * 打印数组
     * @param arr
     */
    public static void printArr(int[] arr){
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<arr.length;i++){
            stringBuffer.append(arr[i]+",");
        }
        System.out.println(stringBuffer.toString());
    }
}
