class Solution {
    public int solution(String s) {
        var n = s.length();
        var stk = new char[n];
        var res = 0;

        for (var i = 0; i < s.length(); i++) {
            var top = -1;

            for (var j = i; j < i + n; j++) {
                var c = s.charAt(j % n);
                if (c == '(') {
                    stk[++top] = ')';
                } else if (c == '[') {
                    stk[++top] = ']';
                } else if (c == '{') {
                    stk[++top] = '}';
                } else {
                    if (top == -1 || stk[top] != c) {
                        top = 0;
                        break;
                    }
                    --top;
                }
            }

            if (top == -1) ++res;
        }
        return res;
    }
}