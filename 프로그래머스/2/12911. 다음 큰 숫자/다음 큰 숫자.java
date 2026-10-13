class Solution {
    public int solution(int n) {
        var oneCount = Integer.bitCount(n);
        var x = n + 1;

        while (Integer.bitCount(x) != oneCount) {
            ++x;
        }
        return x;
    }
}