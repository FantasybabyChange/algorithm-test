package com.fantasybaby.leetcode.add;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串
 * @author: liuxi
 * @time: 2019/12/25 17:03
 */
public class LongestSubStringWithoutRepeating {
    /**
     * T(n)=O(n)
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if(s.length() ==0){
            return 0;
        }
        int maxSubLength = 0;
        int startIndex = 0;
        Map<Character,Integer> charWithLastIndex = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(!charWithLastIndex.containsKey(c)){
                charWithLastIndex.put(c,i);
            }else{
                Integer lastIndex = charWithLastIndex.get(c);
                if(lastIndex >= startIndex){
                    int currentSubLength = i - startIndex;
                    if(maxSubLength < currentSubLength){
                        maxSubLength = currentSubLength;
                    }
                    startIndex =  lastIndex + 1;
                }
                charWithLastIndex.put(c,i);

            }

        }
        int i = s.length() - startIndex;
        if(i > maxSubLength){
            return i;
        }
        return maxSubLength;
    }
    public int lengthOfLongestSubstring1(String s) {
        int length = s.length();
        if(length ==0){
            return 0;
        }
        int startIndex = 0;
        int currentIndex = 0;
        int result = 0;
        Set<Character> set = new HashSet();
        while(startIndex < length && currentIndex < length){
            if(!set.contains(s.charAt(currentIndex))){
                set.add(s.charAt(currentIndex++));
                result = Math.max(result,set.size());
            }else{
                set.remove(s.charAt(startIndex++));
            }
        }
        return result;
    }
    public int lengthOfLongestSubstring2(String s) {
        int maxSubLength = 0;
        int startIndex = 0;
        Map<Character,Integer> charWithLastIndex = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(charWithLastIndex.containsKey(c)){
                startIndex = Math.max(startIndex,charWithLastIndex.get(c)+1);
            }
            maxSubLength = Math.max(maxSubLength,i - startIndex + 1);
            charWithLastIndex.put(c,i );
        }
        return maxSubLength;
    }

    /**
     * 通过数组下标做索引
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring4(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[256];
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }


    public static void main(String[] args) {
        // dvdf pass  3
        //bbbbb pass  1
        //abcabcbb pass 3
        //pwwkew pass 3
        //au pass 2
        //tmmzuxt 5 pass
        //aabaab!bb 3
        int result = new LongestSubStringWithoutRepeating().lengthOfLongestSubstring2("tmmzuxt");
        System.out.println(result);
    }
}
