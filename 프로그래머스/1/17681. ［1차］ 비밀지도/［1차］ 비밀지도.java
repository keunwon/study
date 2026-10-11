class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        var res = new String[n];
        var chars = new char[n];

        for (var i = 0; i < n; i++) {
            var x = arr1[i] | arr2[i];
            for (var j = 0; j < n; j++) {
                var k = n - j - 1;
                chars[j] = ((x >> k & 1) == 1) ? '#' : ' ';
            }
            res[i] = new String(chars);
        }
        return res;
    }
}