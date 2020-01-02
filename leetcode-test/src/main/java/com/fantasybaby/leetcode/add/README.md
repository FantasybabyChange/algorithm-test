1.**LongestSubStringWithoutRepeating**
 通过两个指针  一个标记当前位置 A 一个标记重复出现过的位置 B 
 每次发现重复的需要对比 B和当前字符上次出现位置,把B更新成大的获取 获取长度  
2.**MedianOfTwoSortedArrays** 中位数,可以将一段有序的数组完全划分为两段。
我们将 A数组 从 i划分  B数组从 j划分,
在将左边的和右边的分别放入两个部分   left_part和right_part
当满足  len(left_part)=len(right_part) 并且 max(left_part)<= min(right_part)
的时候,可以求出中位数.  
`mid=len(left_part)=len(right_part) / 2`
 
 
  
 