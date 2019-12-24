package com.fantasybaby.xiaohui.chapter2;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 线性链表的Node 存在两个域  数据域 和指针域
 * @author Reid.Liu
 *
 * @param <T>
 */
public class Node<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4339114890944879710L;
	private T data;
	@Getter
	@Setter
	private String key;
	private Node<T> next;
	@Getter
	@Setter
	private Node<T> pre;
	public Node() {
	}
	public Node(T data) {
		this.data = data;
	}
	
	public Node(T data, Node<T> next) {
		super();
		this.data = data;
		this.next = next;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
}
