package com.fantasybaby.xiaohui.chapter3;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * 树的遍历
 * @author: liuxi
 * @time: 2019/12/2 20:03
 */
public class TreeIterator {
    @Data
    static class TreeNode<T>{
        private T data;
        private TreeNode<T> leftChild;
        private TreeNode<T> rightChild;

    }
    public TreeIterator(){

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
     * 前序遍历
     * @param treeNode
     */
    public void preOrderTraversal(TreeNode treeNode){
        if(Objects.isNull(treeNode)){
            return;
        }
        System.out.print(","+treeNode.getData());
        preOrderTraversal(treeNode.leftChild);
        preOrderTraversal(treeNode.rightChild);
    }

    /**
     * 中序遍历
     * @param treeNode
     */
    public void inOrderTraversal(TreeNode treeNode){
        if(Objects.isNull(treeNode)){
            return;
        }
        inOrderTraversal(treeNode.leftChild);
        System.out.print(","+treeNode.getData());
        inOrderTraversal(treeNode.rightChild);

    }
    /**
     * 后序遍历
     * @param treeNode
     */
    public void postOrderTraversal(TreeNode treeNode){
        if(Objects.isNull(treeNode)){
            return;
        }
        postOrderTraversal(treeNode.leftChild);
        postOrderTraversal(treeNode.rightChild);
        System.out.print(","+treeNode.getData());
    }

    /**
     * 用栈实现前序遍历
     * @param root
     */
    public <T>void preOrderTraversalWithStack(TreeNode<T> root){
        Stack<TreeNode<T>> stack = new Stack();
        TreeNode newTree = root;
        while (!stack.isEmpty() || newTree != null){
           while (newTree != null){
               System.out.print(","+newTree.getData());
               stack.push(newTree);
               newTree = newTree.leftChild;
           }
           if(!stack.isEmpty()){
               TreeNode<T> node = stack.pop();
               newTree = node.getRightChild();
           }
        }
    }
    /**
     * 用栈实现中序遍历
     * @param root
     */
    public <T>void inOrderTraversalWithStack(TreeNode<T> root){
        Stack<TreeNode<T>> stack = new Stack();
        TreeNode newTree = root;
        while (!stack.isEmpty() || newTree != null){
            while (newTree != null){
                stack.push(newTree);
                newTree = newTree.leftChild;
            }
            if(!stack.isEmpty()){
                TreeNode<T> node = stack.pop();
                System.out.print(","+node.getData());
                newTree = node.getRightChild();
            }
        }
    }
    /**
     * 用栈实现后序遍历
     * @param root
     */
    public <T>void postOrderTraversalWithStack(TreeNode<T> root){
        int left=1;
        int right=2;
        Stack<TreeNode<T>> stack1 = new Stack();
        Stack<Integer> stack2 = new Stack();
        TreeNode newTree = root;
        while (!stack1.isEmpty() || newTree != null){
            while(newTree != null)
            {//将节点压入栈1，并在栈2将节点标记为左节点
                stack1.push(newTree);
                stack2.push(left);
                newTree = newTree.getLeftChild();
            }

            while(!stack1.empty() && stack2.peek() == right)
            {//如果是从右子节点返回父节点，则任务完成，将两个栈的栈顶弹出
                stack2.pop();
                System.out.print(","+stack1.pop().getData());
            }

            if(!stack1.empty() && stack2.peek() == left)
            {//如果是从左子节点返回父节点，则将标记改为右子节点
                stack2.pop();
                stack2.push(right);
                newTree = stack1.peek().getRightChild();
            }
        }
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
        TreeIterator iterator = new TreeIterator();
        TreeNode<Integer> rootNode = iterator.createTreeNode(inputList);
        iterator.preOrderTraversal(rootNode);
        System.out.println();
        iterator.inOrderTraversal(rootNode);
        System.out.println();
        iterator.postOrderTraversal(rootNode);
        System.out.println();
        iterator.preOrderTraversalWithStack(rootNode);
        System.out.println();
        iterator.inOrderTraversalWithStack(rootNode);
        System.out.println();
        iterator.postOrderTraversalWithStack(rootNode);

    }
}
