package com.fantasybaby.xiaohui.chapter6;
/**
 * 完成一个bitMap的逻辑
 * 2019/12/22
 *
 * @authorfantasybaby
 **/
public class BitMap {
    private long[] wordsBitMap ;
    private int size;
    public BitMap(int size){
        wordsBitMap = new long[getWordsIndex(size -1)+1];
        this.size = size;
    }
    /**
     * 向右移位 2^6
     * 获得一个位置在当前
     * 因为一个long整型是64位
     * 通过计算得知应该在那个long形数组中
     * @param i
     * @return
     */
    public int getWordsIndex(int i){
        return i >> 6;
    }
    public boolean getBit(int bitIndex){
        if(bitIndex < 0 ||bitIndex > size - 1){
            throw new IndexOutOfBoundsException();
        }
        int wordsIndex = getWordsIndex(bitIndex);
        return (wordsBitMap[wordsIndex] & 1<<bitIndex) !=0;
    }

    public void setBit(int bitIndex){
        if(bitIndex < 0 ||bitIndex > size - 1){
            throw new IndexOutOfBoundsException();
        }
        int wordsIndex = getWordsIndex(bitIndex);
        /**
         * 位溢出  重新开始
         *  1L << 65
         * 1L是Long 整型 有64位
         * 所以 1L<<65  1在 65%(64-1)的位置
         */
        wordsBitMap[wordsIndex] |= 1L << bitIndex;
    }
    public static void main(String[] args) {
        BitMap bitMap = new BitMap(64);
        bitMap.setBit(63);
        System.out.println(bitMap.getBit(62));
    }
}
