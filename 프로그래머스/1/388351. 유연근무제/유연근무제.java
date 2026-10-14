class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        for (var i = 0; i < schedules.length; i++) {
            schedules[i] += 10;
            if (schedules[i] % 100 >= 60) {
                schedules[i] += 100;
                schedules[i] -= 60;
            }
        }

        var res = 0;
        for (var i = 0; i < schedules.length; i++) {
            var schedule = schedules[i];
            var timelog = timelogs[i];
            var valid = true;

            for (var j = 0; j < timelog.length; j++) {
                var day = (j + startday - 1) % 7;
                if (day != 5 && day != 6) {
                    if (timelog[j] > schedule) {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid) ++res;
        }
        return res;
    }
}