package record.hanshunping;

import java.io.*;

/**
 * 稀疏队列
 * 缩小数组记录的数据规模
 * 例如五子棋盘记录
 * 例如我们需要记录二维数组arr[row][col]
 * 稀疏数组的原理是一个n行3列的数组，该数组第一行记录arr的行、列、以及一共有多少有效值
 * 第2行至第n行记录有效数据的行、列、值
 */
public class SparseArray {
    private static final String BASE_PATH = "D:/log";
    private static final String ORIGINAL_NAME = "original.data";
    private static final String SPARSE_NAME = "sparse.data";

    public static void main(String[] args) {
        //获取棋盘的原始数组
        int[][] originalArr = getOriginalArr();
        //打印原始数组
        System.out.println("=================section1==================");
        printArr(originalArr);
        //将原始数组转成稀疏数组
        int[][] sparseArr = transferToSparseArr(originalArr);
        printArr(sparseArr);

        //将原始二维数组和稀疏数组写到磁盘中：originArr.data（635字节）,sparseArr.data（107字节）;
        writeArrToDisk(BASE_PATH,ORIGINAL_NAME,originalArr);
        writeArrToDisk(BASE_PATH,SPARSE_NAME,sparseArr);

        //将原始数组和稀疏数组读取出来
        int[][] readOriginal = readArrFromDisk(BASE_PATH, ORIGINAL_NAME);
        int[][] readSparse = readArrFromDisk(BASE_PATH, SPARSE_NAME);
        System.out.println("=================section2==================");
        printArr(readOriginal);
        printArr(readSparse);
        System.out.println("=================section3==================");
        //将稀疏数组转成原始数组
        int[][] newOriginalArr = transferFromSparseArr(readSparse);
        printArr(newOriginalArr);
    }

    /**
     * 模拟11X11的棋盘，其中[1][2]=1 [2][3]=2
     *
     * @return
     */
    public static int[][] getOriginalArr() {
        int[][] arr = new int[11][11];
        //遍历行
        fillZero(arr);
        arr[1][2] = 1;
        arr[2][3] = 2;
        return arr;
    }

    /**
     * 打印二维数组
     *
     * @param arr
     */
    public static void printArr(int[][] arr) {
        for (int row = 0; row < arr.length; row++) {
            for (int col = 0; col < arr[row].length; col++) {
                System.out.print(arr[row][col]);
                System.out.print(" ");
            }
            System.out.println("");
        }
    }

    /**
     * 将原始的二维数组转成稀疏数组
     * @param originalArr
     * @return
     */
    public static int[][] transferToSparseArr(int[][] originalArr){
        //稀疏数组的行数是原始数组非零数据个数+1，列数量固定是3
        int spaceRowNum = 1;
        for(int row=0; row<originalArr.length; row++){
            for(int col=0; col<originalArr[row].length; col++){
                if(originalArr[row][col]!=0){
                    spaceRowNum++;
                }
            }
        }
        //定义稀疏数组
        int[][] spaceArr = new int[spaceRowNum][3];
        //定义第一行的三个元素分别是原数组的行数、列数、有效数据个数
        spaceArr[0][0] = originalArr.length;
        spaceArr[0][1] = originalArr[0].length;
        spaceArr[0][2] = spaceRowNum-1;
        //将有效数据填充值至稀疏数组中
        int curRow = 1;
        for(int row=0; row<originalArr.length; row++){
            for(int col=0; col<originalArr[row].length; col++){
                if(originalArr[row][col]!=0){
                    spaceArr[curRow][0] = row;
                    spaceArr[curRow][1] = col;
                    spaceArr[curRow][2] = originalArr[row][col];
                    curRow++;
                }
            }
        }
        return spaceArr;
    }

    /**
     * 将二维数组写到磁盘中
     * @param basePath
     * @param fileName
     * @param arr
     */
    public static void writeArrToDisk(String basePath,String fileName,int[][] arr) {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(basePath+"/"+fileName)));
            objectOutputStream.writeObject(arr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(null!=objectOutputStream){
                try {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 将二维数组读到程序中
     * @param basePath
     * @param fileName
     * @return
     */
    public static int[][] readArrFromDisk(String basePath,String fileName){
        ObjectInputStream objectInputStream = null;
        try{
            objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream(basePath+"/"+fileName)));
            Object object = objectInputStream.readObject();
            return (int[][]) object;
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            if(null!=objectInputStream){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 根据稀疏数组转成正常的二维数组
     * @return
     */
    public static int[][] transferFromSparseArr(int[][] sparseArr){
        //获取原始数组的列和宽
        int rowNum = sparseArr[0][0];
        int colNum = sparseArr[0][1];
        int[][] originalArr = new int[rowNum][colNum];
        //填充0
        fillZero(originalArr);
        //从稀疏数组的第一行开始填充原始数组数据
        for(int row=1;row<sparseArr.length;row++){
            originalArr[sparseArr[row][0]][sparseArr[row][1]] = sparseArr[row][2];
        }
        return originalArr;
    }

    /**
     * 将二维数组填充0
     * @param arr
     */
    public static void fillZero(int[][] arr){
        for (int row = 0; row < arr.length; row++) {
            //遍历每一行每一列
            for (int col = 0; col < arr[row].length; col++) {
                arr[row][col] = 0;
            }
        }
    }
}
