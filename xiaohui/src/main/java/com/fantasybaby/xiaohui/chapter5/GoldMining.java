
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

    public int bestGold(int w,int[]g,int[]p){
        int[][] results = new int[g.length+1][w+1];
        for (int i = 1; i <= g.length; i++) {
            for (int j = 1; j <= w; j++) {
                if(j < p[i - 1]){
                    results[i][j]=results[i-1][j];
                }else{
                    results[i][j]= Math.max(results[i-1][j],results[i-1][j -p[i-1]]+g[i-1]);
                }
            }
        }
        return results[g.length][w];
    }

    /**
     * 优化空间复杂度
     * 从左向右逐渐覆盖最优值
     * @param w
     * @param g
     * @param p
     * @return
     */
    public int bestGold2(int w,int[]g,int[]p){
        int[] results = new int[w+1];
        for (int i = 1; i <= g.length; i++) {
            for (int j = w; j >= 1; j--) {
                if(j >= p[i -1 ]){
                    results[j]= Math.max(results[j],results[j -p[i-1]]+g[i-1]);
                }
            }
        }
        return results[w];
    }


    public static void main(String[] args) {
        int w = 10;
        int n = 5;
        int[]p = {5,5,3,4,3};
        int[]g = {400,500,200,300,350};
        int i = new GoldMining().biggestGold(n, w, g, p);
        System.out.println(i);
        i = new GoldMining().bestGold(w, g, p);
        System.out.println(i);
        i = new GoldMining().bestGold2(w, g, p);
        System.out.println(i);
    }
}
