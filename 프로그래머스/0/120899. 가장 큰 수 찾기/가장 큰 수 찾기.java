class Solution {
    public int[] solution(int[] array) {
        var res = new int[] {array[0], 0};
        for (var i = 1; i < array.length; i++) {
            if (array[i] > res[0]) {
                res[0] = array[i];
                res[1] = i;
            }
        }
        return res;
    }
}