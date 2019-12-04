package com.fantasybaby.xiaohui.chapter3;

import java.util.Arrays;

/**
 * 二叉堆相关操作
 * 二叉堆的数据是维护在数组中的
 * 这个demo我用最小堆来实现
 * @author: reid.liu
 * @time: 2019/12/3 22:18
 */
public class BinaryHeap {
    /**
     * 前提是array是一个二叉堆
     * 上浮插入的数据
     * @param array
     */
    public void upAdjust(int[] array,int length){
        int childIndex = length - 1;
        int parentIndex = (childIndex-1)/2;
        int temp = array[childIndex];
        /**
         * 只需要和temp做比较 最后比完再赋值
         */
        while (childIndex > 0 && array[parentIndex] > temp){
            array[childIndex] = array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex-1)/2;
        }
        array[childIndex] = temp;

    }

    /**
     *节点下沉
     * @param array
     * @param parentIndex
     * @param length
     */
    public void downAdjust(int[] array,int parentIndex,int length){
        int tem = array[parentIndex];
        int leftChildIndex = 2*parentIndex + 1;
        while (leftChildIndex < length){
            if(leftChildIndex + 1 < length && array[leftChildIndex + 1 ] < array[leftChildIndex]){
                leftChildIndex++;
            }
           if(tem <= array[leftChildIndex]){
                break;
           }
           array[parentIndex] = array[leftChildIndex];
           parentIndex = leftChildIndex;
            /**
             * 只要没有子节点 肯定超过length
             */
           leftChildIndex = parentIndex*2 + 1;
        }
        array[parentIndex] = tem;
    }

    /**
     *将完全二叉树转换为最小堆
     * @param array 一个完全二叉树
     */
    public  void buildHead(int[] array){
        /**
         * 因为完全二叉树叶子节点可能没有右节点
         */
        for (int i = (array.length-2)/2; i >=0  ; i--) {
            downAdjust(array,i,array.length);
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1,3,2,6,5,7,8,9,10,0};
        BinaryHeap binaryHeap = new BinaryHeap();
        binaryHeap.upAdjust(array,array.length);
        System.out.println(Arrays.toString(array));

        array =  new int[]{7,6,4,3,9,10,5,1};
        binaryHeap.buildHead(array);
        System.out.println(Arrays.toString(array));
    }
}
