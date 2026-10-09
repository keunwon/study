class Solution {
    public int solution(int[] array) {
        var count = new int[1001];
        var max = 0;
        var res = 0;
        
        for (var a : array) {
            ++count[a];
            
            if (count[a] > max) {
                max = count[a];
                res = a;
            } else if (count[a] == max) {
                res = -1;
            }
        }
        return res;
    }
}