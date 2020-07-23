package record.book.capter02;

/**
 * 归并排序
 * 采用分治递归的思想，将如需整个数组排序，则先将数组分成两半，两半的数组分别排好顺序，然后将两半的数组合并成一个有序数组
 * 递归将数组分成两半，直到数组左边和右边都只有一个数，则直接将左右两个数合并称为一个有序数组即可
 */
public class MyMerge {

    /**
     * 思路：采用分治递归的思想，将数组递归拆分两半，然后将左右两边的数组排完序后，再将两边数组合并成为一个新的有序数组
     * 1.主函数
     * 2.
     */
    public static void sort(int[] arr){
        sort(arr,0,arr.length-1);
    }

    /**
     * 将数组分成左右两半，左边是left-mid，右边是mid+1-right
     * 递归将左边和右边的数组分别进行排序，然后将左右两边有序数组合并成为一个大的有序数组
     * 分治递归的思想是：如果左边和右边都只有一个元素，那么就可以认为左边和右边都是有序数组，然后直接合并即可
     * @param arr
     * @param left
     * @param right
     */
    private static void sort(int[] arr,int left,int right){
        //如果元素的个数只有一个，那么认为是有序的
        if(left == right){
            return;
        }
        int mid = (left+right)/2;
        //排序左半部分
        sort(arr,left,mid);
        //排序右半部分
        sort(arr,mid+1,right);
        //左边和右边有序了之后，将左右两边的数组进行合并
        merge(arr,left,mid,right);
    }

    private static void merge(int[] arr,int left,int mid,int right){
        //定一个存储左右两边元素的临时数组
        int[] temp = new int[right-left+1];
        int temp_index = 0;
        //将左边和右边的数组中的有序元素合并到临时数组中
        int temp_left = left;
        int temp_right = mid+1;
        //只要左侧和右侧数组都还有元素，那么就比较两个元素，并且将小的放在临时数组中
        while (temp_left<=mid && temp_right<=right){
            if(arr[temp_left]<arr[temp_right]){
                temp[temp_index++] = arr[temp_left++];
            }else{
                temp[temp_index++] = arr[temp_right++];
            }
        }
        //上方循环完成后，则左侧或右侧数组有一个数组已经空了，则将另外一个非空的数组循环放入到临时数组中
        while (temp_left<=mid){
            temp[temp_index++] = arr[temp_left++];
        }
        while (temp_right<=right){
            temp[temp_index++] = arr[temp_right++];
        }
        //最终临时数组中是有序的数组，则顺序将临时数组中的元素放置在arr中的left到right的位置
        for(int i=0;i<temp_index;i++){
            arr[left+i] = temp[i];
        }
    }
}

