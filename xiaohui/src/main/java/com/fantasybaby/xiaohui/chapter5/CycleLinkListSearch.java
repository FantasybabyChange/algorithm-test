/******************************************************************************
 * Copyright (C) 2018 ShangHai Quicktron Intelligent Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为上海快仓智能科技开发研制。未经本公司正式书面同意，其他任何个人、团体
 * 不得使用、复制、修改或发布本软件.
 *****************************************************************************/
package com.fantasybaby.xiaohui.chapter5;

import com.fantasybaby.xiaohui.chapter2.Node;
import com.google.common.collect.Lists;

import java.util.LinkedList;
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
        while(Objects.nonNull(p1.getNext()) && Objects.nonNull(p2.getNext())){
            p1 = p1.getNext();
            Node pn1 = p2.getNext();
            if(Objects.nonNull(pn1)){
                p2 = pn1.getNext();
                if(Objects.nonNull(p1)&&p1 == p2){
                    return true;
                }
            }
        }
        return false;
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



    }

}
