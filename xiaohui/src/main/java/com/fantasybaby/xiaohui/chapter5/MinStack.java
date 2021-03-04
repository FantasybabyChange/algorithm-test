
package com.fantasybaby.xiaohui.chapter5;

import java.util.Objects;
import java.util.Stack;

/**最小栈
 * 要让该数据结构 取值
 * @author reid.liu
 * @date 2019-12-12 16:43
 */
public class MinStack {
    private Stack<Integer> currentStack = new Stack();
    private Stack<Integer> minStack = new Stack();

    /**
     * pop最小堆栈顶的值 需要同时pop出来
     * @return
     */
    public Integer pop(){
        Integer pop = currentStack.pop();
        if(Objects.nonNull(pop) && pop.equals(minStack.peek())){
            minStack.pop();
        }
        return pop;
    }

    /**
     *  维护最小队列 遇到小于等于栈顶的值就放进去
     * @param data
     */
    public void push(Integer data){
        currentStack.push(data);
        if(minStack.isEmpty() || minStack.peek() >= data){
            minStack.push(data);
        }
    }
    public Integer getMin() throws Exception {
        if(minStack.isEmpty()){
            throw new Exception("empty stack");
        }
        return minStack.peek();
    }

    public static void main(String[] args) throws Exception {
        MinStack stack = new MinStack();
        stack.push(4);
        stack.push(3);
        stack.push(7);
        stack.push(9);
        stack.push(3);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
    }
}
