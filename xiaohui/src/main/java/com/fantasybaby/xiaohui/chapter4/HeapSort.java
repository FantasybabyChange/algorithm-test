package com.fantasybaby.xiaohui.chapter4;

import com.fantasybaby.xiaohui.chapter3.BinaryHeap;

import java.util.Arrays;

/**
 * 通过二叉堆排序
 * 将元素放入最后一个位置
 * 每个元素下浮结束 数组就是有序的了
 * 2019/12/8
 *
 * @author fantasybaby
 **/
public class HeapSort {
    private BinaryHeap heap = new BinaryHeap();
     public void sort(int[] array){
         heap.buildHead(array);
         for (int i = array.length - 1; i > 0; i--) {
             int tmp = array[i];
             array[i] = array[0];
             array[0] = tmp;
             heap.downAdjust(array,0,i);
         }
     }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};
        new HeapSort().sort(array);
        System.out.println(Arrays.toString(array));
    }
}
