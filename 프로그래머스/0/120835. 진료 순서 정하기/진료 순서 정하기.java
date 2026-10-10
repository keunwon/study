class Solution {
    public int[] solution(int[] emergency) {
        var n = emergency.length;
        var order = new int[n];

        for (var i = 0; i < n; i++) {
            for (var j = 0; j < n; j++) {
                if (emergency[i] <= emergency[j]) {
                    ++order[i];
                }
            }
        }
        return order;
    }
}