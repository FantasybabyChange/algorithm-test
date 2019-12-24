package com.fantasybaby.xiaohui.chapter6;

import java.util.Arrays;
import java.util.Random;

/**
 * @author: liuxi
 * @time: 2019/12/23 20:44
 */
public class RedBag {
    public int[] dividedRedBag(int money,int p){
        int[] result = new int[p];
        Random random = new Random();
        int restP = p;
        int restM = money;
        for (int i = 0; i < p; i++) {
            int i1 = 2*(restM / restP) - 1;
            int i2 = random.nextInt(i1)+1;
            result[i] = i2;
            restP--;
            restM -=i2;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] ints = new RedBag().dividedRedBag(1000, 5);
        System.out.println(Arrays.toString(ints));
    }
}
