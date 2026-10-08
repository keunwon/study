class Solution {
    public int[] solution(String[] strlist) {
        var n = strlist.length;
        var res = new int[n];
        
        for (var i = 0; i < n; i++) {
            res[i] = strlist[i].length();
        }
        return res;
    }
}