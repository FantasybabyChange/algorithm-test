package com.fantasybaby.xiaohui.chapter6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
    public List<Integer> dividedRedBag1(int totalAmount,int totalPeopleNum){
        List<Integer> amountList = new ArrayList();
        Set<Integer> segments = new HashSet();
        Random random = new Random();
        for(int i = 0; i< totalPeopleNum-1; i++){
            int segment =  random.nextInt(totalAmount-2) + 1;
            int delta = random.nextInt(1)==0 ? 1 : -1;
            while(segments.contains(segment) || segment == 0){
                segment = (segment+delta)%totalAmount;
            }
            segments.add(segment);
        }

        List<Integer> segmentList = new ArrayList(segments);
        Collections.sort(segmentList);
        for(int i=0; i<segmentList.size(); i++){
            Integer amount;
            if(i==0){
                amount = segmentList.get(0);
            }else {
                amount = segmentList.get(i) - segmentList.get(i-1) ;
            }
            amountList.add(amount);
        }
        amountList.add(totalAmount - segmentList.get(segmentList.size()-1));

        return amountList;
    }

    public static void main(String[] args) {
        int[] ints = new RedBag().dividedRedBag(1000, 5);
        System.out.println(Arrays.toString(ints));
    }
}
