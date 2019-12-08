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

    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};
        int[] sort = new CountSort().sort(array);
        System.out.println(Arrays.toString(sort));
    }
}
