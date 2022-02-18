package com.keunwon.algorithm.tree;

import java.io.*;

public class Problem1991 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static int n;
    private static Tree tree;

    public static void main(String[] args) throws IOException {
        input();
        tree.preorder(tree.root);
        bw.write(System.lineSeparator());

        tree.inorder(tree.root);
        bw.write(System.lineSeparator());

        tree.postorder(tree.root);
        bw.write(System.lineSeparator());

        bw.flush();
        bw.close();
        br.close();
    }

    private static void input() throws IOException {
        n = Integer.parseInt(br.readLine());
        tree = new Tree();

        for (int i = 0; i < n; i++) {
            char[] arr = br.readLine().replaceAll(" ", "").toCharArray();
            tree.add(arr[0], arr[1], arr[2]);
        }
    }

    private static class Node {
        char data;
        Node left;
        Node right;

        Node(char data) {
            this.data = data;
        }
    }

    private static class Tree {
        Node root;

        public void add(char data, char left, char right) {
            if (root == null) {
                root = new Node(data);

                if (left != '.') {
                    root.left = new Node(left);
                }
                if (right != '.') {
                    root.right = new Node(right);
                }
                return;
            }

            search(root, data, left, right);
        }

        public void search(Node root, char data, char left, char right) {
            if (root == null) {
                return;
            }

            if (root.data == data) {
                if (left != '.') {
                    root.left = new Node(left);
                }
                if (right != '.') {
                    root.right = new Node(right);
                }
                return;
            }

            search(root.left, data, left, right);
            search(root.right, data, left, right);
        }

        public void preorder(Node root) throws IOException {
            bw.write(root.data);

            if (root.left != null) { preorder(root.left); }
            if (root.right != null) { preorder(root.right); }
        }

        public void inorder(Node root) throws IOException {
            if (root.left != null) { inorder(root.left); }
            bw.write(root.data);
            if (root.right != null) { inorder(root.right); }
        }

        public void postorder(Node root) throws IOException {
            if (root.left != null) { postorder(root.left); }
            if (root.right != null) { postorder(root.right); }
            bw.write(root.data);
        }
    }
}
