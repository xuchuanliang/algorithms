package record.hanshunping.sort6;

/**
 * 希尔排序：主要是为了解决插入排序的缺点，示例如下：
 * 数组 arr = {2,3,4,5,6,1} 这时需要插入的数 1(最小), 这样的过程是：
 * {2,3,4,5,6,6}
 * {2,3,4,5,5,6}
 * {2,3,4,4,5,6}
 * {2,3,3,4,5,6}
 * {2,2,3,4,5,6}
 * {1,2,3,4,5,6}
 * 结论: 当需要插入的数是较小的数时， 后移的次数明显增多， 对效率有影响
 * 希尔排序也是一种插入排序， 它是简单插入排序经过改进之后的一个更高效的版本， 也称为缩小增量排序。
 * 希尔排序是把记录按下标的一定增量分组， 对每组使用直接插入排序算法排序； 随着增量逐渐减少， 每组包含
 * 的关键词越来越多， 当增量减至 1 时， 整个文件恰被分成一组， 算法便终止；
 * 说人话：按照距离将待排序的数组拆分成相互不关联的小数组，小数组使用插入排序，那么经过几次之后数组就大致有序，直到最后变成有序，整体移动的次数比较小，从而增加效率
 */
public class ShellSort extends Sort {
    @Override
    public void sort(int[] arr) {
        //假设最初从每两个元素为一组，则一共分为arr.length/2组，即步长是arr.length/2
        int gap = arr.length / 2;
        //逐步的缩小步长，每一个步长情况下的每一个间隔数组进行插入排序，当步长缩小至1的时候，则是进行了一次简单插入排序，然后达到了整个数组有序
        while (gap > 0) {
            //例如步长是5，则实际上是将arr分割成5个数组，这个5个数组的第一个元素分别是0，1，2，3，4；后面的元素是分别增加步长
            for (int i = 0; i < gap; i++) {
                //当前需要向前比较的元素索引位置,即该间隔数组的该元素前面的所有数据都是有序的
                int unSortIndex = i + gap;
                while (unSortIndex < arr.length) {
                    //与前面的有序数组进行比较，并插入到合适的位置，保持该间隔数组有序
                    //记录当前未排序的元素值
                    int unSortIndexVal = arr[unSortIndex];
                    //游标：由于标记当前在左边有序间隔数组中需要比较的元素的索引
                    int cursor = unSortIndex - gap;
                    //只要游标还未到最左边并且当前游标指向的值大于待插入的值，则向左移动
                    while (cursor >= 0 && arr[cursor] > unSortIndexVal) {
                        //只要当前需要比较的游标还未到最左边 并且游标指向的位置值比待插入的元素大
                        //首先将当前游标指向的值向后移动，腾出位置，因为要插入到他的左边
                        arr[cursor + gap] = arr[cursor];
                        //然后将游标向左移动到下一个元素，并继续向左探测
                        cursor = cursor - gap;
                    }
                    //找到了需要插入的位置，就是cursor+gap的位置
                    arr[cursor + gap] = unSortIndexVal;
                    //向后移动未排序的数据
                    unSortIndex = unSortIndex + gap;
                }
            }
            //当前步长的各个间隔数组有序后，则整个数组距离有序近了一步
            //重点：一定要步长成倍的缩减，否则会导致效率比较差
            gap = gap/2;
        }
    }
}
