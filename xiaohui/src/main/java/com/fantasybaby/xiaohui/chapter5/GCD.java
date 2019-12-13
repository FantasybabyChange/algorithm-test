/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.xiaohui.chapter5;

/**greatest common divisor
 * @author reid.liu
 * @date 2019-12-13 10:02
 */
public class GCD {
    public int calculateGCD(int a,int b){
        int big;
        int small;
        if(a > b){
            big = a;
            small = b;
        }else{
            big = b;
            small = a;
        }
        /**
         * 阿几里得法
         */
        int r1 = big % small;
        while (r1 != 0){
            big=small;
            small=r1;
            r1=big%small;
        }
        return small;
    }

    public int calculateGCDRecursion(int a,int b){
        int big;
        int small;
        if(a > b){
            big = a;
            small = b;
        }else{
            big = b;
            small = a;
        }
        if(big % small == 0){
            return small;
        }
        return  calculateGCDRecursion(small,big % small);
    }

    /**
     * 使用更相减法
     * @param a
     * @param b
     * @return
     */
    public int calculateGXJRecursion(int a,int b){
        if(a == b){
            return a;
        }
        int big;
        int small;
        if(a > b){
            big = a;
            small = b;
        }else{
            big = b;
            small = a;
        }
        return  calculateGXJRecursion(small,big - small);
    }

    /**
     * 通过移位 做到最优
     * @param a
     * @param b
     * @return
     */
    public int calculateRemixJRecursion(int a,int b){
        if(a == b){
            return a;
        }
        int big;
        int small;
        if(a > b){
            big = a;
            small = b;
        }else{
            big = b;
            small = a;
        }
        return  calculateGXJRecursion(small,big - small);
    }

    public static void main(String[] args) {
        int i = new GCD().calculateGCD(12, 8);
        System.out.println(i);
        i = new GCD().calculateGCDRecursion(25, 5);
        System.out.println(i);
        i = new GCD().calculateGXJRecursion(25, 5);
        System.out.println(i);
    }

}
