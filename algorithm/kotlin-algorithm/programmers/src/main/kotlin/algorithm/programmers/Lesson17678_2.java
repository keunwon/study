package algorithm.programmers;

import java.util.ArrayList;

public class Lesson17678_2 {
    public String solution(int n, int t, int m, String[] timetable) {
        var minutes = new int[timetable.length];
        var mIndex = 0;
        var lastMinute = 0;
        var count = 0;
        var busMinute = toMinute("09:00");

        for (var i = 0; i < minutes.length; i++) {
            minutes[i] = toMinute(timetable[i]);
        }
        Arrays.sort(minutes);

        for (var i = 0; i < n; i++) {
            count = 0;

            while (mIndex < minutes.length && minutes[mIndex] <= busMinute && count < m) {
                lastMinute = minutes[mIndex++];
                ++count;
            }

            if (i == n - 1) {
                if (count == m) busMinute = lastMinute - 1;
            } else {
                busMinute += t;
            }
        }
        return String.format("%02d:%02d", busMinute / 60, busMinute % 60);
    }

    private int toMinute(String time) {
        var separatorIndex = time.indexOf(':');
        var hour = Integer.parseInt(time.substring(0, separatorIndex));
        var minute = Integer.parseInt(time.substring(separatorIndex + 1));
        return hour * 60 + minute;
    }
}
