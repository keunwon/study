class Solution {
    public int solution(String binomial) {
        var tokens = binomial.split(" ");
        var a = Integer.parseInt(tokens[0]);
        var op = tokens[1];
        var b = Integer.parseInt(tokens[2]);

        return switch (op) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            default -> -1;
        };
    }
}