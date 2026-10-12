class Solution {
    private int[] numbers;
    private int res = 0;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        dfs(target, 0);
        return res;
    }

    private void dfs(int target, int depth) {
        if (depth == numbers.length) {
            if (target == 0) ++res;
            return;
        }
        
        dfs(target + numbers[depth], depth + 1);
        dfs(target - numbers[depth], depth + 1);
    }
}