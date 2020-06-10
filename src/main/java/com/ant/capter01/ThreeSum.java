package com.ant.capter01;

import com.ant.Util;

import java.util.Random;

/**
 * 统计数组中所有和为0的三整数元祖的数量
 */
public class ThreeSum {

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Util.println(new Random().nextInt(1));
        }
    }

    /**
     * 实现思路，从第一个数开始向后匹配，即三层循环，一个个向后遍历
     * @param a
     * @return
     */
    public static int count(int[] a){
        int count = 0;
        //从第一个数开始计算与后面的数相加是否等于0
        for(int i=0;i<a.length;i++){
            for(int j = i+1; j<a.length; j++){
                for(int k= j+1; k<a.length; k++){
                    if(a[i] + a[j] + a[k] == 0){
                        count++;
                    }
                }
            }
        }
        return count;
    }
}
