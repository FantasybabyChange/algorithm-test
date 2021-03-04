
package com.fantasybaby.leetcode.palindrome;

/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 示例 1:
 *
 * 输入: 121
 * 输出: true
 * 示例 2:
 *
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 *
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 *进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 * @author reid.liu
 * @date 2020-01-04 11:37
 */
public class PalindromeNumber {
    public boolean isPalindrome(int x) {
        if(x <0 ){
            return false;
        }
        int a = x;
        int ans = 0;
        while(a != 0){
            ans = ans * 10 + a % 10;
            a = a / 10;
        }
        return ans == x;
    }

    /**
     * 通过只转换一般 防止溢出
     * @param x
     * @return
     */
    public boolean isPalindrome1(int x) {
        if(x <0  || (x % 10 == 0 && x != 0)){
            return false;
        }
        int ans = 0;
        while(x > ans){
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        //由于可能会有奇数的情况 所以要/10
        return ans == x || x == ans /10;
    }

    public static void main(String[] args) {
        System.out.println(new PalindromeNumber().isPalindrome1(12321));
    }
}
