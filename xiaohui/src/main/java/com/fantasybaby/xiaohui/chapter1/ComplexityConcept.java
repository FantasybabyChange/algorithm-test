package com.fantasybaby.xiaohui.chapter1;

/**一些常用复杂度
 * @author: liuxi
 * @time: 2019/11/25 10:41
 */
public class ComplexityConcept {
    /**
     * 长度为n cm的面包,每3分钟吃1cm整个面包吃完需要多长时间。
     * T(n)=3n;
     *  T(n)=O(n)
     * 线性
     */
    public void complexity1(int n){
        for (int i = 0; i < n; i++) {
            System.out.println("第一分钟");
            System.out.println("第二分钟");
            System.out.println("吃一cm");
        }
    }

    /**
     * 2.假设n cm的面包,每5分钟吃一半,多久才能吃到1cm
     * 对数计算   T(n)=5log2n
     * T(n)=O(log2n)
     * @param n
     */
    public void complexity2(int n){
        for (int i = n; i > 1; i=i/n) {
            System.out.println("第一分钟");
            System.out.println("第二分钟");
            System.out.println("第四分钟");
            System.out.println("第五分钟");
            System.out.println("吃一半");
        }
    }

    /**
     * 3. 1个长度为10cm的面包和1个鸡腿,两分钟吃掉一个鸡腿,问吃掉鸡腿的时间
     * T(n)=2
     * 常量
     * T(n)=O(1)
     * @param n
     */
    public void complexity3(int n){
        System.out.println("第一分钟");
        System.out.println("第二分钟");
        System.out.println("吃掉一个鸡腿");
    }

    /**
     *4.长度为n的面包, 第1cm吃1分钟,第二个1cm吃2分钟,第3个cm吃3分钟,总共吃完要多长时间.
     *多项式计算
     *T(n)=0.5n^2+0.5n
     * T(n)=O(n^2)
     * @param n
     */
    public void complexity4(int n){
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println("第一分钟");
            }
            System.out.println("吃1cm面包");
        }
    }
}
