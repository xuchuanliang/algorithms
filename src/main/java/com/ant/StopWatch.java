package com.ant;

public class StopWatch {
    private long start;
    private long end;
    public StopWatch(){
        start = 0L;
        end = 0L;
    }
    public void start(){
        start = System.currentTimeMillis();
    }

    public void stop(){
        end = System.currentTimeMillis();
    }

    public void print(){
        System.out.println("task time is :"+(end-start));
    }

    public void stopAndPrint(){
        stop();
        print();
    }
}
