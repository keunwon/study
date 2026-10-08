class Solution {
    public String[] solution(String[] names) {
        var n = (names.length - 1) / 5 + 1;
        var res = new String[n];
        
        for (var i = 0; i < n; i++) {
            res[i] = names[i * 5];
        }
        return res;
    }
}