package com.fantasybaby.xiaohui.chapter4;

import java.util.Arrays;

/**
 * 计数排序
 * 2019/12/8
 *
 * @author fantasybaby
 **/
public class CountSort {
    public int[] sort(int[] array){
        int max=0;
        for (int i = 0; i < array.length; i++) {
            if(array[i] > max){
                max = array[i];
            }
        }
        int[] countArray = new int[max+1];
        for (int i = 0; i < array.length ; i++) {
            countArray[array[i]]++;
        }
        int[] sortedArray = new int[array.length];
        int index = 0;
        for (int i = 0; i < countArray.length; i++) {
            for (int j = 0; j < countArray[i]; j++) {
                sortedArray[index++]=i;
            }
        }

        return sortedArray;
    }

    /**
     * 优化排序算法
     * 1.最小边界
     * 2.通过累加前面的元素,获取最终计算结果的位置可以找到原来的元素
     * @param array
     */
    public int[] sortImporve(int[] array) {
        int max = array[0];
        int min = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
            if (min > array[i]){
                min = array[i];
            }
        }
        int df = max - min;
        int[] countArray = new int[df + 1];
        for (int i = 0; i < array.length ; i++) {
            /**
             * 减去最小值 索引
             */
            countArray[array[i] - min]++;
        }
        /**
         * 累加当前位置和前面位置的元素和
         * 这样就能保证顺序
         */
        for (int i = 1; i < countArray.length; i++) {
            countArray[i] += countArray[i-1];
        }
        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0; i--) {
            sortedArray[countArray[array[i]-min] -1] = array[i];
            countArray[array[i]-min]--;
        }
        return sortedArray;
    }
    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};
        int[] sort = new CountSort().sortImporve(array);
        System.out.println(Arrays.toString(sort));
    }
}
