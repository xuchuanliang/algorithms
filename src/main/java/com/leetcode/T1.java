package com.leetcode;

import java.util.HashSet;
import java.util.Set;

public class T1 {
    public static void main(String[] args) {
        String[] s = new String[]{"a=!a"};
        System.out.println(equationsPossible(s));
    }

    public static boolean equationsPossible(String[] equations){
        Set<Character> equality = new HashSet<Character>(26);
        Set<Character> ineuqality = new HashSet<Character>(26);
        for(String s:equations){
            if('!'==s.charAt(1)){
                ineuqality.add(s.charAt(0));
                ineuqality.add(s.charAt(3));
            }else{
                equality.add(s.charAt(0));
                equality.add(s.charAt(3));
            }
        }
        if(equality.size()>0 && ineuqality.size()>0){
            int count = 0;
            for(Character e:equality){
                if(ineuqality.contains(e)){
                    count++;
                }
                if(count>2){
                    return false;
                }
            }
        }
        return true;
    }

    public static int cutRope(int number){
        //只要绳子长度<=4，那么分段最大乘积就是本身
        if(number<=4){
            return number;
        }

    }
}
