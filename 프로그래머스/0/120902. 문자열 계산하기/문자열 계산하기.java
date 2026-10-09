class Solution {
    public int solution(String my_string) {
        var tokens = my_string.split(" ");
        var curSum = Integer.parseInt(tokens[0]);

        for (var i = 1; i < tokens.length; i += 2) {
            var op = tokens[i];
            var x = Integer.parseInt(tokens[i + 1]);

            switch (op) {
                case "+" -> curSum += x;
                case "-" -> curSum -= x;
            }
        }
        return curSum;
    }
}