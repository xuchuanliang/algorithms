package record.niuke;

public class C2 {
    /*
    描述：给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。
    思路：如果从左上角开始搜索，那么只能从左到右，从上到下搜索；如果从右上角开始，因为最右边就是该行最大的数字，那么可以先定位
    到多少行，然后再从该行进行搜索；同样的也可以从左下角开始搜索，先确定是那一列，再从这一列向上找
    官方思路：
    要求时间复杂度 O(M + N)，空间复杂度 O(1)。其中 M 为行数，N 为 列数。
    该二维数组中的一个数，小于它的数一定在其左边，大于它的数一定在其下边。因此，从右上角开始查找，就可以根据 target 和当前元素的大小关系来缩小查找区间，当前元素的查找区间为左下角的所有元素。
     */
    public static boolean find(int target, int[][] matrix){
        int high = matrix.length;
        int width = matrix[0].length;
        //首先从右上角开始向下搜索，定位到某一行
        int tempHigh = 0;
        while (tempHigh < high && matrix[tempHigh][width-1] < target){
            tempHigh++;
        }
        if(tempHigh >= high){
            return false;
        }
        //从该行开始向右搜索，采用二分法
        int t = binarySearch(matrix[tempHigh],target);
        if(-1 != t){
            System.out.println("位置是：" + (tempHigh+1) + "行," + (t+1) + "列");
            return true;
        }else{
            System.out.println("未找到");
            return false;
        }
    }

    private static int binarySearch(int[] arr,int value){
        int left = 0;
        int right = arr.length-1;
        while (left <= right){
            int middle = (left+right)/2;
            if(arr[middle] == value){
                return middle;
            }
            if(value > arr[middle]){
                //在右边
                left = middle+1;
            }else{
                //在左边
                right = middle-1;
            }
        }
        return -1;
    }
}
