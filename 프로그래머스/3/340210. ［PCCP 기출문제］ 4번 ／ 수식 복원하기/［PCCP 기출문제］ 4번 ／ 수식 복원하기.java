import java.util.ArrayList;
import java.util.TreeSet;

class Solution {
    public String[] solution(String[] expressions) {
        var validExpressions = new ArrayList<Expression>();
        var invalidExpressions = new ArrayList<Expression>();
        var maxDigit = 0;

        for (var ex : expressions) {
            var tokens = ex.split(" ");
            var expression = new Expression(tokens[0], tokens[1], tokens[2], tokens[4]);

            maxDigit = Math.max(maxDigit, maxOfDigit(ex));

            if (ex.charAt(ex.length() - 1) == 'X') {
                invalidExpressions.add(expression);
            } else {
                validExpressions.add(expression);
            }
        }

        var bases = new boolean[10];
        for (var base = maxDigit + 1; base < 10; base++) {
            var valid = true;
            for (var ex : validExpressions) {
                var a = Integer.parseInt(ex.a, base);
                var b = Integer.parseInt(ex.b, base);
                var c = Integer.parseInt(ex.c, base);

                if (ex.op.equals("+")) {
                    valid = a + b == c;
                } else if (ex.op.equals("-")) {
                    valid = a - b == c;
                }

                if (!valid) break;
            }

            if (valid) bases[base] = true;
        }

        var res = new String[invalidExpressions.size()];
        for (var i = 0; i < invalidExpressions.size(); i++) {
            var ex = invalidExpressions.get(i);
            var numSet = new TreeSet<String>();

            for (var base = maxDigit + 1; base < 10; base++) {
                if (!bases[base]) continue;

                var a = Integer.parseInt(ex.a, base);
                var b = Integer.parseInt(ex.b, base);
                var c = ex.op.equals("+") ? a + b : a - b;
                numSet.add(Integer.toString(c, base));
            }
            res[i] = String.format("%s %s %s = %s", ex.a, ex.op, ex.b, numSet.size() == 1 ? numSet.getFirst() : "?");
        }
        return res;
    }

    private int maxOfDigit(String s) {
        var maxDigit = 0;
        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i);
            if (Character.isDigit(c)) {
                maxDigit = Math.max(maxDigit, c - '0');
            }
        }
        return maxDigit;
    }

    private static class Expression {
        String a;
        String op;
        String b;
        String c;

        public Expression(String a, String op, String b, String c) {
            this.a = a;
            this.op = op;
            this.b = b;
            this.c = c;
        }
    }
}