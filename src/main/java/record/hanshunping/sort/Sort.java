package record.hanshunping.sort;

import com.ant.StopWatch;

import java.util.Random;

public abstract class Sort {

    public static void main(String[] args) {
        Sort sort = null;
        //冒泡排序的主要思想是冒泡的思想，将大的逐步冒泡到右边，最后就有序；相对于选择排序，交换的次数比较多，每次从左向右遍历时，都将最大的元素逐渐交换到右边
        //10W时间：22621
        sort = new BubbleSort();
        sort.test(100000);

        //选择排序主要思想是选择，每次都选出最小的放在最左边，最后就有序了；相对于冒泡，交换的次数比较少，每次选出来最小的索引然后再进行交换
        //10W时间：4863
        sort = new SelectSort();
        sort.test(100000);


    }

    public abstract void sort(int arr[]);

    /**
     * 构造指定长度的数组并测试排序是否正确、打印出排序时间
     *
     * @param length
     */
    public void test(int length) {
        int bound = length * 100;
        int[] arr = new int[length];
        Random random = new Random();
        //获取随机数组
        for (int i = 0; i < length; i++) {
            arr[i] = random.nextInt(bound);
        }
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        sort(arr);
        stopWatch.stopAndPrint();
        System.out.println("是否有序：" + isSorted(arr));
    }

    /**
     * 数组是否有序：即是否升序
     *
     * @param arr
     * @return
     */
    public boolean isSorted(int[] arr) {
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 交换两个元素位置
     *
     * @param arr
     * @param index1
     * @param index2
     */
    public void swap(int[] arr, int index1, int index2) {
        int i = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = i;
    }
}
