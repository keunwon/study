package com.keunwon.codingtest.datastructure;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class DoubleLinkedList<T> {
    public Node<T> head;
    public Node<T> tail;

    public void addNode(T data) {
        if (this.head == null) {
            this.head = new Node<>(data);
            this.tail = this.head;
            return;
        }

        Node<T> node = this.head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node<>(data);
        node.next.prev = node;
        this.tail = node.next;
    }

    public boolean addPreNode(T targetData, T addData) {
        if (this.head == null) {
            this.head = new Node<>(addData);
            this.tail = this.head;
            return true;
        }

        if (this.head.data == targetData) {
            Node<T> newHead = new Node<>(addData);
            newHead.next = this.head;
            this.head = newHead;
            return true;
        }

        Node<T> node = this.head;
        while (node != null) {
            if (node.data == targetData) {
                Node<T> preNode = node.prev;
                preNode.next = new Node<>(addData);
                preNode.next.next = node;

                preNode.next.prev = preNode;
                node.prev = preNode.next;

                return true;
            }
            node = node.next;
        }

        return false;
    }

    public List<T> allData() {
        if (head == null) { return emptyList(); }

        List<T> result = new ArrayList<>();
        Node<T> node = this.head;

        result.add(node.data);
        while (node.next != null) {
            node = node.next;
            result.add(node.data);
        }
        return result;
    }

    public T searchFromHead(T data) {
        if (this.head == null) { return null; }

        Node<T> node = this.head;
        while (node != null) {
            if (node.data == data) {
                return node.data;
            }
            node = node.next;
        }

        return null;
    }

    public T searchFromTail(T data) {
        if (this.head == null) { return null; }

        Node<T> node = this.tail;
        while (node != null) {
            if (node.data == data) {
                return node.data;
            }
            node = node.prev;
        }

        return null;
    }

    private class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}
