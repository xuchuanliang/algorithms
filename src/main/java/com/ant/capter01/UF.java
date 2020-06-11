package com.ant.capter01;

public interface UF {
    /**
     * 连通分量的数量
     * @return
     */
    int count();

    /**
     * 判断位置i的触点和位置j触点之间是否已经连接
     * @param i
     * @param j
     * @return
     */
    boolean isConnected(int i,int j);

    /**
     * 查找i位置的值
     * @param i
     * @return
     */
    int find(int i);

    /**
     * 在将位置i的触点与j触点建立连接
     * @param i
     * @param j
     */
    void union(int i,int j);

}
