package com.fantasybaby.xiaohui.chapter4;

import java.util.Arrays;

/**
 * 冒泡排序
 * @author: liuxi
 * @time: 2019/12/4 11:12
 */
public class BubbleSort {
    private void sort(int[] array){
        int tmp ;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i -1; j++) {
                if(array[j] > array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }

    /**
     * 优化一下防止 顺序已经排好还循环比对
     * @param array
     */
    private void sortImprove(int[] array){
        int tmp ;
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            for (int j = 0; j < array.length - i -1; j++) {
                if(array[j] > array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    isSorted=false;
                }
            }
            if(isSorted){
                break;
            }
        }
    }

    /**
     * 优化一下算一下上一次交换的有序区边界
     * @param array
     */
    private void sortImprove2(int[] array){
        int tmp ;
        int border = array.length - 1;
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            int lastChangeIndex = 0;
            for (int j = 0; j < border; j++) {
                if(array[j] > array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    isSorted=false;
                    lastChangeIndex = j;
                }
            }
            border = lastChangeIndex;
            if(isSorted){
                break;
            }
        }
    }
    public void cockTailSort(int array[]){
        int tmp ;
        for (int i = 0; i < array.length - 1; i++) {
            boolean isSorted = true;
            /**
             * 技术排序
             */
            System.out.println("正向排序");
            for (int j = 0; j < array.length - i -1; j++) {
                if(array[j] > array[j+1]){
                    tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                    isSorted=false;
                }
            }
            System.out.println(Arrays.toString(array));
            if(isSorted){
                break;
            }
            System.out.println("逆向排序");
            for (int j = array.length - i -1; j >i ; j--) {
                if(array[j] < array[j-1]){
                    tmp = array[j];
                    array[j] = array[j-1];
                    array[j-1] = tmp;
                    isSorted=false;
                }
            }
            System.out.println(Arrays.toString(array));
            if(isSorted){
                break;
            }
        }
    }
    public static void main(String[] args) {
        int[] array = new int[]{2,3,4,1,4,5,7};
        new BubbleSort().cockTailSort(array);
        System.out.println("==>"+Arrays.toString(array));
    }
}
