class Solution {
    public int[][] solution(int[][] arr) {
        var size = Math.max(arr.length, arr[0].length);
        var res = new int[size][size];
        
        for (var i = 0; i < arr.length; i++) {
            System.arraycopy(arr[i], 0, res[i], 0, arr[i].length);
        }
        return res;
    }
}