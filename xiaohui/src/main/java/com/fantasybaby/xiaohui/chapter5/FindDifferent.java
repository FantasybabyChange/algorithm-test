package com.fantasybaby.xiaohui.chapter5;

import java.util.Arrays;

/**
 * 给定一个数组
 * 其中 有两个数出现了奇数次 其他数都出现了偶数次
 * 求这两个数
 * @author reid.liu
 * @date 2019-12-19 16:17
 */
public class FindDifferent {
    public int[] find(int[] input){
        int result = input[0];
        for (int i = 1; i < input.length; i++) {
            result =result^ input[i];
        }
        int index = 1;
        while ((result & index) == 0){
            index<<=1;

        }
        int[] an = new int[2];
        for (int i = 0; i < input.length; i++) {
            if((input[i]& index) == 0){
                an[0] =an[0] ^ input[i];
            }else{
                an[1] =an[1] ^ input[i];
            }

        }
        return an;
    }

    public static void main(String[] args) {
        int[] array = new int[]{4,1,2,2,5,1,4,3};
        int[] result = new FindDifferent().find(array);
        System.out.println(Arrays.toString(result));
    }
}
