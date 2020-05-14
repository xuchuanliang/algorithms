package com.diagrammatize.capter01;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

public class Map1 {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedBlockingQueue<>();
        Map<String,String[]> map = build();
        String key = null;
        queue.offer("you");
        List<String> list = new ArrayList<>();
        Map<String,Boolean> searched = new HashMap<>();
        while ((key=queue.poll())!=null){
            String[] strings = map.get(key);
            if(strings.length>0){
                for(String s:strings){
                    //如果这个人已经被搜索过了
                    if(null != searched.get(s)){
                        continue;
                    }
                    searched.put(s,true);
                    if("jonny".equals(s)){
                        System.out.println("success:"+s);
                    }else{
                        queue.offer(s);
                    }
                }
            }
        }
        for(int i=0;i<100;i++){
            if(i % 10 != 0){
                continue;
            }else{
                System.out.println(i);
            }
        }
    }

    /**
     * 创建图
     * @return
     */
    public static Map<String,String[]> build(){
        Map<String,String[]> map = new HashMap<>();
        map.put("you",new String[]{"alice","bob","claire"});
        map.put("bob",new String[]{"anuj","peggy"});
        map.put("alice",new String[]{"peggy"});
        map.put("claire",new String[]{"thom","jonny"});
        map.put("anuj",new String[]{});
        map.put("peggy",new String[]{});
        map.put("thom",new String[]{});
        map.put("jonny",new String[]{});
        return map;
    }
}
