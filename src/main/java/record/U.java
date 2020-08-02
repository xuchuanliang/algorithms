package record;

import record.book.capter03.ST;

import java.util.Random;

public final class U {
    /**
     * 获取指定长度的数组
     * @param length
     * @return
     */
    public static int[] getArr(int length){
        Random random = new Random();
        int[] result = new int[length];
        for(int i=0;i<length;i++){
            result[i] = random.nextInt(length);
        }
        return result;
    }

    /**
     * 打印数组中的元素
     * @param arr
     */
    public static void print(int[] arr){
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<arr.length;i++){
            if(arr[i] <10 ){
                stringBuffer.append("   ");
            }else if(arr[i] < 100){
                stringBuffer.append("  ");
            }else if(arr[i] < 1000){
                stringBuffer.append(" ");
            }
            stringBuffer.append(arr[i]).append(",");
        }
        System.out.println(stringBuffer.toString());
    }

    /**
     * 交换数组中两个元素的位置
     * @param arr
     * @param firstIndex
     * @param secondIndex
     */
    public static void swap(int[] arr,int firstIndex,int secondIndex){
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    /**
     * 将字符创按照英文逗号分割成数组
     * @param s
     * @return
     */
    public static int[] transfer(String s){
        String[] split = s.split(",");
        int[] result = new int[split.length];
        for(int i = 0;i<split.length;i++){
            if(null!=split[i] && split[i] != ""){
                result[i] = Integer.parseInt(split[i].trim());
            }
        }
        return result;
    }

    /**
     * 获取一个指定长度和高度的二位数组
     * @param high
     * @param width
     * @return
     */
    public static int[][] getTwoArr(int high,int width){
        int[][] arr = new int[high][width];
        int val = 0;
        for(int i=0; i<high; i++){
            for(int j=0; j<width; j++){
                arr[i][j] = val++;
            }
        }
        return arr;
    }

    /**
     * 打印二维数组
     * @param arr
     */
    public static void print(int[][] arr){
        for(int i=0;i<arr.length;i++){
            print(arr[i]);
        }
    }

    /**
     * 判断数组是否有序
     * @param arr
     */
    public static void assertSort(int[] arr){
        int i=0;
        while (i<arr.length-2){
            if(arr[i] > arr[i+1]){
                System.err.println("数组无序");
                return;
            }
            i++;
        }
        System.out.println("数组有序");
    }

    /**
     * 填充指定长度的符号表/字典
     * @param st
     * @param length
     */
    public static void fillST(ST<String,String> st,int length){
        for(int i=0;i<length;i++){
            st.put("key:"+i,"value:"+i);
        }
    }

    public static String buildSTKey(int i){
        return "key:"+i;
    }

    public static String buildSTValue(int i){
        return "value:"+i;
    }

    /**
     * a是否比B小
     * @param a
     * @param b
     * @return
     */
    public static boolean less(Comparable a,Comparable b){
        return a.compareTo(b) < 1;
    }
}
