class Solution {
    public int[] solution(int[] numbers, int num1, int num2) {
        var n = num2 - num1 + 1;
        var res = new int[n];
        
        for (var i = 0; i < n; i++) {
            res[i] = numbers[i + num1];
        }
        return res;
    }
}