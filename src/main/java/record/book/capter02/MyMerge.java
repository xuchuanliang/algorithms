package record.book.capter02;

/**
 * 归并排序
 * 采用分治递归的思想，将如需整个数组排序，则先将数组分成两半，两半的数组分别排好顺序，然后将两半的数组合并成一个有序数组
 * 递归将数组分成两半，直到数组左边和右边都只有一个数，则直接将左右两个数合并称为一个有序数组即可
 */
public class MyMerge {
    //合并后的结果
    private static int[] result;
    //已经合并的数组索引位置
    private static int index = 0;

    public static void sort(int[] arr){
        result = new int[arr.length];
        sort(arr,0,arr.length-1);
    }

    private static void sort(int[] arr,int left,int right){
        //如果left>=right，则表示只剩下一个元素了，则return
        //如果right大于left，例如left=1，right=2，那么继续将数组拆分，直到只有一个元素为止，
        if(left >= right){
            return;
        }
        //例如3，4，那么中间元素索引是3，则拆分成[3]和[4]两个数组，这两个数组因为只有一个元素，默认是有序的，然后将[3]和[4]合并成为一个数组
        //如果任意一边的元素个数都大于2，那么继续拆分，直到左右两表都是一个最终合并
        int mid = left + (right-left)/2;
        sort(arr,left,mid);
        sort(arr,mid+1,right);
        merge(arr,left,mid,right);
    }


    private static void merge(int[] arr,int left,int mid,int right){
        int temp_left = left;
        int temp_right = mid+1;
        index = left;

        for(int i=left;i<=mid;i++){
            if(arr[temp_left] < arr[temp_right]){
                result[index++] = arr[temp_left++];
            }else{
                result[index++] = arr[temp_right++];
            }
        }

        //如果左侧还有元素，则说明右侧的元素已经全部放入数组中，则顺序将左侧元素放在数组中即可
        while (temp_left <= mid){
            result[index++] = arr[temp_left++];
        }
        //如果右侧还有元素，则说明左侧元素已经全部放入数组中，则顺序将右侧元素放入数组中即可
        while (temp_right<=right){
            result[index++] = arr[temp_right++];
        }
        //将result中元素重新放回到原数组中
        for(int i=left; i<=right; i++){
            arr[i] = result[i];
        }
    }
}

