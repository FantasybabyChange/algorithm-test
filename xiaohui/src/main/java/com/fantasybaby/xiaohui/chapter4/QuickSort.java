package com.fantasybaby.xiaohui.chapter4;

import java.util.Arrays;

/**快速排序
 * 2019/12/8
 * @author FantasyBaby
 **/
public class QuickSort {
    /**
     * 通过双边循环排序
     */
    public void quickSort(int[] array,int startIndex,int endIndex,boolean isSingle){
        if(startIndex >= endIndex){
            return;
        }
        int index ;
        if(isSingle){
            index = partitionSingle(array,startIndex,endIndex);
        }else {
            index = partitionTwo(array,startIndex,endIndex);
        }
        quickSort(array,startIndex,index -1,isSingle);
        quickSort(array,index + 1,endIndex,isSingle);
    }
    private int partitionTwo(int[] array,int startIndex,int endIndex){
        System.out.println(startIndex+"-"+endIndex);
        /**
         * 这里可以使用第一个元素或者通过随机获取基准元素
         */
        int pivot = array[startIndex];
        int left = startIndex;
        int right = endIndex;
        while(left != right){
            while (left < right && array[right] > pivot){
                right--;
            }
            while(left < right && array[left] <= pivot){
                left++;
            }
            if(left < right){
                int leftElement = array[left];
                array[left] = array[right];
                array[right] = leftElement;
            }
        }
        array[startIndex] = array[left];
        array[left] = pivot;
        System.out.println(Arrays.toString(array));
        System.out.println("left:"+left);
        return  left;
    }
    private int partitionSingle(int[] array,int startIndex,int endIndex){
        /**
         * 这里可以使用第一个元素或者通过随机获取基准元素
         */
        int mark = array[startIndex];
        /**
         * 标记Index
         */
        int markIndex = startIndex;
        for (int i = startIndex; i <= endIndex; i++) {
            if(array[i] < mark){
                markIndex++;
                int tmp = array[i];
                array[i] = array[markIndex];
                array[markIndex] = tmp;
            }
        }
        array[startIndex] = array[markIndex];
        array[markIndex] = mark;
        return  markIndex;
    }
    public static void main(String[] args) {
        int[] array = new int[]{4,7,6,5,3,2,8,1};
        new QuickSort().quickSort(array,0,array.length - 1,true);
        System.out.println(Arrays.toString(array));
    }
}
