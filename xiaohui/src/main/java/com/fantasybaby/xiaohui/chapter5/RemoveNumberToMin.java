package com.fantasybaby.xiaohui.chapter5;


/**删除n个数
 * n <= 当前数字的长度
 * 求怎么删除最小
 * 2019/12/15
 *
 * @authorfantasybaby
 **/
public class RemoveNumberToMin {
    public String removeNumber(String number, int removeLength) throws Exception {
        if(number.length() < removeLength){
            throw new Exception("length bigger than array");
        }
        int newLength = number.length() - removeLength;
        if(newLength == 0){
            return "0";
        }
        /**
         * 利用栈的原理
         */
        char[] newChar = new char[number.length()];
        int top = 0;
        for (int i = 0; i < number.length(); ++i) {
            char c = number.charAt(i);
            while(top > 0 && c < newChar[top - 1] && removeLength > 0){
                top--;
                removeLength--;
            }
            newChar[top++] = c;
        }
        int notZeroIndex = 0;
        for (int i = 0; i < newChar.length; i++) {
            if(newChar[i] != '0'){
                notZeroIndex=i;
                break;
            }
        }
        return new String(newChar,notZeroIndex,newLength - notZeroIndex);
    }

    public static void main(String[] args) throws Exception {
        String s = new RemoveNumberToMin().removeNumber("563468", 3);
        System.out.println(s);
    }

}
