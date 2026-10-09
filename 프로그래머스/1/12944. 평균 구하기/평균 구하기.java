class Solution {
    public double solution(int[] arr) {
        var total = 0;
        for (var num : arr) total += num;
        return (double) total / arr.length;
    }
}