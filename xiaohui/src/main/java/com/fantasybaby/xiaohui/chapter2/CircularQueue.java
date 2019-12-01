package com.fantasybaby.xiaohui.chapter2;

import java.util.Arrays;

/**
 * 实现循环队列
 * @author: liuxi
 * @time: 2019/12/1 17:32
 */
public class CircularQueue {
    private Integer[] innerArray;
    private int head;
    private int rear;
    public CircularQueue(int size){
        innerArray = new Integer[size+1];
        head=0;
        rear=0;
    }
    public synchronized void enqueue(int element) throws Exception {
        if((rear+1)%innerArray.length == head){
            throw  new Exception("queue is full");
        }
        innerArray[rear]= element;
        rear = (rear + 1) %innerArray.length;
    }
    public synchronized int dequeue() throws Exception {
        if(rear == head){
            throw new Exception("empty queue");
        }
        int dequeueElement = innerArray[head];
        innerArray[head] = null;
        head = (head+1)%innerArray.length;
        return dequeueElement;
    }
    public void print(){
        System.out.println(Arrays.toString(innerArray));
    }
    public static void main(String[] args) {
        CircularQueue circularQueue = new CircularQueue(3);
        try {
            circularQueue.enqueue(3);
            circularQueue.enqueue(2);
            circularQueue.enqueue(1);
            circularQueue.print();
            int dequeue = circularQueue.dequeue();
            System.out.println(dequeue);
            circularQueue.enqueue(7);
            circularQueue.print();
            dequeue = circularQueue.dequeue();
            System.out.println(dequeue);
            circularQueue.enqueue(8);
            circularQueue.print();
            dequeue = circularQueue.dequeue();
            System.out.println(dequeue);
            circularQueue.enqueue(9);
            circularQueue.print();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
