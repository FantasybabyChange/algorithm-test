
package com.fantasybaby.leetcode.add;

/**
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *
 * 输入: 123
 * 输出: 321
 *  示例 2:
 *
 * 输入: -123
 * 输出: -321
 * 示例 3:
 *
 * 输入: 120
 * 输出: 21
 * 注意:
 *
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author reid.liu
 * @date 2020-01-03 10:48
 */
public class ReverseInteger {
    /**
     * 这块要对整数的最大值和最小值 做边界校验
     * @param x
     * @return
     */
    public int reverse(int x) {
        int ans = 0;
        while(x != 0){
            int pop = x % 10;
            if(ans > Integer.MAX_VALUE/10 || (ans  == Integer.MAX_VALUE/10 && pop  > Integer.MAX_VALUE%10)){
                return 0;
            }
            if(ans < Integer.MIN_VALUE/10 || (ans  == Integer.MIN_VALUE/10 && pop  < Integer.MIN_VALUE%10)){
                return 0;
            }
            ans = ans*10 + pop;
            x = x / 10;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE );
        System.out.println(Integer.MIN_VALUE );
        int reverse = new ReverseInteger().reverse(1463847412);
        System.out.println(reverse);
    }
}
