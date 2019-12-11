/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.xiaohui.chapter4;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**桶排序实现
 * @author reid.liu
 * @date 2019-12-11 17:43
 */
public class BucketSort {
    /**
     *
     * @param array
     */
    public double[] sort(double[] array){
        double max = 0;
        double min = 0;
        for (int i = 0; i < array.length; i++) {
            if(max < array[i]){
                max = array[i];
            }
            if(min > array[i]){
                min = array[i];
            }
        }
        /**
         * 桶的数量和数组大小相同
         */
        int bucketNum = array.length;
        /**
         * 如果在一个桶中 可以用链表存放
         */
        List<LinkedList<Double>> bucketWithElements = Lists.newArrayList();
        for (int i = 0; i < bucketNum; i++) {
            bucketWithElements.add(new LinkedList());
        }
        for (int i = 0; i < array.length; i++) {
            /**
             * (e-min)/((max - min)/(bucket-1))= (e-min)*(bucketNum-1)/(max-min)
             * 计算index
             */
            bucketWithElements.get((int) ((array[i]-min)*(bucketNum-1)/max-min)).add(array[i]);
        }
        for (int i = 0; i < bucketWithElements.size(); i++) {
            Collections.sort(bucketWithElements.get(i));
        }

        double[] sortedArray = new double[array.length];
        int index=0;
        for (int i = 0; i < bucketWithElements.size(); i++) {
            LinkedList<Double> values = bucketWithElements.get(i);
            for (Double value : values) {
                sortedArray[index] = value;
                index++;
            }
        }
        return sortedArray;
    }

    public static void main(String[] args) {
        double[] array = new double[]{4.12,6.24,3.34,7.23,8.61,10.09};
        double[] sort = new BucketSort().sort(array);
        System.out.println(Arrays.toString(sort));
    }
}
