class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        var videoLen = toSecond(video_len);
        var posSec = toSecond(pos);
        var opStartSec = toSecond(op_start);
        var opEndSec = toSecond(op_end);

        if (posSec >= opStartSec && posSec <= opEndSec) {
            posSec = opEndSec;
        }

        for (var command : commands) {
            if (command.equals("prev")) {
                posSec = Math.max(posSec - 10, 0);
            } else if (command.equals("next")) {
                posSec = Math.min(posSec + 10, videoLen);
            }

            if (posSec >= opStartSec && posSec <= opEndSec) {
                posSec = opEndSec;
            }
        }
        return String.format("%02d:%02d", posSec / 60, posSec % 60);
    }

    private int toSecond(String time) {
        var minute = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
        var second = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
        return minute * 60 + second;
    }
}