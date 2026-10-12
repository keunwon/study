class Solution {
    public String solution(int n) {
        var sb = new StringBuilder();
        while (n > 0) {
            var x = switch (n % 3) {
                case 1 -> 1;
                case 2 -> 2;
                case 0 -> {
                    --n;
                    yield 4;
                }
                default -> -1;
            };
            
            sb.append(x);
            n /= 3;
        }
        return sb.reverse().toString();
    }
}