package com.keunwon.algorithm.programmers;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class Lesson17676_2 {
    public int solution(String[] lines) {
        var logs = Arrays.stream(lines).map(Log::new).toList();
        var max = 0;

        for (var i = 0; i < logs.size(); i++) {
            var target = logs.get(i);
            var count = 0;

            for (var j = i; j < logs.size(); j++) {
                if (target.contains(logs.get(j))) {
                    ++count;
                }
            }
            max = Math.max(max, count);
        }
        return max;
    }

    private static class Log {
        static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        static final long NANO_PER_SECOND = ChronoUnit.SECONDS.getDuration().toNanos();
        static final long PREFIX_NANO = (long) (0.001 * NANO_PER_SECOND);

        long startTime;
        long endTime;

        public Log(String log) {
            var times = log.split(" ");
            var t = Double.parseDouble(times[2].replace("s", ""));

            endTime = LocalTime.parse(times[1], DATE_FORMAT).toNanoOfDay();
            startTime = endTime - (long) (t * NANO_PER_SECOND - PREFIX_NANO);
        }

        public boolean contains(Log other) {
            return endTime + NANO_PER_SECOND > other.startTime;
        }
    }

    public static void main(String[] args) {
        var lines = new String[]{
                "2016-09-15 01:00:04.002 2.0s",
                "2016-09-15 01:00:07.000 2s"
        };
        var result = new Lesson17676_2().solution(lines);
        System.out.println(result);
    }
}
