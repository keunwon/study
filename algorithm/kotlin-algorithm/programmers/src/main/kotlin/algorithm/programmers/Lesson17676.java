package algorithm.programmers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Lesson17676 {
    public int solution(String[] lines) throws ParseException {
        var logs = Arrays.stream(lines).map(Log::new).collect(Collectors.toList());
        var max = 0;

        for (var i = 0; i < logs.size(); i++) {
            var target = logs.get(i);
            var tmpCount = 0;

            for (var j = i; j < logs.size(); j++) {
                var next = logs.get(j);

                if (target.endTime + 1000 > next.startTime) {
                    ++tmpCount;
                }
            }
            max = Math.max(max, tmpCount);
        }
        return max;
    }

    private static class Log {
        private final SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss.SSS");

        long startTime;
        long endTime;

        public Log(String line) {
            try {
                var arr = line.split(" ");
                var time = format.parse(arr[1]).getTime();
                var duration = (int) (Double.parseDouble(arr[2].replace("s", "")) * 1000);

                this.endTime = time;
                this.startTime = endTime - duration + 1;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
