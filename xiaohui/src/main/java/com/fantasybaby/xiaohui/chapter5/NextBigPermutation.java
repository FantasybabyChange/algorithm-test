package com.fantasybaby.xiaohui.chapter5;

import java.util.Arrays;

/**
 * 比当前数字大的 全排列数
 * 列入 12345
 *   -> 12354
 * 2019/12/15
 *
 * @authorfantasybaby
 **/
public class NextBigPermutation {
    public int[] changeToArray(Integer inte){
        String s = String.valueOf(inte);
        int[] newArray = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            newArray[i] = Integer.parseInt(s.charAt(i)+"");
        }
        return newArray;
    }
    public Integer changeToInteger(int[] array){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        return Integer.parseInt(sb.toString());
    }
    public Integer getNextBiggerNumber(int currentNumber){
        int[] array = changeToArray(currentNumber);
        if(array.length == 1){
            return array[0];
        }
        int transferPoint = 0;
        for (int i = array.length - 1; i > 0; i--) {
            int i1 = array[i];
            int i2 = array[i-1];
            if(i1 > i2){
                transferPoint = i;
            }
        }
        if(transferPoint == 0){
            return null;
        }
        int tmp = array[transferPoint + 1];
        array[transferPoint + 1] = array[array.length - 1];
        array[array.length - 1] = tmp;
        for (int i = array.length - 1; i > transferPoint; i--) {
            int i1 = array[i];
            int i2 = array[i-1];
            if(i1 < i2){
                int tmp = array[transferPoint + 1];
                array[transferPoint + 1] = array[array.length - 1];
                array[array.length - 1] = tmp;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        Integer nextBiggerNumber = new NextBigPermutation().getNextBiggerNumber(12354);
        System.out.println(nextBiggerNumber);
    }
}
