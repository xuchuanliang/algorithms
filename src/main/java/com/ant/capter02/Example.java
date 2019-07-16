package com.ant.capter02;

import java.util.Arrays;

/**
 *
 */
public abstract class Example {

    /**
     * 排序
     * @param a
     */
    public abstract void sort(Comparable[] a);

    /**
     * 比较元素
     * @param v
     * @param w
     * @return
     */
    protected boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }

    /**
     * 交换位置
     * @param a
     * @param i
     * @param j
     */
    protected void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    protected void show(Comparable[] a){
        Arrays.stream(a).forEach(c->System.out.print(c+"->"));
    }

    public boolean isSorted(Comparable[] a){
        for(int i=1;i<a.length;i++){
            if(less(a[i],a[i-1])){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args){
    }

    /**
     * 一万长度数组，0-1000000产生
     * @return
     */
    public static Integer[] getData(int length){
        Integer[] a = new Integer[length];
        for(int i=0;i<a.length;i++){
            a[i] = (int)(Math.random()*100000000);
        }
        return a;
    }
}
