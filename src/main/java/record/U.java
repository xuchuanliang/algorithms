package record;

import java.util.Random;

public final class U {
    public static int[] getArr(int length){
        Random random = new Random();
        int[] result = new int[length];
        for(int i=0;i<length;i++){
            result[i] = random.nextInt(length);
        }
        return result;
    }

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

    public static void swap(int[] arr,int firstIndex,int secondIndex){
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

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

    public static void print(int[][] arr){
        for(int i=0;i<arr.length;i++){
            print(arr[i]);
        }
    }

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
}
