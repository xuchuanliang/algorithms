package com.diagrammatize.capter01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用狄克斯特拉算法找出最短路径
 */
public class Map2 {

    private static final String S1 = "乐谱";
    private static final String S2 = "海报";
    private static final String S3 = "稀有黑胶唱片";
    private static final String S4 = "架子鼓";
    private static final String S5 = "低音吉他";
    private static final String S6 = "钢琴";

    public static void main(String[] args) {
        Map<String,Entity> map = build();
        Entity entity = map.get(S1);
        List<Node> list = new ArrayList<>();
        if(null!=entity.next){
            entity.next.entrySet().forEach(e->{

            });
        }
    }


    /**
     * 创建带有权重的图
     * @return
     */
    public static Map<String,Entity> build(){
        Map<String,Entity> map = new HashMap<>();

        //乐谱
        Entity e1 = new Entity(S1);
        e1.next.put(S2,0);
        e1.next.put(S3,5);
        //海报
        Entity e2 = new Entity(S2);
        e2.next.put(S4,35);
        e2.next.put(S5,30);
        //稀有黑胶唱片
        Entity e3 = new Entity(S3);
        e3.next.put(S5,15);
        e3.next.put(S4,20);
        //架子鼓
        Entity e4 = new Entity(S4);
        e4.next.put(S6,10);
        //低音吉他
        Entity e5 = new Entity(S5);
        e5.next.put(S6,20);
        map.put(S1,e1);
        map.put(S2,e2);
        map.put(S3,e3);
        map.put(S4,e4);
        map.put(S5,e5);
        return map;
    }
}
class Entity{
    public String name;
    public Map<String,Integer> next;

    public Entity() {
    }

    public Entity(String name) {
        this.name = name;
    }
}

class Node {
    private String name;
    private String parent;
    private int sum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}

