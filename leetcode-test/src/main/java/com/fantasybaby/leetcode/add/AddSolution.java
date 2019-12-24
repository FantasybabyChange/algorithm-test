package com.fantasybaby.leetcode.add;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 *
 * 示例:
 *
 * 给定 nums = [2, 7, 11, 15], target = 9
 *
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1]
 *
 * @author: liuxi
 * @time: 2019/12/24 12:02
 */
public class AddSolution {
    /**
     * 使用指针不停的轮询
     * 时间复杂度高
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int[] results = new int[2];
        int index = 0;
        int nextIndex = index+1;
        while(index +1 < nums.length){
            if(nums[index] + nums[nextIndex] == target){
                results[0] = nums[index];
                results[1] = nums[nextIndex];
                break;
            }
            nextIndex++;
            if(nextIndex == nums.length){
                index++;
                nextIndex = index+1;
            }
        }

        return results;
    }
    public int[] twoSum1(int[] nums, int target) {
        Map<Integer,Integer> valueWithIndex = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int df = target - nums[i];
            if(valueWithIndex.containsKey(df)){
               return new int[]{valueWithIndex.get(df),i};
            }
            valueWithIndex.put(nums[i],i);
        }
        throw new IllegalArgumentException("no result");
    }

    public static void main(String[] args) {
        int[]nums = new int[]{15,2 , 11, 7};
        int[] ints = new AddSolution().twoSum(nums, 9);
        System.out.println(Arrays.toString(ints));
        int[] ints1 = new AddSolution().twoSum1(nums, 9);
        System.out.println(Arrays.toString(ints1));
    }

}
