package com.developer.list;

public class LinkedList {
	
	private Node first;
	private Node last;
	private int size;
	
	//struct
	public LinkedList() {
		first = null;
		last = null;
		size = 0;
	}
	
	public void push(int data) {
		Node temp = new Node(data);
		
		if(first==null)
			first = temp;
		else
			last.setNext(temp);
		
		temp.setPrev(last);
		last = temp;
		size++;
	}
	
	public void display() {
		if (first != null) {
			Node temp = first;
			while (temp != null) {
				System.out.print(temp.getData()+" ");
				temp = temp.getNext();
			}
			System.out.println();
			
			temp = last;
			while (temp != null) {
				System.out.print(temp.getData()+" ");
				temp = temp.getPrev();
			}
			System.out.println();
			System.out.println("SIZE: "+size);
		}
		else
		{
			System.out.println("List is empty.");
		}
	}
	
	public void pop() {
		if(size >= 2) {
			last = last.getPrev();
			last.setNext(null);
			size--;
		} else if (size == 1) {
			first = null;
			last = null;
			size--;
		}
	}
	
	public Node find(int data) {
		if(first!=null) {
			Node current = first;
			while(current != null && current.getData() != data) {
				current = current.getNext();
			}
			if (current == null) {
				System.out.println("Not found");
				return null;
			}
			else {
				System.out.println(current);
				return current;
			}
		}
		return null;
	}
	
//	public void swap(Node node1, Node node2) {
//		Node prev1 = node1.getPrev();
//		Node next1 = node1.getNext();
//		node1.setNext(node2.getNext());
//		node1.setPrev(node2.getPrev());
//		node2.setNext(next1);
//		node2.setPrev(prev1);
//	}
}
