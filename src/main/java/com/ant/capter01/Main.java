package com.ant.capter01;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args){
//        System.out.println(gcd(100,8888));
        try {
            read();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();
    private static final int MAX_LINE = 3000;

    public static void read()throws Exception{
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("D:\\a.txt"));
        File file = new File("D:\\code\\idea_code\\HikMJYL\\HikMJYL\\src\\com\\hkbt\\mjyl");
        Queue<File> queue = new LinkedList<>();
        queue.offer(file);
        while (!queue.isEmpty()){
            File poll = queue.poll();
            if(poll.isDirectory()){
                File[] files = poll.listFiles();;
                if(null!=files && files.length>0){
                    for(File f:files){
                        if(f.isDirectory()){
                            queue.offer(f);
                        }else if(f.canRead() && f.getName().endsWith(".java")){
                            if(ATOMIC_INTEGER.get() >= MAX_LINE){
                                System.err.println("已经有："+ATOMIC_INTEGER.get()+"行，停止输出");
                                return;
                            }
                            bufferedReader = new BufferedReader(new FileReader(f));
                            doWriter(bufferedReader,bufferedWriter);
                            bufferedWriter.flush();
                            bufferedReader.close();
                        }
                    }
                }
            }
        }
        bufferedWriter.close();
    }

    public static void doWriter(BufferedReader bufferedReader,BufferedWriter bufferedWriter) throws Exception{
        String s = null;
        while ((s=bufferedReader.readLine())!=null){
            if(!"".equals(s) && !"\r".equals(s) && !"\r\n".equals(s) && !"\n".equals(s)){
                bufferedWriter.newLine();
                bufferedWriter.write(s);
                ATOMIC_INTEGER.incrementAndGet();
            }else{
                System.err.println(s);
            }
        }
    }
}
