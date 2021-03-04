
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
     * 通过移位 减少相减的次数 做到最优
     * @param a
     * @param b
     * @return
     */
    public int calculateRemixJRecursion(int a,int b){
        if(a == b){
            return a;
        }
        /**
         * a,b都是偶数那就除以2  最后在乘以2
         */
        if(isEven(a) && isEven(b)){
            return calculateRemixJRecursion(a>>1,b>>1) << 1;
        }else if (isOdd(a) && isEven(b)){
            return calculateRemixJRecursion(a,b>>1);
        }else if (isEven(a) && isOdd(b)){
            return calculateRemixJRecursion(a>>1,b);
        }else{
            /**
             * 如果都是偶数 就用更相减损法
             */
            int big = a > b ?a:b;
            int small = a > b?b:a;
            return calculateRemixJRecursion(big-small,small);
        }
    }

    /**
     * 判断是否为奇数
     * @param a
     * @return
     */
    private boolean isOdd(int a){
        return (a & 1) == 1;
    }
    private boolean isEven(int a){
        return (a & 1) == 0;
    }

    public static void main(String[] args) {
        int i = new GCD().calculateGCD(12, 8);
        System.out.println(i);
        i = new GCD().calculateGCDRecursion(25, 5);
        System.out.println(i);
        i = new GCD().calculateGXJRecursion(25, 5);
        System.out.println(i);
        i = new GCD().calculateRemixJRecursion(1000, 3100);
        System.out.println(i);
    }

}
