class Solution {
    public String[] solution(String[] todo_list, boolean[] finished) {
        var n = 0;
        for (var finish : finished) {
            if (!finish) ++n;
        }

        var res = new String[n];
        var rIdx = -1;

        for (var i = 0; i < finished.length; i++) {
            if (!finished[i]) {
                res[++rIdx] = todo_list[i];
            }
        }
        return res;
    }
}