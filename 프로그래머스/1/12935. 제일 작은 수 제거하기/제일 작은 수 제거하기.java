class Solution {
    public int[] solution(int[] arr) {
        if (arr.length == 1) {
            return new int[] {-1};
        }

        var n = arr.length;
        var min = arr[0];

        for (var i = 1; i < n; i++) {
            min = Math.min(min, arr[i]);
        }

        var res = new int[n - 1];
        var rIdx = -1;
        for (int num : arr) {
            if (num != min) {
                res[++rIdx] = num;
            }
        }
        return res;
    }
}