package record.hanshunping.sort6;

/**
 * 插入排序：
 * 插入排序（Insertion Sorting） 的基本思想是： 把 n 个待排序的元素看成为一个有序表和一个无序表， 开始时有
 * 序表中只包含一个元素， 无序表中包含有 n-1 个元素， 排序过程中每次从无序表中取出第一个元素， 把它的排
 * 序码依次与有序表元素的排序码进行比较， 将它插入到有序表中的适当位置， 使之成为新的有序表。
 * 说人话就是：从左到右，假设第一个元素是有序的，那么从第二个元素开始，选择合适的位置插入到前面有序的数组中，最终到最后一个元素插入完成后，则整个数组有序
 */
public class InsertSort extends Sort {
    @Override
    public void sort(int[] arr) {
        //从第二个元素开始，第一个元素只有一个，所以是有序的
        int unSortIndex = 1;
        //从第二个元素开始遍历直到最后一个元素，依次插入到左边的合适的位置
        while (unSortIndex < arr.length) {
            /*
            思路是：
            1.首先将需要插入的元素存在一个临时变量中
            2.向左依次遍历元素，只要遍历到的元素比要插入的元素大，则向右挪一位
            3.直到遇到比要插入元素小的元素，则将要插入元素插入到这个元素后面
             */
            int tempValue = arr[unSortIndex];
            //当前遍历的位置索引
            int cursor = unSortIndex - 1;
            //只要游标还没有到头并且游标的数据大于需要插入的数据，则继续向左比对并寻找需要插入的位置
            while (cursor >= 0 && arr[cursor] > tempValue) {
                //将游标指向的元素向后移动，并继续向左移动游标
                arr[cursor+1] = arr[cursor];
                cursor--;
            }
            //说明已经找到需要插入的位置，就是游标的后面
            arr[cursor+1]=tempValue;
            //继续将后面的元素插入到有序集合中
            unSortIndex++;
        }
    }
}
