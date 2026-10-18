import java.util.ArrayList;
import java.util.StringTokenizer;

class Solution {
    private int[] parent;
    private String[] words;

    public String[] solution(String[] commands) {
        this.parent = new int[2501];
        this.words = new String[2501];
        var res = new ArrayList<String>();

        for (var i = 0; i < 2501; i++) {
            parent[i] = i;
            words[i] = "";
        }

        for (var command : commands) {
            var st = new StringTokenizer(command);
            switch (st.nextToken()) {
                case "UPDATE" -> {
                    if (st.countTokens() == 2) {
                        var oldWord = st.nextToken();
                        var newWord = st.nextToken();

                        for (var i = 0; i < words.length; i++) {
                            if (words[i].equals(oldWord)) {
                                words[i] = newWord;
                            }
                        }
                    } else if (st.countTokens() == 3) {
                        var r = Integer.parseInt(st.nextToken());
                        var c = Integer.parseInt(st.nextToken());
                        var word = st.nextToken();
                        var idx = getIndex(r, c);
                        var pIdx = find(idx);

                        words[pIdx] = word;
                    }
                }

                case "MERGE" -> {
                    var r1 = Integer.parseInt(st.nextToken());
                    var c1 = Integer.parseInt(st.nextToken());
                    var r2 = Integer.parseInt(st.nextToken());
                    var c2 = Integer.parseInt(st.nextToken());

                    var idx1 = getIndex(r1, c1);
                    var idx2 = getIndex(r2, c2);
                    var find1 = find(idx1);
                    var find2 = find(idx2);

                    if (find1 != find2) {
                        var w = words[find1].isBlank() ? words[find2] : words[find1];
                        words[find1] = "";
                        words[find2] = "";
                        union(idx1, idx2);
                        words[find(idx1)] = w;
                    }
                }

                case "UNMERGE" -> {
                    var r = Integer.parseInt(st.nextToken());
                    var c = Integer.parseInt(st.nextToken());
                    var idx = getIndex(r, c);
                    var findIdx = find(idx);
                    var w = words[findIdx];

                    words[findIdx] = "";

                    var list = new ArrayList<Integer>();
                    for (var i = 0; i < parent.length; i++) {
                        if (find(i) == findIdx) {
                            list.add(i);
                        }
                    }

                    for (var i : list) {
                        parent[i] = i;
                    }
                    words[idx] = w;
                }

                case "PRINT" -> {
                    var r = Integer.parseInt(st.nextToken());
                    var c = Integer.parseInt(st.nextToken());
                    var idx = getIndex(r, c);
                    var findIdx = find(idx);
                    var w = words[findIdx];

                    res.add(w.isBlank() ? "EMPTY" : w);
                }
            }
        }
        return res.toArray(String[]::new);
    }

    private void union(int a, int b) {
        var findA = find(a);
        var findB = find(b);

        if (findA <= findB) parent[findB] = findA;
        else parent[findA] = findB;
    }

    private int find(int n) {
        if (parent[n] == n) return n;

        var findN = find(parent[n]);
        parent[n] = findN;
        return findN;
    }

    private int getIndex(int r, int c) {
        return (r - 1) * 50 + c;
    }
}