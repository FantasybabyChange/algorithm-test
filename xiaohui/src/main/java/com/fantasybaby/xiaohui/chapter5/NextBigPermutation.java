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

    /**
     * 转成整形
     * @param array
     * @return
     */
    public Integer changeToInteger(int[] array){
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     *  找到逆序 边界
     *  12354
     *     ×
     * @param array
     * @return
     */
    public int searchChangePoint(int[] array){
        for (int i = array.length - 1; i > 0; i--) {
            if (array[i] > array[i -1]) {
                return i;
            }
        }
        return 0;

    }

    /**
     * 交换边界前 和 边界后比边界前大的 最小的值
     * 12354   -> 12453
     * @param array
     * @param index
     */
    public void changeHead(int[] array,int index){
        int head = array[index - 1];
        for (int i = array.length-1;  i >= index; i--) {
            if (head < array[i]) {
                int tmp = array[i];
                array[i] = head;
                head = tmp;
                array[index - 1] = tmp;
            }
        }
        System.out.println(Arrays.toString(array));
    }

    /**
     * 给边界内的元素 升序排序
     * @param array
     * @param index
     */
    public void reverse(int[] array,int index){
        for (int i = index,j= array.length - 1; i < j; i++,j--) {
            int tmp = array[i];
            array[i] = array[j];
            array[j] = tmp;
        }

    }
    public Integer getNextBiggerNumber(int currentNumber){
        int[] array = changeToArray(currentNumber);
        if(array.length == 1){
            return array[0];
        }
        int transferPoint = searchChangePoint(array);
        System.out.println("transferPoint index:" + transferPoint);
        if(transferPoint == 0){
            return null;
        }
        changeHead(array,transferPoint);
        reverse(array,transferPoint);
        return changeToInteger(array);
    }

    public static void main(String[] args) {
        Integer nextBiggerNumber = new NextBigPermutation().getNextBiggerNumber(12453);
        System.out.println(nextBiggerNumber);
    }
}
