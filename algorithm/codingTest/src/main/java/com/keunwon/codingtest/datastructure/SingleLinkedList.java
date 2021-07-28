package com.keunwon.codingtest.datastructure;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;

public class SingleLinkedList<T> {
    public Node<T> head;

    public void addNode(T data) {
        if (head == null) {
            head = new Node<>(data);
            return;
        }

        Node<T> node = head;
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node<>(data);
    }

    public void addNode(T targetData, T addData) {
        Node<T> searchNode = search(targetData);

        if (searchNode == null) {
            this.addNode(addData);
            return;
        }

        Node<T> nextNode = searchNode.next;
        searchNode.next = new Node<>(addData);
        searchNode.next.next = nextNode;
    }

    public boolean remove(T data) {
        if (this.head == null) { return false; }

        Node<T> node = this.head;
        if (node.data == data) {
            this.head = this.head.next;
            return true;
        }

        while (node.next != null) {
            if (node.next.data == data) {
                node.next = node.next.next;
                return true;
            }

            node = node.next;
        }

        return false;
    }

    public Node<T> search(T data) {
        if (this.head == null) { return null; }

        Node<T> node = this.head;
        while (node != null) {
            if (node.data == data) {
                return node;
            }
            node = node.next;
        }

        return null;
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

    public class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
}
