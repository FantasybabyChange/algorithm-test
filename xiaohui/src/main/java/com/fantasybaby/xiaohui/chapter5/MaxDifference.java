package com.fantasybaby.xiaohui.chapter5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

/**
 * 给定一个无序数组
 * 求出最大差值
 * 2019/12/15
 *
 * @authorfantasybaby
 **/
public class MaxDifference {
    /**
     * 通过计数排序的思想 算出差值
     * @param array
     */
    public int useCountSortCalculate(int[] array){
        int max = array[0];
        int min = array[0];
        for (int i : array) {
            if(i > max){
                max = i;
            }
            if(i < min){
                min = i;
            }
        }
        /**
         * 求出计算区间
         */
        int d = max - min +1;
        int[] countArray = new int[d];
        for (int i = 0; i < array.length; i++) {
            countArray[array[i] - min]++;
        }
        System.out.println("sorted count:"+Arrays.toString(countArray));
        int maxZeroCount = 0;
        int zeroCount = 1;
        for (int i = 0; i < countArray.length; i++) {
            if(countArray[i] == 0){
                zeroCount++;
            }else if(countArray[i]  > 0){
                if(zeroCount > maxZeroCount){
                    maxZeroCount = zeroCount;
                    zeroCount = 0;
                }
            }
        }
        return maxZeroCount+1;
    }

    /**
     * 使用桶排序的思想计算出差值
     * @param array
     * @return
     */
    public int calculateByBucketSort(int[] array){
        int max = array[0];
        int min = array[0];
        for (int i : array) {
            if(i > max){
                max = i;
            }
            if(i < min){
                min = i;
            }
        }
        int bucketNum = array.length;
        int d = max - min;
        Bucket[] bucketArray = new Bucket[bucketNum ];
        for (int i = 0; i < array.length; i++) {
            int index = (array[i] - min) * (bucketNum - 1) / d;
            Bucket bucket = bucketArray[index];
            if(Objects.isNull(bucket)){
                bucket = new Bucket();
                bucket.max = array[i];
                bucket.min = array[i];
                bucketArray[index] = bucket;
            }else{
                if(bucket.max < array[i]){
                    bucket.max = array[i];
                }
                if(array[i] < bucket.min){
                    bucket.min = array[i];
                }
            }
        }
        int maxDif = Objects.isNull(bucketArray[0])?0:bucketArray[0].max - bucketArray[0].min;
        int leftMax = 0;
        /**
         * 到底需不需要计算内部最大和最小的值
         */
        for (int i = 1; i < bucketArray.length; i++) {
            Bucket bucket = bucketArray[i];
            if(Objects.isNull(bucket)){
                continue;
            }
            int innerDif = bucket.max - bucket.min;
//            Bucket lastBucket = bucketArray[lastIndex];
            /*if(Objects.isNull(lastBucket) ){
                if(maxDif < innerDif){
                    maxDif = innerDif;
                }
                continue;
            }*/
            int distance = bucket.min - leftMax;
            int max1 = Math.max(distance, innerDif);
            int max2 = Math.max(max1, maxDif);
            maxDif = max2;
            leftMax = bucket.max;
        }
        return maxDif;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Bucket{
        private Integer min;
        private Integer max;
    }
    public static void main(String[] args) {
        int[] array = new int[]{2,6,3,4,5,15,9};
        int result = new MaxDifference().useCountSortCalculate(array);
        System.out.println(result);
        result = new MaxDifference().calculateByBucketSort(array);
        System.out.println(result);
    }

}
