package record.niuke;

import record.U;

/**
 * 2020年7月16日 10:21:29
 *
 */
public final class C1 {

    /*
    题目：
     在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     本人思路：因为数组中的数字都是整数，并且所有数字范围在0到n-1，即数字大小都在索引范围内；那么我们可以通过遍历的方式将数组中的每个元素都都放在数组中对应索引的位置，
     如果该位置已经有与索引值相同的元素，则认为重复
     官方思路：要求时间复杂度 O(N)，空间复杂度 O(1)。因此不能使用排序的方法，也不能使用额外的标记数组。
    对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。
    以 (2, 3, 1, 0, 2, 5) 为例，遍历到位置 4 时，该位置上的数为 2，但是第 2 个位置上已经有一个 2 的值了，因此可以知道 2 重复：

     */
    public static int[] duplicate(int[] nums){
        if(null==nums || nums.length==0){
            throw new IllegalArgumentException("参数错误");
        }
        int[] result = new int[nums.length];
        int resultIndex = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i] == i){
                //当前索引元素与当前索引相同，则跳过
                continue;
            }
            if(nums[i] == nums[nums[i]]){
                //当前索引元素与该元nums[i]素值作为索引的值相同，则重复
                result[resultIndex++] = nums[i];
                continue;
            }
            int tempTo = nums[i];
            int tempFrom = i;
            U.swap(nums,tempTo,tempFrom);
            //如果此处换过来的数据大于i并且是把当前i后面的元素交换到这里，则应该继续从i开始判断，而非i-1，否则会漏掉元素，所以此处重置i
            if(nums[i] > i && tempTo > tempFrom){
                i = i-1;
            }
            U.print(nums);
        }
        return result;
    }
}
