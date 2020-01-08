package com.fantasybaby.leetcode.sort;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 *
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 * 示例 1:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 *
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 *
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 * @author: liuxi
 * @time: 2020/1/6 17:38
 */
public class ZigzagConversion {
    /**
     * S(n)=O(n)
     * T(n)=O(n)
     * index 4 3 2 1
     * odd
     *
     * n = index
     *
     * 使用StringBuilder替换 StringBuffer
     *执行用时 :
     * 6 ms
     * , 在所有 Java 提交中击败了
     * 80.19%
     * 的用户
     * 内存消耗 :
     * 38.7 MB
     * , 在所有 Java 提交中击败了
     * 95.12%
     * 的用户
     * 的用户
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if(numRows == 1){
            return s;
        }
        int minLength = Math.min(numRows, s.length());
        StringBuilder[] rows = new StringBuilder[minLength];
        for (int i = 0; i < rows.length ; i++) {
            rows[i] = new StringBuilder();
        }
        int index = 0;
        boolean group = false;
        for (int i = 0; i < s.length(); i++) {
            rows[index].append(s.charAt(i));
            if(index == 0 || index == minLength -1){
                group = !group;
            }
            index += group ? 1 : -1;
        }
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

    /**
     * 1.起始下标都是行号
     *
     * 2.第0层和第numRows-1层的下标间距总是step 。
     *
     * 3.中间层的下标间距总是step-2*行数，2*行数交替。
     *
     * 4.下标不能超过len(s)-1
     * (1)行 00 中的字符位于索引 k(2⋅numRows−2) 处;
     * (2)行 numRows−1 中的字符位于索引 k(2⋅numRows−2)+numRows−1 处;
     * (3)内部的 行i 中的字符位于索引k(2⋅numRows−2)+i 以及 (k+1)(2⋅numRows−2)−i 处

     * @param s
     * @param numRows
     * @return
     */
    public String convert1(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder ret = new StringBuilder();
        int n = s.length();
        int cycleLen = 2 * numRows - 2;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j + i < n; j += cycleLen) {
                ret.append(s.charAt(j + i));
                if (i != 0 && i != numRows - 1 && j + cycleLen - i < n)
                    ret.append(s.charAt(j + cycleLen - i));
            }
        }
        return ret.toString();
    }

    /**
     * 起始下标都是行号
     *
     * 第0层和第numRows-1层的下标间距总是step 。
     *
     * 中间层的下标间距总是step-2*行数，2*行数交替。
     *
     * 下标不能超过len(s)-1
     * @return
     */
    String convert2(String s, int numRows) {
        if (numRows == 1) return s;
        int step = numRows * 2 - 2; // 间距
        int index,add;// 记录s的下标 // 真实的间距
        int len = s.length();
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < numRows; i++) // i表示行号
        {
            index = i;
            add = i * 2;
            while (index < len)//超出字符串长度计算下一层
            {
                ret.append(s.charAt(index)); // 当前行的第一个字母
                add = step - add;// 第一次间距是step -2*i，第二次是2*i,
                index += (i == 0 || i == numRows-1) ? step : add; // 0行和最后一行使用step间距，其余使用add间距
            }
        }
        return ret.toString();
    }
    public static void main(String[] args) {
        String st = "PAYPALISHIRING";
        int index = 3;
        String convert = new ZigzagConversion().convert2(st, index);
        System.out.println(convert);
    }
}
