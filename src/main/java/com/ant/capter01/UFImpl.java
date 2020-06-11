package com.ant.capter01;

public class UFImpl implements UF{
    private int[] ids;
    private int count;

    @Override
    public int count(){
        return count;
    }

    public UFImpl(int n){
        ids = new int[n];
        for(int i=0;i<n;i++){
            ids[i] = i;
        }
        count = n;
    }

    @Override
    public boolean isConnected(int i, int j){
        return find(i) == find(j);
    }

    @Override
    public int find(int i){
        return ids[i];
    }

    @Override
    public void union(int i, int j){
        int im = ids[i];
        int jm = ids[j];
        if(im == jm){
            //已经建立了连接
            return;
        }
        //如果还没有建立连接，那么需要将所有与i的连接点与j的连接点变成相同的数值，标识为连接
        //可以变成i的值或者j的值，只要保证一样即可
        for(int k=0;k<ids.length;k++){
            if(ids[k]==jm){
                ids[k] = im;
            }
        }
    }
    
}
