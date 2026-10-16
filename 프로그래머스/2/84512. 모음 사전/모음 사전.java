class Solution {
    public int solution(String word) {
        var res = word.length();
        var weights = new int[] {781, 156, 31, 6, 1};
        var vowels = "AEIOU";

        for (var i = 0; i < word.length(); i++) {
            var c = word.charAt(i);
            res += weights[i] * vowels.indexOf(c);
        }
        return res;
    }
}