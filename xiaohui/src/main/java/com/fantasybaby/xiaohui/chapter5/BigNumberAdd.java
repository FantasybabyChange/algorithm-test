
package com.fantasybaby.xiaohui.chapter5;

/**大数字相加
 * @author reid.liu
 * @date 2019-12-17 14:28
 */
public class BigNumberAdd {
    /**
     *  优化 可以按照数据类型的最大位拆分 比如Int 可以有十位
     *  为了防止溢出 用9位的化，可以减少数组开销
     * @param a
     * @param b
     * @return
     */
    public String add(String a,String b){
        int maxLength = a.length() > b.length() ? a.length() : b.length();
        int[] newArray = new int[maxLength + 1];
        int aIndex = a.length() -1;
        int bIndex = b.length() - 1;
        for (int i = maxLength; i > 0 ; i--) {
            if(aIndex >= 0){
                int intA = a.charAt(aIndex) - '0';
                newArray[i]+=intA;
                aIndex--;
            }
            if(bIndex >= 0){
                int intB = b.charAt(bIndex) - '0';
                newArray[i] += intB;
                bIndex--;
            }
            if(newArray[i] >= 10){
                newArray[i] -= 10;
                newArray[i-1] = 1;
            }

        }
        boolean findFirst = false;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < newArray.length; i++) {
            if(!findFirst ){
                if( newArray[i] == 0){
                    continue;
                }
                findFirst = true;
            }
            sb.append(newArray[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String a = "742135";
        String b = "341236";
        String add = new BigNumberAdd().add(a, b);
        System.out.println(add);
    }
}
