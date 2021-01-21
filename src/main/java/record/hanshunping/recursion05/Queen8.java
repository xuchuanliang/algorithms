package record.hanshunping.recursion05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 八皇后问题， 是一个古老而著名的问题， 是回溯算法的典型案例。 该问题是国际西洋棋棋手马克斯· 贝瑟尔于
 * 1848 年提出： 在 8× 8 格的国际象棋上摆放八个皇后， 使其不能互相攻击， 即： 任意两个皇后都不能处于同一行、
 * 同一列或同一斜线上， 问有多少种摆法(92)。
 *
 * 思路：
 * 1.第一个皇后先放第一行第一列
 * 2.第二个皇后放在第二行第一列、 然后判断是否 OK， 如果不 OK， 继续放在第二列、 第三列、 依次把所有列都
 * 放完， 找到一个合适
 * 3.继续第三个皇后， 还是第一列、 第二列……直到第 8 个皇后也能放在一个不冲突的位置， 算是找到了一个正确
 * 解
 * 4.当得到一个正确解时， 在栈回退到上一个栈时， 就会开始回溯， 即将第一个皇后， 放到第一列的所有正确解，
 * 全部得到
 * 5.然后回头继续第一个皇后放第二列， 后面继续循环执行 1,2,3,4 的步骤
 */
public class Queen8 {

    /**
     * 用于临时存放八皇后的解决方法
     */
    private static int[] middleResult = new int[8];
    /**
     * 用于存放最终所有的解法
     */
    private static List<int[]> result = new ArrayList<>();
    /**
     * 用于记录一共的解决办法
     */
    private static int count = 0;

    public static void main(String[] args) {
        //从第一行开始，将元素放在第一列，然后接着逐步将后
        check(0);
        System.out.println(count);
        result.forEach(r->{
            for(int i=0;i<r.length;i++){
                System.out.print(r[i]+"  ");
            }
            System.out.println();
        });
    }

    /**
     * 将第n行的皇后从第一列开始摆，每摆一次就与前面的皇后进行冲突检查，如果有冲突则摆在后面一列，如果没有冲突，那么就开始摆下一行
     * @param n 表示开始在第n行寻找这一行皇后不冲突的位置
     */
    public static void check(int n){
        if(n==8){
            //表示已经到了第九行，则结束
            //执行到此处表示当前已经找到了一种解法，解决方法就是result中的坐标
            count++;
            //将当前结果拷贝添加到记录中
            result.add(Arrays.copyOf(middleResult, middleResult.length));
            return;
        }
        for(int i=0;i<8;i++){
            //开始将皇后分别放在当前第n行的第i列，并且向前回溯检查与前面的皇后是否有冲突
            //假设当前的位置是正确的
            middleResult[n] = i;
            //检查当前位置的皇后与前面的皇后充不冲突
            if(conflict(n)){
                //如果发生了冲突，则尝试当前行的下一列位置
                continue;
            }else{
                //如果没有发生冲突，则进行下一行的皇后位置寻找
                check(n+1);
            }
        }
    }

    /**
     * 检查第n个皇后与n之前的皇后是否冲突，如果冲突则返回true，否则false
     * @param n
     * @return
     */
    public static boolean conflict(int n){
        for(int i=0;i<n;i++){
            //不在同一列或者不在同一个斜线则表示不冲突
            //其中不在同一个斜线可以理解成正方形的两个角，只要x轴和y轴的绝对值相同，则表示在同一个斜线上
            if(middleResult[i]== middleResult[n]){
                //表示在同一列
                return true;
            }
            if(Math.abs(n-i) == Math.abs(middleResult[n]- middleResult[i])){
                //x边的距离与y边的距离绝对值相同，表示两点在同一斜线上
                return true;
            }
        }
        //最终与前面所有皇后比较过，表示没有发生冲突
        return false;
    }
}
