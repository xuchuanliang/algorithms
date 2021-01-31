package record.hanshunping.search07;

import java.util.Arrays;

public class FibonacciSearch {

    public static void main(String[] args) {
//        int[] fibArr = getFibRecursion(30);
        int[] fibArr = getFibCirculation(300);
        System.out.println(Arrays.toString(fibArr));
    }

    /**
     * 非递归方式获取斐波那契数列
     * @return
     */
    public static int[] getFibCirculation(int n){
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            if(i<2){
                //前两个都是1
                arr[i] = 1;
            }else{
                arr[i] = arr[i-1]+arr[i-2];
            }
        }
        return arr;
    }

    /**
     * 递归获取一个长度为n的斐波那契数列
     * @param n
     * @return
     */
    public static int[] getFibRecursion(int n){
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = getf(i);
        }
        return arr;
    }

    public static int getf(int n){
        if(n<2){
            return 1;
        }
        return getf(n-1)+getf(n-2);
    }
}
