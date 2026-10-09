class Solution {
    public int solution(String s) {
        var tokens = s.split(" ");
        var n = tokens.length;
        var numbers = new int[n];
        var top = -1;
        var sum = 0;

        for (String token : tokens) {
            if (token.equals("Z")) {
                if (top > -1) sum -= numbers[top--];
            } else {
                var x = Integer.parseInt(token);
                numbers[++top] = x;
                sum += x;
            }
        }
        return sum;
    }
}