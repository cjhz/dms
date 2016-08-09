package com.sunraysoft.hr.domain.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Node<T> {
	private T data;
    private Node<T> parent;
    private List<Node<T>> children = new ArrayList<Node<T>>();

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> parent) {//parent父目录，data要添加的数值
        this.data = data;
        this.parent = parent;

        parent.addChild(this);
    }
    
    public Node(T data, Node<T> parent, Comparator<T> comparator) {
        this.data = data;
        this.parent = parent;

        parent.addChild(this, comparator);
    }    

    public void addChild(Node<T> node) {
        if(node != null) {
            node.parent = this;
            children.add(node);  //将次数值设置为子目录
        }
    }
    
    public void addChild(Node<T> node, Comparator<T> comparator) {
    	if(node != null) {
            node.parent = this;
            
            boolean insert = false;
            for(int i = 0; i < children.size(); i++) {
            	if(comparator.compare(node.getData(), children.get(i).getData()) == -1) {
            		children.add(i, node);
            		insert = true;
            		break;
            	}
            }
            
            if(!insert) {
            	children.add(node);
            }
        }
    }

    public void remove(Node<T> node) {
        if(node != null && node.parent != null && node.parent.getChildren() != null) {
            node.parent.getChildren().remove(node);
            node.parent = null;
        }
    }

    public boolean isLeaf() {
        return children == null || children.isEmpty();//没有子目录
    }

    public boolean hasChild() {
        return !isLeaf();
    }

    public boolean isLast() {
        if(parent == null) {
            return true; //root node
        } else {
            if(parent.getChildren() == null || parent.getChildren().isEmpty()) {
                throw new IllegalArgumentException("错误的树结构");
            }
            
            int index = parent.getChildren().indexOf(this);//indexof 返回列表中首次出现指定元素的索引，也就是第一级子目录
            if(index == -1) {
                throw new IllegalArgumentException("错误的树结构");
            } else {
                return index == (parent.getChildren().size() - 1);
            }
        }
    }
    
    public boolean containsTheChild(T data) {
    	return getTheChild(data) != null;
    }
    
    public Node<T> getTheChild(T data) {
    	Node<T> child = null;
    	if(hasChild()) {
    		for(Node<T> node : children) {
    			if(data.equals(node.getData())) {
    				child = node;
    				break;
    			}
    		}
    	}
    	
    	return child;
    }
    
    /**判断是否是所有的兄弟节点中的第一个*/
    public boolean isFirst() {
    	Node<T> p = getParent();
    	if(p == null || (p.getChildren() != null && p.getChildren().size() == 1)) {
    		return true;
    	} else {
    		return p.getChildren().indexOf(this) == 0;
    	}
    }

    public T getData() {
        return data;
    }
    
    public void setData(T data) {
		this.data = data;
	}

	public void setParent(Node<T> parent) {
        this.parent = parent;
    }

    public Node<T> getParent() {
        return parent;
    }

    public List<Node<T>> getChildren() {
        return children;
    }
}
