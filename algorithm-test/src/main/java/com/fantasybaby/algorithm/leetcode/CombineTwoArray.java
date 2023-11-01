package com.fantasybaby.algorithm.leetcode;

import java.util.Arrays;

/**
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <p>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 * <a href="https://leetcode.cn/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150"> 两个数组合并 </a>
 *
 * @author Reid Liu
 */
public class CombineTwoArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n != 0) {
            if (m == 0 || nums2[0] >= nums1[m - 1]) {
                for (int i = 0; i < n; i++) {
                    nums1[i + m] = nums2[i];
                }
            } else if (nums2[n - 1] <= nums1[0]) {
                for (int i = 0; i < m; i++) {
                    nums1[n + i] = nums1[i];
                }
                for (int i = 0; i < n; i++) {
                    nums1[i] = nums2[i];
                }
            } else {
                int firstIndex = m - 1;
                int secondIndex = n - 1;
                int changeIndex = m + n - 1;
                while (secondIndex >= 0) {
                    if (nums1[firstIndex] >= nums2[secondIndex]) {
                        nums1[changeIndex] = nums1[firstIndex];
                        firstIndex--;
                        if (firstIndex < 0) {
                            changeIndex--;
                            break;
                        }
                    } else {
                        nums1[changeIndex] = nums2[secondIndex];
                        secondIndex--;
                    }
                    changeIndex--;
                }
                if (secondIndex >= 0) {
                    for (int i = secondIndex; i >= 0; i--) {
                        nums1[changeIndex] = nums2[i];
                        changeIndex--;
                    }
                }
            }

        }


        System.out.println(Arrays.toString(nums1));
    }

    public static void main(String[] args) {
//        int[] a = {1, 2, 3, 0, 0, 0};
//        int[] b = {3, 4, 5};
//        int[] a = {3, 4, 5, 0, 0, 0};
//        int[] b = {2, 2, 4};
        int[] a = {1, 2};
        int[] b = {0, 0, 0};
        int m = 2;
        int n = 0;
        new CombineTwoArray().merge(a, m, b, n);
    }
}
