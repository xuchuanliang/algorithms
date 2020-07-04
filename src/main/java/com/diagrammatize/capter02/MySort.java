package com.diagrammatize.capter02;

import com.ant.Util;

/**
 * 插入排序
 * 基本思路：整个数组分为A和B两段，其中A是有序的，B是无序的，默认开始A之后1个元素，主键将B的第一个元素插入到A中，扩大A的长度，减少B的长度，直到最后一个被插入完成
 */
public class MySort {
    public static void main(String[] args) {
        int[] intArr = Util.getIntArr(1000000);
        insertSort(intArr);
        Util.assertSort(intArr);
    }

    public static void insertSort(int[] arr) {
        //1.默认A中只有一个元素0，B的元素1-arr.length-1
        //则B的第一个元素从1开始，用rightIndex标识
        int rightIndex = 1;
        //只有B的第一个元素比arr.length小，即还没有到最右端
        while (rightIndex < arr.length) {
            //取出右侧第一个元素，并将其插入到A中,此时A的索引范围是0-rightIndex-1
            int rightFirstEle = arr[rightIndex];
            insertEle(arr, 0, rightIndex - 1, rightFirstEle);
            rightIndex++;
        }
    }

    /**
     * @param arr        源数组
     * @param rangeLeft  插入操作的数组范围左侧索引
     * @param rangeRight 插入操作的数组范围右侧索引
     * @param element    要插入的元素
     */
    public static void insertEle(int[] arr, int rangeLeft, int rangeRight, int element) {
        //遍历数组的元素，从rangeLeft开始到rangeRight结束，找到element的位置，并插入操作
        int insertIndex = rangeLeft;
        if (element <= arr[rangeLeft]) {
            //如果element比第一个元素还小或者等于第一个元素，则插入到第一个位置
            insertIndex = rangeLeft;
        } else {
            while (rangeLeft <= rangeRight) {
                //遍历查找element要插入的位置
                //如果遍历的第一个位置时
                //如果已经遍历到了尾部，则要么插入到最后一个元素前面，要么插入最后一个元素后面
                if (rangeLeft == rangeRight) {
                    insertIndex = element > arr[rangeLeft] ? rangeLeft + 1 : rangeLeft;
                    break;
                }
                //如果element与两个要比较的元素之一相等，如果左侧与element相等，则插入左侧元素后面，如果右侧元素与element相等，则插入到右侧元素前面，即都是插入到rangeLeft+1的位置
                if (arr[rangeLeft] == element || arr[rangeLeft + 1] == element) {
                    insertIndex = rangeLeft+1;
                    break;
                }
                //否则，如果element比rangeLeft位置元素大，比rangeLeft+1位置元素小，则插入到rangeLeft+1位置，并跳出循环
                if (arr[rangeLeft] < element && arr[rangeLeft+1] > element) {
                    insertIndex = rangeLeft+1;
                    break;
                }
                rangeLeft++;
            }
        }
        //找到要插入的位置，进行插入操作，如果是rangRight+1，则无需插入
        if (insertIndex == rangeRight + 1) {
            return;
        }
        //开始插入，从右到左元素向右移一位，知道空出来insertIndex这个位置
        while (rangeRight >= insertIndex) {
            arr[rangeRight + 1] = arr[rangeRight];
            rangeRight--;
        }
        //将element插入到insertIndex位置
        arr[insertIndex] = element;
    }

}
