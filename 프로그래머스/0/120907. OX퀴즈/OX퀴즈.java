class Solution {
    public String[] solution(String[] quiz) {
        var n = quiz.length;
        var res = new String[n];

        for (var i = 0; i < n; i++) {
            var tokens = quiz[i].split(" ");
            var a = Integer.parseInt(tokens[0]);
            var op = tokens[1];
            var b = Integer.parseInt(tokens[2]);
            var c = Integer.parseInt(tokens[4]);
            var valid = op.equals("+") ? a + b == c : a - b == c;

            res[i] = valid ? "O" : "X";
        }
        return res;
    }
}