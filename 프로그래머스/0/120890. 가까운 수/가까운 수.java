class Solution {
    public int solution(int[] array, int n) {
        var diff = Integer.MAX_VALUE;
        var x = 0;
        
        for (var num : array) {
            var tmp = Math.abs(n - num);
            
            if (diff > tmp) {
                diff = tmp;
                x = num;
            } else if (diff == tmp && x > num) {
                x = num;
            }
        }
        return x;
    }
}