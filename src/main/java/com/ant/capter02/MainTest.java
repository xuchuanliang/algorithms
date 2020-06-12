package com.ant.capter02;

import com.ant.Util;

public class MainTest {
    /**
     * 选择排序：从左到右从剩下的元素中查询最小的元素，并且放在有序队列的最右边
     */
    public void selection(int[] arr){
        for(int i=0; i<arr.length-1; i++){
            for(int j=i+1;j< arr.length;j++){
                if(arr[i] > arr[j]){
                    exchange(arr,i,j);

                }
            }
        }
    }

    /**
     * 插入排序：从左到右将依次将右边无序数据的第一个逐个向左比较，插入到合适的位置
     * @param arr
     */
    public void inertion(int[] arr){

    }

    public void exchange(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        MainTest mainTest = new MainTest();
        int[] arr = Util.getIntArr(100);
        mainTest.selection(arr);
        Util.print(arr);
    }
}
