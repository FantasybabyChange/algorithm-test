package com.fantasybaby.xiaohui.chapter5;

import java.util.Stack;

/**通过栈模仿队列
 * 2019/12/15
 *
 * @authorfantasybaby
 **/
public class QueueUseStack<T> {
    Stack<T> innerStack = new Stack<>();
    Stack<T> outterStack = new Stack<>();
    public void enQueue(T data){
        innerStack.push(data);
    }
    public T deQueue() throws Exception {
        if(outterStack.isEmpty()){
            if(innerStack.isEmpty()){
                throw  new Exception("current queue is empty");
            }
            while (!innerStack.isEmpty()){
                outterStack.push(innerStack.pop());
            }
        }
        return outterStack.pop();
    }

    public static void main(String[] args) throws Exception {
        QueueUseStack<Integer> queue = new QueueUseStack();
        queue.enQueue(3);
        queue.enQueue(2);
        queue.enQueue(7);
        queue.enQueue(12);
        Integer deQueue = queue.deQueue();
        System.out.println(deQueue);
        deQueue = queue.deQueue();
        System.out.println(deQueue);
        deQueue = queue.deQueue();
        System.out.println(deQueue);
        deQueue = queue.deQueue();
        System.out.println(deQueue);
        deQueue = queue.deQueue();
        System.out.println(deQueue);
    }
}
