import java.util.Arrays;

class Solution {
    public String[] solution(String[] files) {
        Arrays.sort(files, (o1, o2) -> {
            var node1 = new Node(o1.toLowerCase());
            var node2 = new Node(o2.toLowerCase());

            var headCompare = node1.head.compareTo(node2.head);
            if (headCompare != 0) {
                return headCompare;
            }
            return Integer.compare(node1.number, node2.number);
        });
        
        return files;
    }

    private static class Node {
        String head;
        int number;

        public Node(String str) {
            var idx = 0;

            while (idx < str.length() && !Character.isDigit(str.charAt(idx))) {
                ++idx;
            }
            this.head = str.substring(0, idx);

            var sIdx = idx;
            while (idx < str.length() && Character.isDigit(str.charAt(idx))) {
                ++idx;
            }
            this.number = Integer.parseInt(str.substring(sIdx, idx));
        }
    }
}