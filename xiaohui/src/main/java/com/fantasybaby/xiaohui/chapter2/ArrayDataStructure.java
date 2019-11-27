package com.fantasybaby.xiaohui.chapter2;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 数组的数据结构
 * @author: liuxi
 * @time: 2019/11/27 14:44
 */
public class ArrayDataStructure {
    private int[] array;
    private int size;
    public ArrayDataStructure(int capacity){
        array =new int[capacity];
        this.size = 0;
    }

    /**
     * 数组越界
     * @param index
     * @param elment
     */
    public void insert(int index,int elment){
        if(index < 0 || index > size ){
            throw new ArrayIndexOutOfBoundsException("数组越界了");
        }
        if(size >= array.length){
            resize();
        }
        for (int i = size - 1; i >= index  ; i--) {
            array[i+1] = array[i];
        }
        array[index]=elment;
        size++;
    }
    public void print(){
        System.out.println(Arrays.toString(array));
    }
    public void resize(){
        int[] newArray = new int[2*array.length];
        System.arraycopy(array,0,newArray,0,array.length);
        array = newArray;
    }
    public static void main(String[] args) {
        ArrayDataStructure arrayDataStructure = new ArrayDataStructure(3);
        arrayDataStructure.insert(0,10);
        arrayDataStructure.insert(1,2);
        arrayDataStructure.insert(2,3);
        arrayDataStructure.print();
        arrayDataStructure.insert(3,3);
        arrayDataStructure.insert(4,3);
        arrayDataStructure.print();
    }
}
