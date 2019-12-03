package com.fantasybaby.xiaohui.chapter3;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * 树的遍历
 * @author: liuxi
 * @time: 2019/12/2 20:03
 */
public class BreadthSearch {
    @Data
    static class TreeNode<T>{
        private T data;
        private TreeNode<T> leftChild;
        private TreeNode<T> rightChild;

    }
    public BreadthSearch(){

    }

    /**
     * 构建二叉树
     * 使用递归
     * 这种实现需要线性的输入结构为前序遍历的结果
     * @param inputList
     * @param <T>
     * @return
     */
    public <T>TreeNode<T> createTreeNode(List<T> inputList){
        TreeNode<T> treeNode = null;
        if(CollectionUtils.isEmpty(inputList)){
            return null;
        }
        T remove = inputList.remove(0);
        if(Objects.nonNull(remove)){
            treeNode = new TreeNode();
            treeNode.setData(remove);
            treeNode.setLeftChild(createTreeNode(inputList));
            treeNode.setRightChild(createTreeNode(inputList));
        }
        return treeNode;
    }

    /**
     * 使用队列
     * 层序遍历
     * @param root
     */
    public void levelOrderTraversal(TreeNode root){
        Queue<TreeNode> queue = new LinkedList();
        TreeNode treeNode = root;
        queue.offer(treeNode);
        while (!queue.isEmpty()){
            TreeNode poll = queue.poll();
            System.out.print(","+poll.getData());
            TreeNode leftChild = poll.getLeftChild();
            TreeNode rightChild = poll.getRightChild();
            if(leftChild != null){
                queue.offer(leftChild);
            }
            if(rightChild != null){
                queue.offer(rightChild);
            }
        }
    }
    List<List> res = new ArrayList<>();
    /**
     * 使用递归层次遍历
     * @param treeNode
     * @param level
     */
    public void levelOrderTraversalWithRecursion(TreeNode treeNode,int level){
        //递归终止条件
        if (treeNode == null) {
            return;
        }
        if (level >= res.size()) {
            //如果是新的一层,就创建
            res.add(new ArrayList<>());
        }
        //添加当前的元素
        res.get(level).add(treeNode.getData());
        //遍历左节点
        levelOrderTraversalWithRecursion(treeNode.getLeftChild(), level + 1);
        //遍历又节点
        levelOrderTraversalWithRecursion(treeNode.getRightChild(), level + 1);

    }
    /**           3
     *          /  \
     *         2    8
     *        / \    \
     *       9   10   4
     * @param args
     */
    public static void main(String[] args) {
        List<Integer> inputList = Lists.newArrayList(new Integer[]{
                3,2,9,null,null,10,null,null,8,null,4}
        );
        BreadthSearch iterator = new BreadthSearch();
        TreeNode<Integer> rootNode = iterator.createTreeNode(inputList);
        iterator.levelOrderTraversal(rootNode);
        System.out.println();
        iterator.levelOrderTraversalWithRecursion(rootNode,0);
        List<List> res = iterator.res;
        for (List re : res) {
            for (Object o : re) {
                System.out.print(","+o);
            }
        }
    }
}
