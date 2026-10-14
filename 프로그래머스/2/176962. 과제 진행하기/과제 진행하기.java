import java.util.Arrays;
import java.util.Stack;

class Solution {
    public String[] solution(String[][] plans) {
        var studies = Arrays.stream(plans)
                .map(p -> new Study(p[0], toMinute(p[1]), Integer.parseInt(p[2])))
                .sorted()
                .toList();
        var stk = new Stack<Study>();
        var res = new String[plans.length];
        var rIdx = 0;

        for (var study : studies) {
            if (stk.isEmpty()) {
                stk.push(study);
                continue;
            }

            if (stk.peek().endTime() <= study.startTime) {
                var prev = stk.pop();
                res[rIdx++] = prev.subject;

                var ret = study.startTime - prev.endTime();
                while (!stk.isEmpty() && ret > 0) {
                    var playTime = stk.peek().playTime;
                    stk.peek().playTime -= ret;
                    ret -= playTime;

                    if (stk.peek().playTime <= 0) {
                        res[rIdx++] = stk.pop().subject;
                    }
                }
            } else {
                stk.peek().playTime = stk.peek().endTime() - study.startTime;
            }
            stk.push(study);
        }

        while (!stk.isEmpty()) {
            res[rIdx++] = stk.pop().subject;
        }
        return res;
    }

    private int toMinute(String time) {
        var h = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
        var m = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
        return h * 60 + m;
    }

    private static class Study implements Comparable<Study> {
        final String subject;
        final int startTime;
        int playTime;

        public Study(String subject, int startTime, int playTime) {
            this.subject = subject;
            this.startTime = startTime;
            this.playTime = playTime;
        }

        public int endTime() {
            return startTime + playTime;
        }

        public String getSubject() {
            return subject;
        }

        @Override
        public int compareTo(Study o) {
            return startTime - o.startTime;
        }
    }
}