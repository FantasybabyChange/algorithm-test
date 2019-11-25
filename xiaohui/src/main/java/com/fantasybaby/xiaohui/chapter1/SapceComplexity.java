package com.fantasybaby.xiaohui.chapter1;

/**
 * 空间复杂度
 * @author: liuxi
 * @time: 2019/11/25 16:14
 */
public class SapceComplexity {
    /**
     * S(n)=O(1)
     */
    public void complex1(){
        int a = 3;
    }
    /**
     * S(n)=O(n)
     */
    public void complex2(int n){
        int[] a = new int[n];
    }
    /**
     * S(n)=O(n^2)
     */
    public void complex3(int n){
        int[][] a = new int[n][n];
    }
    /**
     * S(n)=O(n)
     */
    public void complex4(int n){
        if(n < 1){
            return;
        }
        complex4(n - 1);
    }
}
