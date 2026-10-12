class Solution {
    public int[] solution(int brown, int yellow) {
        for (var i = 1; i <= yellow; i++) {
            if (yellow % i != 0) continue;

            var j = yellow / i;
            var sum = (j + 2) * 2 + (i * 2);
            if (sum == brown) {
                return new int[] {j + 2, i + 2};
            }
        }
        return new int[] {-1};
    }
}