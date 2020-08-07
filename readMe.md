# 算法
## 第二章 排序
### 冒泡排序
- 冒泡排序的基本思想：元素两两进行比较，将大的元素向后移动，直到移动到最后，然后进行下一趟，像冒泡一样，每次都将最大的元素冒到上面
- 实现逻辑：
- 代码示例
```java
public class BubblingSort {
    public static void sort(int[] arr){
        int length = arr.length;
        while (length>0){
            for(int i=1;i<length;i++){
                if(arr[i-1]>arr[i]){
                    //如果左边的比右边的大，则交换左右的位置，即将大的向上冒
                    U.swap(arr,i-1,i);
                }
            }
            length--;
        }
    }
}
```
### 选择排序
- 选择排序基本思想：首先，找到数组中最小的那个元素，其次，将它和数组的第一个元素交换位置（如果第一个元素就是最小元素那么它就和自己交换）。
然后，在剩下的元素中找到最小的元素，将它与数组的第二个元素交换位置。如此往复，直到将整个数组排序。这种方法叫做选择排序，因为它在不断地选
择剩余元素之中的最小者。【感觉刚好和冒泡排序相反】
- 选择排序特点：运行时间和输入无关。为了找出最小的元素而扫描一遍数组并不能为下一遍扫描提供什么信息这种性质在某些情况下是缺点，因为使用选择
排序的人可能会惊讶地发现，一个已经有序的数组或是主键全部相等的数组和一个元素随机排列的数组所用的排序时间竟然一样长！我们将会看到，其他算法
会更善于利用输入的初始状态。数据移动是最少的。每次交换都会改变两个数组元素的值，因此选择排序用了 N 次交换——交换次数和数组的大小是线性关系。
我们将研究的其他任何算法都不具备这个特征（大部分的增长数量级都是线性对数或是平方级别）。
- 代码示例
```java
public class SelectorSort {

    public static void sort(int[] arr){
        if(null==arr)
            throw new IllegalArgumentException("参数错误");
        for(int i=0; i<arr.length; i++){
            //找到i至最后一个元素中最小的元素的所在索引
            int min = i;
            for(int j=i+1;j<arr.length-1;j++){
                if(arr[j] < arr[min]){
                    min = j;
                }
            }
            //将最小的元素交换值i的位置
            U.swap(arr,i,min);
        }
    }
}
```
### 插入排序
- 插入排序主要思想：通常人们整理桥牌的方法是一张一张的来，将每一张牌插入到其他已经有序的牌中的适当位置。在计算机的实现中，为了给
要插入的元素腾出空间，我们需要将其余所有元素在插入之前都向右移动一位。这种算法叫做插入排序。<br/>
与选择排序一样，当前索引左边的所有元素都是有序的，但它们的最终位置还不确定，为了给更小的元素腾出空间，它们可能会被移动。但是当索
引到达数组的右端时，数组排序就完成了。和选择排序不同的是，插入排序所需的时间取决于输入中元素的初始顺序。例如，对一个很大且其中的
元素已经有序（或接近有序）的数组进行排序将会比对随机顺序的数组或是逆序数组进行排序要快得多。<br/>
总的来说，插入排序对于部分有序的数组十分高效，也很适合小规模数组。这很重要，因为这些类型的数组在实际应用中经常出现，而且它们也是
高级排序算法的中间过程。我们会在学习高级排序算法时再次接触到插入排序
- 代码示例
```java
public class InsertSort{

    /**
     * 思路：从第二个开始逐步向后遍历，并且插入到前面的指定位置，将该位置的元素向后移动，
     * @param arr
     */
    public static void sort(int[] arr){
        for(int i=1;i<arr.length;i++){
            //将当前元素插入到前面的有序集合中
            //1.定位到位置
            int index = find(arr,0,i-1,arr[i]);
            //2.将该位置的元素后移一位，且将该元素插入到该位置
            insertIndex(arr,index,i-1,arr[i]);
        }
    }

    private static int find(int[] arr,int left,int right,int value){
        //如果比最右边的还大，则位置不变
        if(value >= arr[right]){
            return right+1;
        }
        //如果比最左边的还小，则为最左边位置
        if(value <= arr[left]){
            return left;
        }
        //在中间的情况
        while (right > left){
            if(arr[right]==value || arr[right-1]==value){
                return right;
            }else if(arr[right] > value && arr[right-1] < value){
                return right;
            }
            right--;
        }
        throw new IllegalArgumentException("未找到元素位置");
    }

    private static void insertIndex(int[] arr,int index,int right,int value){
        //从后向前移动
        while (right >= index){
            U.swap(arr,right,right+1);
            right--;
        }
        arr[index] = value;
    }

    public static void sort_simulate(int[] arr){
        for(int i=1;i<arr.length;i++){
            int j = i;
            while (j>0 && arr[j] < arr[j-1]){
                U.swap(arr,j,j-1);
                j--;
            }
        }
    }

    /**
     * 相对于我自己的思路，书上的思路更加优雅，代码量也比较少，
     * 思路：对于 1 到 N-1 之间的每一个 i，将 a[i] 与 a[0] 到 a[i-1] 中比它小的所有元素依次有序地交换。
     * 在索引 i 由左向右变化的过程中，它左侧的元素总是有序的，所以当 i 到达数组的右端时排序就完成了。
     * @param arr
     */
    public static void sort_book(int[] arr){
        for(int i=1;i<arr.length;i++){
            for(int j=i;j>0 && arr[j]<arr[j-1];j--){
                U.swap(arr,j,j-1);
            }
        }
    }

}
```

### 希尔排序
- 希尔排序主要思想：基础是插入排序。插入排序对大规模乱序数组排序很慢，因为它只会交换相邻的元素，因此元素只能一点一点地从数组的一
端移动到另一端。尔排序的思想是使数组中任意间隔为 h 的元素都是有序的。这样的数组被称为 h 有序数组。换句话说，一个 h 有序数组就是 
h 个互相独立的有序数组编织在一起组成的一个数组。在进行排序时，如果 h 很大，我们就能将元素移动到很远的地方，为实现更小的 h 有序创
造方便。用这种方式，对于任意以 1 结尾的 h 序列，我们都能够将数组排序。这就是希尔排序。
- 代码示例
```java
public class ShellSort {
    /**
     * 本人思路：首先设计间隔为h，例如h=3，那么以3为间隔将arr分成多个数组，如索引号分别是[0,3,6,9][1,4,7,10][2,5,8]
     * 将这三个索引的数组排序完成后，逐步减少h的值，此时是2，则需要使用插入排序的数组索引号是[0,2,4,6,8,10][1,3,5,7,9]
     * 然后将间隔为2的数组拍完序后，将h缩减为1，再使用插入排序的数组索引号是[0,1,2,3,4,5,6,7,8,9,10]，
     * 主要思想是插入排序逐步的移动元素效率太低，通过间隔的方式，扩大元素移动的范围，逐步的将无序数组转变成大致有序最终由于，
     * 主要是利用插入排序的特点，插入排序的特点是数组如果有序，则插入排序的元素交换次数就会比较少，效率就会很高
     * @param arr
     */
    public static void sort(int[] arr){
        int h = arr.length/3;
        while (h>0){
            for(int i=0;i<arr.length;i++){
                int j = i;
                while (j>=0 && (j+h)<arr.length && arr[j+h]<arr[j]){
                    U.swap(arr,j,j+h);
                    j-=h;
                }
            }
            h--;
        }
    }
}
```

### 归并排序
#### 《算法》书中的归并排序
- 归并排序《算法》书中的主要描述：要理解归并排序就要仔细研究该方法调用的动态情况，要将 a[0..15] 排序， sort() 方法会调用自己将a[0..7] 排序，
再在其中调用自己将 a[0..3] 和a[0..1] 排序。在将 a[0] 和 a[1] 分别排序之后，终于才会开始将 a[0] 和 a[1] 归并（简单起见，我们在
轨迹中把对单个元素的数组进行排序的调用省略了）。第二次归并是 a[2] 和 a[3]，然后是 a[0..1] 和 a[2..3]，以此类推。从这段轨迹可以看到， 
sort() 方法的作用其实在于安排多次merge() 方法调用的正确顺序。后面几节还会用到这个发现。
- 书中代码示例
```java
public class Merge {

    /**
     * 实现归并的一种直截了当的办法是将两个不同的有序数组归并到第三个数组中，
     * 但是，当用归并将一个大数组排序时，我们需要进行很多次归并，因此在每次归并时都创建一
     * 个新数组来存储排序结果会带来问题。我们更希望有一种能够在原地归并的方法，这样就可以先将
     * 前半部分排序，再将后半部分排序，然后在数组中移动元素而不需要使用额外的空间。
     * merge(a, lo,mid, hi)，它会将子数组 a[lo..mid] 和 a[mid+1..hi] 归并成一个有序的数组并将结果存放在 a[lo..hi] 中。
     *
     */
    private static void merge(int[] a,int lo,int mid,int hi){
        int i = lo, j = mid+1;
        for (int k = lo; k <= hi; k++) // 将a[lo..hi]复制到aux[lo..hi]
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++) // 归并回到a[lo..hi]
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > hi ) {
                a[k] = aux[i++];
            } else if (aux[j] < aux[i]) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
    }


    private static int[] aux; // 归并所需的辅助数组

    public static void sort(int[] a)
    {
        aux = new int[a.length]; // 一次性分配空间
        sort(a, 0, a.length - 1);
    }
    private static void sort(int[] a, int lo, int hi)
    { // 将数组a[lo..hi]排序
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, lo, mid); // 将左半边排序
        sort(a, mid+1, hi); // 将右半边排序
        merge(a, lo, mid, hi); // 归并结果（代码见“原地归并的抽象方法”）
    }
}
```
#### 学习过程中的归并算法V1版本
- 主要思想：采用分治递归的思想，将如需整个数组排序，则先将数组分成两半，两半的数组分别排好顺序，然后将两半的数组合并成一个有序数
组递归将数组分成两半，直到数组左边和右边都只有一个数，则直接将左右两个数合并成为一个有序数组即可
- 本人代码示例V1版本
```java
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
```
#### 学习过程中的归并算法V2版本
- 主要思想：V1版本的缺点是每次合并都需要创建一个临时数组，当需要排序的数量非常大时，会产生创建和销毁大量的临时数组，导致空间的浪费，
因此V2版本创建一个与原数组长度一致的临时数组，例如left-mid mid+1-right这两个数组，因为临时数组长度与原数组一致，所以将其合并的元素
放入到临时数组的left-right位置中，然后再复制到原数组的相同位置中，这样就做到只有会创建一个临时数组，节省了空间以及GC的压力。
- 本人代码示例V2版本
```java
public class MyMergeV2 {
    private static int[] tempArr;

    public static void sort(int[] arr) {
        tempArr = new int[arr.length];
        sort(arr, 0, arr.length - 1);
    }

    /**
     * 将给定left-right长度的数组分成左边有右边两个数组，并且递归将其排好序后，合并成为一个有序数组
     * @param arr
     * @param left
     * @param right
     */
    private static void sort(int[] arr, int left, int right) {
        //如果left==right，则认为该数组的长度只有1，则已经为有序数组
        if(left == right){
            return;
        }
        int mid = (left+right)/2;
        sort(arr,left,mid);
        sort(arr,mid+1,right);
        if(arr[mid]>arr[mid+1]){
            //****优化：只有左侧数组最后一个元素大于右侧第一个元素，才认为需要进行合并操作，否则left-right数组本身就是有序，无需进行合并操作
            merge(arr,left,mid,right);
        }
    }

    /**
     * 将左侧有序的数组以及右侧有序的数组合并成为一个大的有序数组
     * @param arr
     * @param left
     * @param mid
     * @param right
     */
    private static void merge(int[] arr,int left,int mid,int right){
        //只要左右两个数组都还没有被遍历完，则始终对比左侧和右侧数组最左侧的元素，将最小的数据推到队列中
        int temp_left = left;
        int temp_right = mid+1;
        int temp_index = left;
        while (temp_left<=mid && temp_right<=right){
            //说明左右两个数组元素都不空
            if(arr[temp_left]<arr[temp_right]){
                //如果左边第一个元素小，则左边的放入临时数组中
                tempArr[temp_index++] = arr[temp_left++];
            }else{
                //如果右边元素小，则右边第一个元素放入临时数组中
                tempArr[temp_index++] = arr[temp_right++];
            }
        }
        //到此为止，则要么左侧元素已经遍历完成，要么右侧元素已经遍历完成，则只剩下一个非空的数组，将未遍历到的剩余数据依次放入到临时数组中
        while (temp_left<=mid){
            //则左侧数组非空
            tempArr[temp_index++] = arr[temp_left++];
        }
        while (temp_right<=right){
            //则右侧数组非空
            tempArr[temp_index++] = arr[temp_right++];
        }
        //将合并后有序的临时数组中的元素依次重新放入到原数组中
        for(int i=left;i<=right;i++){
            arr[i] = tempArr[i];
        }
    }
}
```
### 快速排序
#### 学习过程中自行实现的快速排序
- 基本思想：快速排序流行的原因是它实现简单、适用于各种不同的输入数据且在一般应用中比其他排序算法都要快得多。快速排序引人注目的特点
包括它是原地排序（只需要一个很小的辅助栈），且将长度为 N 的数组排序所需的时间和 NlgN 成正比。我们已经学习过的排序算法都无法将这
两个优点结合起来。另外，快速排序的内循环比大多数排序算法都要短小，这意味着它无论是在理论上还是在实际中都要更快。<br/>
快速排序是一种分治的排序算法。它将一个数组分成两个子数组，将两部分独立地排序。快速排序和归并排序是互补的：归并排序将数组分成两个子数组分别排序，
并将有序的子数组归并以将整个数组排序；而快速排序将数组排序的方式则是当两个子数组都有序时整个数组也就自然有序了。在第一种情况中，
递归调用发生在处理整个数组之前；在第二种情况中，递归调用发生在处理整个数组之后。在归并排序中，一个数组被等分为两半；在快速排序中，
切分（ partition）的位置取决于数组的内容。<br/>
- 个人理解：了解快速排序就需要和归并排序做对比才好理解。归并排序的基本思想是对原数组进行切分，只要左边的数组是有序的，右边的数组
也是有序的，那么将左右两边的数组进行合并就能合并成为一个有序的数组；合并过程很简单，就是将左右两个有序数组都从第一个元素进行遍历，
然后对比，那个小则那个元素在前，直到将两个有序数组合并成为一个有序数组。递归的临界条件就是当需要排序的数组递归到还剩一个元素的时候，
那么就没有必要再切分为左右两个数组进行递归排序，默认1个元素就是有序的,归并排序的缺点就是需要一个临时数组用来存储临时合并的有序数组，
对空间的占用与原数组有关系，原数组越大，则空间占用会越大。<br/>
快速排序的基本思想是借鉴了一部分归并排序的思想，但是解决了临时数组的问题，让原数组进行原地排序主要思想是先确定一个需要切分的分界点，
然后找到分界点的位置，将分界点左侧的比分界点大的元素放在右边，将右边比分界点小的元素放在左边，然后对左右两边的数据也确定一个切分的分界点，
按照分界点分割成左边比分界点小，右边比分界点大的两个数组，不断递归，将两边的数组都变成有序数组，那么整个数组也就有序，
如果左右两边的数组有序，那么整个数组也就有序了，相对于归并排序，少了最后的归并操作。

- 本人代码示例
```java
public class MyFastSort {
    public static void sort(int[] arr){
        sort(arr,0,arr.length-1);
    }

    private static void sort(int[] arr,int left,int right){
        //如果left==right，则说明需要排序的只有一个元素，则默认有序，也是递归的边界条件
        if(left >= right){
            return;
        }
        int index = sharding(arr,left,right);
        //递归将分界点左侧和右侧的数据进行排序
        sort(arr,left,index);
        sort(arr,index+1,right);
    }

    /**
     * 对arr[left-right]的元素进行切分，主要思想是拿第一个元素作为边界，然后从第二个元素向后推，同时最后一个元素向前推，
     * 将左边比第一个元素大的与右边比第一个元素小的进行交换，直到相遇，那么相遇的位置就是第一个元素的位置，并且将第一个元素交换到该位置
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static int sharding(int[] arr, int left, int right) {
        int index = left;
        //确定基准元素
        int val = arr[left];
        //确定最左边的指针和最右边的指针，即left+1和right，只要两个指针没有相遇，那么就从从两头向中间推进，
        int temp_left = left+1;
        int temp_right = right;
        while (temp_left<temp_right){
            //先从左边开始向右找到第一个比var大的元素，即只要还在范围内并且left元素小于val，则向右推进
            while (arr[temp_left]<=val && temp_left<temp_right){
                temp_left++;
            }
            while (arr[temp_right]>=val && temp_left<temp_right){
                temp_right--;
            }
            //如果此时相遇，则说明左侧元素都小于val，右侧元素都大于val，那么如果val大于相遇的位置，则放在右边，否则放在左边
            if(temp_left==temp_right){
                if(arr[temp_left]<val){
                    //temp_left小于基准值，则交换temp_left与val的位置
                    U.swap(arr,left,temp_left);
                    index = temp_left;
                }else{
                    //否则说明temp_left的元素比val大，交换temp_left-1与val的位置
                    U.swap(arr,left,temp_left-1);
                    index = temp_left-1;
                }
            }else{
                //否则，则说明左边有比val大的元素，右侧有比val小的元素，则交换两者位置
                U.swap(arr,temp_left,temp_right);
            }
        }
        return index;
    }
}
```

#### 《算法》书中的快速排序
- 书中代码示例
```java
public class FastSort {
    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int left, int right) {
        if (right <= left) {
            return;
        }
        int j = partition(arr, left, right); // 切分（请见“快速排序的切分”）
        sort(arr, left, j - 1); // 将左半部分a[lo .. j-1]排序
        sort(arr, j + 1, right); // 将右半部分a[j+1 .. hi]排序
    }

    private static int partition(int[] arr, int left, int right) { // 将数组切分为a[lo..i-1], a[i], a[i+1..hi]
        int i = left, j = right + 1; // 左右扫描指针
        int v = arr[left]; // 切分元素
        while (true) { // 扫描左右，检查扫描是否结束并交换元素
            while (arr[++i] < v) {
                if (i == right) {
                    break;
                }
            }
            while (v < arr[--j]) {
                if (j == left) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            U.swap(arr, i, j);
        }
        U.swap(arr, left, j);// 将v = a[j]放入正确的位置
        return j; // a[lo..j-1] <= a[j] <= a[j+1..hi] 达成
    }
}
```






# 算法图解
# 第一章 算法简介
## 1.2 二分查找
> 二分查找是一种算法，其输入是一个有序的元素列表（必须有序的原因稍后解释）。如果要查找的元素包含在列表中，二分查找返回其位置；否则返回null。  
>对于包含n个元素的列表，用二分查找最多需要log2n步，而简单查找最多需要n步。  
>仅当列表是有序的时候，二分查找才管用  

## 快速排序
- 快速排序基本思想：选中一个基准点，将数组分成基准点左边是比基准点小，右边是比基准点大的两个数组，采用递归的思想，直到数组长度为1或空的数组表示不用排序

## 散列表
- 散列表必须满足以下要求：
1.它必须是一致的。例如，假设你输入apple时得到的是4，那么每次输入apple时，得到的都必须为4。如果不是这样，散列表将毫无用处。
2.它应将不同的输入映射到不同的数字。 例如， 如果一个散列函数不管输入是什么都返回1，它就不是好的散列函数。最理想的情况是，将不同的输入映射到不同的数字。
- 散列表的特点：
1.散列函数总是将同样的输入映射到相同的索引。
2.散列函数将不同的输入映射到不同的索引。 
3.散列函数知道数组有多大，只返回有效的索引。
- 散列表是使用散列函数和数组组成的一种数据结构
- 散列表的应用场景：1.创建映射；2.查找
- 散列表冲突：当key被hash算法映射成为一个相同的key时，就在该位置存储一个链表
>散列表的经验教训有两个：
>>1.散列函数很重要。前面的散列函数将所有的键都映射到一个位置，而最理想的情况是， 散列函数将键均匀地映射到散列表的不同位置。
>>2.如果散列表存储的链表很长，散列表的速度将急剧下降。然而， 如果使用的散列函数很好，这些链表就不会很长！
>散列表要避免冲突，主要注意两个方面：1.较低的填装因子；2.良好的散列函数
- 填装因子=散列表包含的元素数/位置总数，例如，数组长度是10，元素一共有5个，则填装因子是0.5，如果元素一共是20个，则填装因子是2
>因为散列表使用数组来存储数据，填装因子是计算数组中被占用的位置数
- 填装因子越低，发生冲突的可能性越小，散列表的性能越高。一般情况下，一个填装因子大于0.7，就调整散列表的长度。
1.你可以结合散列函数和数组来创建散列表。
2.冲突很糟糕，你应使用可以最大限度减少冲突的散列函数。
3.散列表的查找、插入和删除速度都非常快。
4.散列表适合用于模拟映射关系。
5.一旦填装因子超过0.7，就该调整散列表的长度。
6.散列表可用于缓存数据（例如，在Web服务器上）。
7.散列表非常适合用于防止重复。

# 广度优先搜索
- 解决最短路径问题的算法被称为广度优先搜索（BFS）。 广度优先搜索让你能够找出两样东西之间的最短距离。
## 图是什么
- 图模拟一组连接。图由节点（ node） 和边（ edge） 组成。图由节点和边组成。一个节点可能与众多节点直接相连，这些节点被称为邻居。图用于模拟不同的东西是如何相连的。
- 广度优先搜索是一种用于图的查找算法，可帮助回答两类问题。1.第一类问题：从节点A出发，有前往节点B的路径吗？2.第二类问题：从节点A出发，前往节点B的哪条路径最短？
- 在广度优先搜索的执行过程中，搜索范围从起点开始逐渐向外延伸，即先检查一度关系，再检查二度关系。
- 队列只支持两种操作： 入队和出队
- 队列是一种先进先出（ First In First Out， FIFO）的数据结构，而栈是一种后进先出（ Last In First Out， LIFO）的数据结构。
- 有向图（ directed graph） ，其中的关系是单向的。
- 无向图（ undirected graph） 没有箭头，直接相连的节点互为邻居。
- 广度优先算法实际上是将图划分成度，即距离初始节点如果直接关联则是1度，如果是第二个节点就是2度，然后通过队列先进先出的方式，
每搜索一个节点时就将该节点的关联节点推到队列的后面，这样就会先搜索1度的所有节点，其次搜索2度的节点知道最终将所有节点搜索完，也是递归的思想。
>图总结：
>>1.广度优先搜索指出是否有从A到B的路径。
>>2.如果有，广度优先搜索将找出最短路径。
>>3.面临类似于寻找最短路径的问题时，可尝试使用图来建立模型，再使用广度优先搜索来解决问题。
>>4.有向图中的边为箭头，箭头的方向指定了关系的方向，例如， rama→adit表示rama欠adit钱。
>>5.无向图中的边不带箭头，其中的关系是双向的，例如， ross - rachel表示“ross与rachel约会，而rachel也与ross约会”。
>>6.队列是先进先出（ FIFO）的。
>>7.栈是后进先出（ LIFO）的。
>>8.你需要按加入顺序检查搜索列表中的人，否则找到的就不是最短路径，因此搜索列表必须是队列。
>>9.对于检查过的人，务必不要再去检查，否则可能导致无限循环。

# 狄克斯特拉算法
>狄克斯特拉算法包含4个步骤：
>1.找出“最便宜”的节点，即可在最短时间内到达的节点。
>2.更新该节点的邻居的开销，其含义将稍后介绍。
>3.重复这个过程，直到对图中的每个节点都这样做了。
>4.计算最终路径。
- 狄克斯特拉算法用于每条边都有关联数字的图，这些数字称为权重（ weight）。带权重的图称为加权图（ weighted graph），不带权重的图称为非加权图（ unweighted graph）。
- 要计算非加权图中的最短路径，可使用广度优先搜索。要计算加权图中的最短路径，可使用狄克斯特拉算法。

# 贪婪算法
- 每一步都采取最优解，即每一步都选举局部最优解，最终得到的就是全局最优解。


