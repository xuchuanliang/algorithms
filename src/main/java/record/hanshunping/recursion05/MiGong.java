package record.hanshunping.recursion05;

/**
 * 递归解决迷宫问题
 */
public class MiGong {
    public static void main(String[] args) {
        //初始化一个8X7的二维数组作为迷宫
        int[][] mg = new int[8][7];
        //默认迷宫的格式都是0，墙用1表示
        //将迷宫的四周设置称为墙
        for (int i = 0; i < 7; i++) {
            mg[0][i] = 1;
            mg[7][i] = 1;
        }

        for (int i = 0; i < 8; i++) {
            mg[i][0] = 1;
            mg[i][6] = 1;
        }
        //设置障碍
        mg[3][1] = 1;
        mg[3][2] = 1;
        mg[3][3] = 1;
        printArr(mg);
        getWay(mg,1,1);
        System.out.println("=====================");
        printArr(mg);
    }

    /**
     * 打印迷宫
     *
     * @param arr
     */
    public static void printArr(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + "  ");
            }
            System.out.println();
        }
    }

    /**
     * 从迷宫中寻找路径，思路如下：
     * 假定路通则标记为2，路不通则标记为3
     * 假定从1,1开始寻找，目的是寻找到6,5位置的路径
     * 主要思想递归不断从上、下、左、右四个方向探测，直到探测到6,5节点或者找不到未走过的路为止
     *
     * @param curx 当前节点的横坐标
     * @param cury 当前节点的纵坐标
     * @param mg   迷宫
     * @return
     */
    public static boolean getWay(int[][] mg, int curx, int cury) {
        if(curx==6 && cury==5){
            //如果当前节点是6,5，那么说明路已经通了
            return true;
        }
        //如果当前节点已经探测过或当前节点是墙，则表示不通
        if (mg[curx][cury] != 0) {
            return false;
        }
        //当前节点未探测过，则首先默认将当前节点通
        mg[curx][cury] = 2;
        //按照上、右、下、左的顺序依次递归探测路径
        if(getWay(mg,curx,cury+1) || getWay(mg,curx+1,cury) || getWay(mg,curx,cury-1) || getWay(mg,curx-1,cury)){
            //如果递归向上或向右或向下或向左是通的，则表示通了
            return true;
        }else{
            //如果从当前节点向四个方向探测都不通（即要么遇到了墙、要么遇到了已经走过的节点）
            //将当前节点标记为不通
            mg[curx][cury] = 3;
            return false;
        }
    }

}
