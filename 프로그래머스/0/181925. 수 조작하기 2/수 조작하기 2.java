class Solution {
    public String solution(int[] numLog) {
        var sb = new StringBuilder(numLog.length);
        for (var i = 1; i < numLog.length; i++) {
            var diff = numLog[i] - numLog[i - 1];
            switch (diff) {
                case 1 -> sb.append('w');
                case -1 -> sb.append('s');
                case 10 -> sb.append('d');
                case -10 -> sb.append('a');
            }
        }
        return sb.toString();
    }
}