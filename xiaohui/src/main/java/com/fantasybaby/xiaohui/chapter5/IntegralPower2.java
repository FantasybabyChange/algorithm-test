package com.fantasybaby.xiaohui.chapter5;

/**
 * 求出2的整数幂
 * 2019/12/15
 *
 * @authorfantasybaby
 **/
public class IntegralPower2 {
     public boolean judgeIsIntegral(int a){
         if(a <= 0){
             return false;
         }
         return (a&(a-1)) == 0;
     }

    public static void main(String[] args) {
        boolean b = new IntegralPower2().judgeIsIntegral(1);
        System.out.println(b);
    }
}
