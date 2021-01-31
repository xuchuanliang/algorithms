package record.hanshunping.search07;

/**
 * 线性查找：特点是对于数组的有序性没有要求，缺点是效率是O(n)
 */
public class LinerSearch extends Search {
    private static int count = 0;
    @Override
    public int search(int[] arr, int searchValue) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            count++;
            if (searchValue == arr[i]) {
                index = -1;
                break;
            }
        }
        System.out.println("线性查找算法的查找次数是："+count);
        return index;
    }
}
