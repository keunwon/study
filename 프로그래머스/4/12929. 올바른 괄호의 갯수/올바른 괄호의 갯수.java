class Solution {
    private int res = 0;

    public int solution(int n) {
        dfs(n, 0, 0);
        return res;
    }

    private void dfs(int n, int left, int right) {
        if (left == n && right == n) {
            ++res;
            return;
        }

        if (n > left) dfs(n, left + 1, right);
        if (left > right) dfs(n, left, right + 1);
    }
}