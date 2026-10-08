class Solution {
    public int[] solution(int[] arr, int n) {
        var startIdx = arr.length % 2 == 0 ? 1 : 0;
        for (var i = startIdx; i < arr.length; i += 2) {
            arr[i] += n;
        }
        return arr;
    }
}