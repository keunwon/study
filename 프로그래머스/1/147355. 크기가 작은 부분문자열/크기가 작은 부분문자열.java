class Solution {
    public int solution(String t, String p) {
        var count = 0;
        for (var i = 0; i <= t.length() - p.length(); i++) {
            var compare = 0;
            for (var j = 0; j < p.length(); j++) {
                compare = Character.compare(t.charAt(i + j), p.charAt(j));
                if (compare != 0) break;
            }
            if (compare < 1) ++count;
        }
        return count;
    }
}