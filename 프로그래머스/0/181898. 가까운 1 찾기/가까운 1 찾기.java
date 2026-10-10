class Solution {
    public int solution(int[] arr, int idx) {
        for (var i = idx; i < arr.length; i++) {
            if (arr[i] == 1) return i;
        }
        return -1;
    }
}