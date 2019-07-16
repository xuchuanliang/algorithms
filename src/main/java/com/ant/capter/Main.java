package com.ant.capter;

public class Main {
    public static void main(String[] args){
        System.out.println(gcd(100,8888));
    }

    /**
     * 计算p和q的最大公约数
     * @param p
     * @param q
     * @return
     */
    public static int gcd(int p,int q){
        if(q==0) return p;
        int r = p % q;
        return gcd(q,r);
    }
}
