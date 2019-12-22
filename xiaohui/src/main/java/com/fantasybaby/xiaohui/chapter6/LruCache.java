package com.fantasybaby.xiaohui.chapter6;

import com.fantasybaby.xiaohui.chapter2.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过哈希链表 实现LRU算法
 * 2019/12/22
 *
 * @authorfantasybaby
 **/
public class LruCache {
    private Node<Integer> head;
    private Node<Integer> last;
    private int limit;
    /**
     * 维护哈希之间得关系
     */
    private Map<String,Node<Integer>> map;
    public LruCache(int limit){
        this.limit = limit;
        map = new HashMap<>();
    }
    public void put(String key,Integer value){
        Node node = map.get(key);
        if(node == null){
            if(map.size() >= limit){
                String oldHead = removeNode(head);
                map.remove(oldHead);
            }
            node = new Node();
            node.setKey(key);
            node.setData(value);
            addNode(node);
            map.put(key,node);
        }else{
            node.setData(value);
            refreshNode(node);
        }
    }
    public Integer get(String key){
        Node<Integer> node = map.get(key);
        if(node != null){
            refreshNode(node);
            return node.getData();
        }
        return null;
    }
    private String removeNode(Node<Integer> node){
        if(head == node && last == node){
            head = null;
            last = null;
        }else if(node == head){
            head = head.getNext();
            head.setPre(null);
        }else if(node == last){
            last = last.getPre();
            last.setNext(null);
        }else{
            node.getPre().setNext(node.getNext());
            node.getNext().setPre(node.getPre());
        }
        return node.getKey();
    }
    private void addNode(Node<Integer> node){
        if(last != null){
            last.setNext(node);
            node.setPre(last);
            node.setNext(null);
        }
        last = node;
        if(head == null){
            head = node;
        }
    }
    public void remove(String key){
        Node<Integer> integerNode = map.get(key);
        removeNode(integerNode);
        map.remove(key);
    }

    /**
     * 先删除节点 去掉关联关系 在放到链表尾部
     * @param node
     */
    private void refreshNode(Node node){
        if(node == null){
            return;
        }
        removeNode(node);
        addNode(node);
    }

    public static void main(String[] args) {
        LruCache cached = new LruCache(3);
        cached.put("key1",1);
        cached.put("key2",2);
        cached.put("key3",3);
        System.out.println(cached.get("key1"));
        cached.put("key4",4);
        System.out.println(cached.get("key1"));
    }
}
