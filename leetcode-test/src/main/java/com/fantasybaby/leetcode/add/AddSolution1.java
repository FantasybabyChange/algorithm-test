package com.fantasybaby.leetcode.add;

/**
 *
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author: liuxi
 * @time: 2019/12/24 12:02
 */
public class AddSolution1 {
    static  class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int up = 0;
        ListNode lastNode = null;
        ListNode head = null;

        while (l1 != null){
            if(l2 != null){
                int i = l1.val + l2.val + up;
                if(i >= 10){
                    i = i-10;
                    up = 1;
                }else{
                    up = 0;
                }
                if(lastNode == null){
                    lastNode = new ListNode(i);
                    head = lastNode;
                }else{
                    ListNode listNode = new ListNode(i);
                    lastNode.next = listNode;
                    lastNode = listNode;
                }
                l2 = l2.next;
            }else{
                int i = l1.val + up;
                if(i >= 10){
                    i = i-10;
                    up = 1;
                }else{
                    up = 0;
                }
                ListNode listNode = new ListNode(i);
                lastNode.next = listNode;
                lastNode = listNode;
            }
            l1 = l1.next;
        }
        if(l2 != null){
            while (l2 != null){
                int i = l2.val + up;
                if(i >= 10){
                    i = i-10;
                    up = 1;
                }else{
                    up = 0;
                }
                ListNode listNode = new ListNode(i);
                lastNode.next = listNode;
                lastNode = listNode;

                l2 = l2.next;
            }
        }
         if(up == 1){
             ListNode listNode = new ListNode(up);
             lastNode.next = listNode;
         }

        return head;
    }

    /**
     * 通过补0简化一下循环
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        int up = 0;
        ListNode lastNode = null;
        ListNode head = null;

        while (l1 != null || l2 != null) {
            int v1 = l1 == null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            int i = v1 + v2 + up;
            if (i >= 10) {
                i = i - 10;
                up = 1;
            } else {
                up = 0;
            }
            if (lastNode == null) {
                lastNode = new ListNode(i);
                head = lastNode;
            } else {
                ListNode listNode = new ListNode(i);
                lastNode.next = listNode;
                lastNode = listNode;
            }
            if(l1 != null){
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }
        if(up == 1){
            ListNode listNode = new ListNode(up);
            lastNode.next = listNode;
        }

        return head;
    }
    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
//        ListNode l2 = new ListNode(4);
//        ListNode l3 = new ListNode(3);
//        l1.next = l2;
//        l2.next = l3;

        ListNode l4 = new ListNode(9);

        ListNode l5 = new ListNode(9);
        l4.next = l5;
//        ListNode l6 = new ListNode(4);
//        l5.next = l6;
        ListNode listNode = new AddSolution1().addTwoNumbers(l4, l1);
        while (listNode != null){
            System.out.print(listNode.val+",");
            listNode = listNode.next;
        }
        System.out.println();
        ListNode l7 = new ListNode(1);
        ListNode l8 = new ListNode(8);
        listNode = new AddSolution1().addTwoNumbers(l7, l8);
        while (listNode != null){
            System.out.print(listNode.val);
            listNode = listNode.next;
        }
        System.out.println();
        listNode = new AddSolution1().addTwoNumbers1(l4, l1);
        while (listNode != null){
            System.out.print(listNode.val+",");
            listNode = listNode.next;
        }
    }
}
