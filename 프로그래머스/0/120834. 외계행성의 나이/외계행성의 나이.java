class Solution {
    public String solution(int age) {
        var n = (int) Math.log10(age) + 1;
        var arr = new char[n];
        var aIdx = n - 1;

        while (aIdx >= 0) {
            var pop = age % 10;
            arr[aIdx--] = (char) ('a' + pop);
            age /= 10;
        }
        return new String(arr);
    }
}