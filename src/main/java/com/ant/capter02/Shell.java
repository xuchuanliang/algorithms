package com.ant.capter02;

/**
 * 希尔排序
 */
public class Shell extends Example{
    @Override
    public void sort(Comparable[] a) {
        int N = a.length;
        int h = 1;
        while (h<N/3){
            //1,4,13,40,121,364....
            h = 3*h +1;
        }
        while (h>=1){
            for(int i=h;i<N;i++){
                //将a[i]插入到a[i-h]，a[i-2*h],a[i-3*h]....之中
                for(int j=i;j>=h && less(a[j],a[j-h]);i -= h){
                    exch(a,j,j-h);
                }
            }
            h=h/3;
        }
    }
    public static void main(String[] args){
        //122201792
        Integer[] data = Example.getData(100000);
        Insertion insertion = new Insertion();
        long t = System.nanoTime();
        insertion.sort(data);
        System.out.println(System.nanoTime()-t);
        insertion.show(data);
    }
}
