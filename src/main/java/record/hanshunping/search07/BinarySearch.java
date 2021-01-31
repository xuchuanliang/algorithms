package record.hanshunping.search07;

/**
 * 二分查找：特点是基于有序的数组进行查找
 * 思路是：每次都将查询范围分成左右两半，如果比中间大则向右边查找，如果小则向左边查找
 */
public class BinarySearch extends Search {
    private static int count = 0;
    @Override
    public int search(int[] arr, int searchValue) {
        int index = search(arr, 0, arr.length - 1, searchValue);
        System.out.println("二分查找次数是："+count);
        return index;
    }

    /**
     * @param arr         查找的数组
     * @param leftIndex   左边界
     * @param rightIndex  右边界
     * @param searchValue 查找数据
     * @return
     */
    public int search(int[] arr, int leftIndex, int rightIndex, int searchValue) {
        count++;
        if (leftIndex > rightIndex) {
            return -1;
        }
        int mid = (leftIndex + rightIndex) / 2;
        if (arr[mid] == searchValue) {
            return mid;
        }
        if (arr[mid] > searchValue) {
            //比中间的元素要小，则查找左边
            return search(arr, leftIndex, mid - 1, searchValue);
        }

        if (arr[mid] < searchValue) {
            //比中间的元素大，则查找右边
            return search(arr, mid + 1, rightIndex, searchValue);
        }
        return -1;
    }
}
