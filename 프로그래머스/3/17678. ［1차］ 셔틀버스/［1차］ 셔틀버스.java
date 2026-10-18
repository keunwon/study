import java.util.Arrays;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        var minutes = new int[timetable.length];
        for (var i = 0; i < timetable.length; i++) {
            minutes[i] = toMinute(timetable[i]);
        }
        Arrays.sort(minutes);

        var targetMinute = toMinute("09:00");
        var mIdx = 0; // minutes index
        var count = 0;
        var lastMinute = 0;

        for (var i = 0; i < n; i++) {
            count = 0;

            while (mIdx < minutes.length && minutes[mIdx] <= targetMinute && count < m) {
                lastMinute = minutes[mIdx++];
                ++count;
            }

            if (i == n - 1) {
                if (count == m) targetMinute = lastMinute - 1;
            } else {
                targetMinute += t;
            }
        }
        return String.format("%02d:%02d", targetMinute / 60, targetMinute % 60);
    }

    private int toMinute(String time) {
        var hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
        var minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
        return hour * 60 + minute;
    }
}