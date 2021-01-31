package record.hanshunping.search07;

/**
 * 查找接口：从数组中查找某一个元素
 */
public abstract class Search {
    /**
     * 查找
     * @param arr
     * @param searchValue
     * @return
     */
    public abstract int search(int[] arr,int searchValue);

    public static void main(String[] args) {
//        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14};
//        Search search = null;
//        search = new LinerSearch();
//        System.out.print(search.search(arr,10));
//        search = new BinarySearch();
//        System.out.print(search.search(arr,10));

        int[] arr = new int[100];
        for(int i=0;i<100;i++){
            arr[i] = i;
        }

        Search search = new LinerSearch();
        //79次
        search.search(arr,78);
        //6次
        search = new BinarySearch();
        search.search(arr,78);
        //1次
        search = new InsertValueSearch();
        search.search(arr,78);
    }
}
