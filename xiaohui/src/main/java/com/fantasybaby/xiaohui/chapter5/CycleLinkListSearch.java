
package com.fantasybaby.xiaohui.chapter5;

import com.fantasybaby.xiaohui.chapter2.Node;

import java.util.Objects;

/**
 * 给定一个链表判断是否为循环链表
 * T(n)=O(n)
 * S(n)=O(1)
 * @author reid.liu
 * @date 2019-12-12 10:08
 */
public class CycleLinkListSearch {
    /**
     * 用两个指针 p1 p2
     * p2比p1快 通过追击原理 如果p2追上了p1则肯定有循环链表
     * @param head
     * @return
     */
    public boolean judgeIsCycleLinkList(Node<Integer> head){
        Node p1 = head;
        Node p2 = head;
        while(Objects.nonNull(p1) && Objects.nonNull(p2.getNext())){
            p1 = p1.getNext();
            p2 = p2.getNext().getNext();
            if(Objects.nonNull(p1)&&p1 == p2){
                return true;
            }
        }
        return false;
    }

    /**
     * 第一次相遇到再一次相遇 前进的次数就是当前环的长度
     * @param head
     * @return
     */
    public int getCycleListLength(Node<Integer> head) {
        Node p1 = head;
        Node p2 = head;
        int count = 0;
        int length = 0;
        while (Objects.nonNull(p1) && Objects.nonNull(p2.getNext())) {
            p1 = p1.getNext();
            p2 = p2.getNext().getNext();
            if (Objects.nonNull(p1) && p1 == p2) {
                if (count == 0) {
                    length = 0;
                    count++;
                } else {
                    return length;
                }
            }
            length++;
        }
        return 0;
    }

    /**
     * 设P为环形入口节点  D为头节点到P的距离
     * 首次相遇的点P2    入口点到P2距离位S1  p2到P的距离位 S2
     * 因为指针2速度是指针1的两倍
     * 所以  2*(D+S1)=n(S1+S2)+D+S1
     * 得到 D=(n-1)(S1+S2)+S2
     * 所以从头节点到P 和 P2走到P的距离一样
     *
     * @param head
     * @return
     */
    public Node getInnerPoint(Node head){
        Node p1 = head;
        Node p2 = head;
        Node firstNode = head;
        Node firstCatchNode = null;
        while(Objects.nonNull(p1) && Objects.nonNull(p2.getNext())){
            p1 = p1.getNext();
            p2 = p2.getNext().getNext();
            if(Objects.nonNull(p1)&&p1 == p2){
                firstCatchNode=p1;
                break;
            }
        }
        if(firstCatchNode != null){
            while(firstCatchNode!= null && firstNode!= null){
                firstNode = firstNode.getNext();
                firstCatchNode = firstCatchNode.getNext();
                if(firstNode == firstCatchNode){
                    return firstNode;
                }
            }
        }
        return null;


    }
    public static void main(String[] args) {
        Node<Integer> head = new Node(5);
        Node node1 = new Node(3);
        head.setNext(node1);
        Node node2 = new Node(7);
        node1.setNext(node2);
        Node node3 = new Node(2);
        node2.setNext(node3);
        Node node4 = new Node(6);
        node3.setNext(node4);
        Node node5 = new Node(8);
        node4.setNext(node5);
        Node node6 = new Node(1);
        node5.setNext(node6);
        node6.setNext(node3);
        boolean isCycle = new CycleLinkListSearch().judgeIsCycleLinkList(head);
        System.out.println(isCycle);
        int length = new CycleLinkListSearch().getCycleListLength(head);
        System.out.println(length);

        Node innerPoint = new CycleLinkListSearch().getInnerPoint(head);
        System.out.println(innerPoint.getData());



    }

}
