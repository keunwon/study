class Solution {
    public int solution(String number) {
        var sum = 0;
        for (var i = 0; i < number.length(); i++) {
            sum += (number.charAt(i) - '0');
        }
        return sum % 9;
    }
}