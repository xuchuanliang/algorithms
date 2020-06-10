package com.ant;

import java.util.Random;

public final class Util {
    public static int[] getArr(int size){
        int[] result = new int[size];
        Random random = new Random();
        for(int i =0;i<size;i++){
            result[i] = random.nextInt();
        }
        return result;
    }

    public int[] getArr(){
        return getArr(10);
    }

    public static void print(Object o){
        if(o instanceof  int[]){
            int[] ints = (int[]) o;
            System.out.print("[");
            for(int i=0;i<ints.length;i++){
                System.out.print(ints[i]);
                System.out.print(",");
            }
            System.out.print("]");
        }else{
            System.out.print(o);
        }
    }

    public static void println(Object o){
        if(o instanceof int[]){
            print(o);
            System.out.println("");
        }else{
            System.out.println(o);
        }
    }
}
