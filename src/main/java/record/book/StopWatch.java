package record.book;

public class StopWatch {

    private static long start;

    private static long end;

    public static void start(){
        start = System.currentTimeMillis();
    }

    public static void stopAndPrint(String name){
        end = System.currentTimeMillis();
        System.err.println(name+"执行的时间是："+(end-start));
    }
}
