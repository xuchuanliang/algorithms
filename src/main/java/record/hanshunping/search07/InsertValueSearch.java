package record.hanshunping.search07;

/**
 * 差值查找算法
 * 产生的背景是：
 * 假设有一个有序数组[0,1,2,3,4....100]，如果寻找0，则使用二分查找需要查找的次数是50，25，12，6，3，1，0；其实不需要这么多次，可以在查找过程中自动调整折半的距离
 * 因此就有了差值查找算法：
 * 插值查找算法类似于二分查找， 不同的是插值查找每次从自适应 mid 处开始查找。
 * mid的公式是：int mid = low + (high - low) * (findValue - arr[low]) / (arr[high] - arr[low])
 * 假设还是查找0，那么int mid = 0+(99-0)*(0-arr[0])/(arr[99]-arr[0])=0+99*0/99=0;
 * 注意：由于差值查找的主要思想也是有序数组，那么要求的数组也是有序的
 *
 * 差值查找的问题：
 * 对于数据量较大， 关键字分布比较均匀的查找表来说， 采用插值查找, 速度较快
 * 关键字分布不均匀的情况下， 该方法不一定比折半查找要好
 */
public class InsertValueSearch extends Search {

    private static int count = 0;

    @Override
    public int search(int[] arr, int searchValue) {
        int index =  searchValue(arr, 0, arr.length - 1,0);
        System.out.println("差值查找算法的查找次数是："+count);
        return index;
    }

    /**
     * 差值查找算法
     *
     * @param arr       需要查找的数组
     * @param left      左侧索引
     * @param right     右侧索引
     * @param findValue 需要查找的值
     * @return 如果找到则返回查找值的索引，否则返回-1
     */
    private int searchValue(int[] arr, int left, int right, int findValue) {
        count++;
        /*
        注意：findValue < arr[left] || findValue > arr[right] 这一句判断必须要，因为注意一下mid的计算公式，如果没有判断则会出现数组越界的情况
         */
        if (left > right || findValue < arr[left] || findValue > arr[right]) {
            //如果左侧索引大于右侧索引或查找的值比最左侧的值还小或查找的值比最右侧的值还大，那么说明根本不在查找范围内
            return -1;
        }
        int mid = left + (right - left) * (findValue - arr[left]) / (arr[right] - arr[left]);
        if (findValue == arr[mid]) {
            //找到了
            return mid;
        } else if (findValue > arr[mid]) {
            //在右边
            return searchValue(arr, mid+1, right, findValue);
        } else {
            //在左边
            return searchValue(arr, left, mid-1, findValue);
        }
    }
}
