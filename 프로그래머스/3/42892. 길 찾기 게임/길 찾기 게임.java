import java.util.Arrays;

class Solution {
    private int[] preorderArr;
    private int[] postorderArr;
    private int preIdx;
    private int postIdx;

    public int[][] solution(int[][] nodeinfo) {
        this.preorderArr = new int[nodeinfo.length];
        this.postorderArr = new int[nodeinfo.length];
        this.preIdx = 0;
        this.postIdx = 0;

        var nodes = new Node[nodeinfo.length];
        for (var i = 0; i < nodeinfo.length; i++) {
            var info = nodeinfo[i];
            nodes[i] = new Node(i + 1, info[0], info[1]);
        }

        Arrays.sort(nodes);

        var rootNode = nodes[0];
        for (var i = 1; i < nodes.length; i++) {
            insert(rootNode, nodes[i]);
        }

        preorder(rootNode);
        postorder(rootNode);

        return new int[][] {preorderArr, postorderArr};
    }

    private void postorder(Node cur) {
        if (cur == null) return;

        postorder(cur.left);
        postorder(cur.right);
        postorderArr[postIdx++] = cur.id;
    }

    private void preorder(Node cur) {
        if (cur == null) return;

        preorderArr[preIdx++] = cur.id;
        preorder(cur.left);
        preorder(cur.right);
    }

    private void insert(Node root, Node child) {
        if (child.x < root.x) {
            if (root.left == null) root.left = child;
            else insert(root.left, child);
        } else {
            if (root.right == null) root.right = child;
            else insert(root.right, child);
        }
    }

    private static class Node implements Comparable<Node> {
        int id;
        int x;
        int y;
        Node left = null;
        Node right = null;

        public Node(int id, int x, int y) {
            this.id = id;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Node o) {
            if (y != o.y) return o.y - y;
            return x - o.x;
        }
    }
}