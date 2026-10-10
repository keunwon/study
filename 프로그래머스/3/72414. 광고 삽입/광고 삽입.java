class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        var playSec = toSecond(play_time);
        var advSec = toSecond(adv_time);
        var prefixSum = new long[playSec + 1];

        for (var log : logs) {
            var startSec = toSecond(log.substring(0, 8));
            var endSec = toSecond(log.substring(9, 17));
            ++prefixSum[startSec];
            --prefixSum[endSec];
        }

        for (var i = 0; i < 2; i++) {
            for (var j = 1; j <= playSec; j++) {
                prefixSum[j] += prefixSum[j - 1];
            }
        }

        var start = 0;
        var maxPlay = prefixSum[advSec - 1];

        for (var i = advSec; i <= playSec; i++) {
            var time = prefixSum[i] - prefixSum[i - advSec];
            if (time > maxPlay) {
                maxPlay = time;
                start = i - advSec + 1;
            }
        }
        return String.format("%02d:%02d:%02d", start / 3600, start % 3600 / 60, start % 60);
    }

    private int toSecond(String time) {
        var hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
        var minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
        var second = (time.charAt(6) - '0') * 10 + (time.charAt(7) - '0');
        return hour * 3600 + minute * 60 + second;
    }
}