package com.fantasybaby.xiaohui.chapter4;

import lombok.Data;

import java.util.Arrays;
import java.util.Stack;

/**
 * 2019/12/8
 * @author fantasybaby
 **/
public class QuickSortWithStack {
    private QuickSortUseRecursion sort = new QuickSortUseRecursion();
    @Data
    public static class InnerIndex{
        private int startIndex;
        private int endIndex;
    }
    public void quickSort(int[]array,int startIndex,int endIndex){
        Stack<InnerIndex> stack = new Stack();
        InnerIndex innerIndex = new InnerIndex();
        innerIndex.setEndIndex(endIndex);
        innerIndex.setStartIndex(startIndex);
        stack.push(innerIndex);
        while(!stack.isEmpty()){
            InnerIndex pop = stack.pop();
            int pivotIndex = sort.partitionSingle(array, pop.startIndex, pop.endIndex);
            if(pop.getStartIndex() < pivotIndex - 1){
                InnerIndex firstInnerIndex = new InnerIndex();
                firstInnerIndex.setStartIndex(pop.getStartIndex());
                firstInnerIndex.setEndIndex(pivotIndex - 1);
                stack.push(firstInnerIndex);
            }
            if(pop.getEndIndex() > pivotIndex + 1){
                InnerIndex secondInnerIndex = new InnerIndex();
                secondInnerIndex.setStartIndex(pivotIndex + 1);
                secondInnerIndex.setEndIndex(pop.getEndIndex());
                stack.push(secondInnerIndex);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,7,6,5,3,2,8,1};
        new QuickSortWithStack().quickSort(array,0,array.length - 1);
        System.out.println(Arrays.toString(array));
    }
}
