class Solution {
    public int solution(int n) {
        var f = 1;
        var base = 1;
        
        while (n >= f) {
            f *= ++base;
        }
        return base - 1;
    }
}