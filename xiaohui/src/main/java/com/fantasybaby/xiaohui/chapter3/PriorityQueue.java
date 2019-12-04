package com.fantasybaby.xiaohui.chapter3;

import java.util.Arrays;

/**
 * @author: liuxi
 * @time: 2019/12/4 9:27
 */
public class PriorityQueue {
    private int[] array;
    private int length;
    BinaryHeap binaryHeap = new BinaryHeap();
    public PriorityQueue(){
        array = new int[32];
    }

    /**
     * 入队 上浮
     * @param element
     */
    public void enqueue(int element){
        if(array.length <= length){
            resize();
        }
        array[length++] = element;
        binaryHeap.upAdjust(array,length);
        System.out.println(Arrays.toString(array));
    }
    public int dequeue() throws Exception {
        if(length < 0){
            throw  new Exception("queue is empty");
        }
        int head = array[0];
        array[0]=array[--length];
        binaryHeap.downAdjust(array,0,length);
        return  head;
    }
    public void resize(){
        int newSize = array.length *2 ;
        array = Arrays.copyOf(array, newSize);
    }

    public static void main(String[] args) throws Exception {
        PriorityQueue queue = new PriorityQueue();
        queue.enqueue(4);
        queue.enqueue(6);
        queue.enqueue(2);
        queue.enqueue(3);
        System.out.println(queue.dequeue());
    }
}
