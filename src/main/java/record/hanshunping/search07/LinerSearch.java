package record.hanshunping.search07;

/**
 * 线性查找：特点是对于数组的有序性没有要求，缺点是效率是O(n)
 */
public class LinerSearch extends Search {
    @Override
    public int search(int[] arr, int searchValue) {
        for (int i = 0; i < arr.length; i++) {
            if (searchValue == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
