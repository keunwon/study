class Solution {
    public String solution(String my_string) {
        var uniques = new boolean[128];
        var sb = new StringBuilder(my_string.length());

        for (var i = 0; i < my_string.length(); i++) {
            var c = my_string.charAt(i);
            if (!uniques[c]) {
                sb.append(c);
                uniques[c] = true;
            }
        }
        return sb.toString();
    }
}