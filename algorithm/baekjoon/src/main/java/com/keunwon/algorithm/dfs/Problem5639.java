package com.keunwon.algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem5639 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node rootNode = new Node(Integer.parseInt(br.readLine()));

        String w;
        while ((w = br.readLine()) != null && w.length() != 0) {
            int value = Integer.parseInt(w);
            insertNode(rootNode, value);
        }

        postOrder(rootNode);
    }

    static Node insertNode(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        Node currentNode;
        if (node.data > data) {
            currentNode = insertNode(node.left, data);
            node.left = currentNode;
        } else {
            currentNode = insertNode(node.right, data);
            node.right = currentNode;
        }
        return node;
    }

    static void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.data);
        }
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
