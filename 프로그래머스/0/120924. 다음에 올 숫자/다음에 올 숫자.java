class Solution {
    public int solution(int[] common) {
        var n = common.length;
        var diff1 = common[1] - common[0];
        var diff2 = common[n - 1] - common[n - 2];
        
        return diff1 == diff2 
            ?  common[n - 1] + diff1 
            : common[n - 1] * (common[1] / common[0]);
    }
}