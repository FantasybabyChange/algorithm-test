/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.xiaohui.chapter5;

/**
 * 金矿问题
 * 求出
 * g 是每座金矿的矿数量
 * p 为每个矿要多少人
 * @author reid.liu
 * @date 2019-12-18 17:22
 */
public class GoldMining {
    public int biggestGold(int n,int w,int[]g,int[] p){
        if(n == 0 || w == 0){
            return 0;
        }
        if(w < p[n - 1]){
            return biggestGold(n-1,w,g,p);
        }
        return Math.max(biggestGold(n-1,w,g,p),biggestGold(n-1,w-p[n-1],g,p)+g[n-1]);
    }

    public static void main(String[] args) {
        int w = 10;
        int n = 5;
        int[]p = {5,5,3,4,3};
        int[]g = {400,500,200,300,350};
        int i = new GoldMining().biggestGold(n, w, g, p);
        System.out.println(i);
    }
}
