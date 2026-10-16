import java.util.Arrays;

class Solution {
    public String solution(String m, String[] musicinfos) {
        var targetSheet = replaceSheet(m);
        var musics = Arrays.stream(musicinfos).map(Music::new).toList();
        var title = "";
        var maxPlayTime = -1;

        for (var music : musics) {
            if (music.playSheet.contains(targetSheet)) {
                if (music.playTime > maxPlayTime) {
                    maxPlayTime = music.playTime;
                    title = music.title;
                }
            }
        }
        return maxPlayTime == -1 ? "(None)" : title;
    }

    private static String replaceSheet(String sheet) {
        var sb = new StringBuilder(sheet.length());
        for (var i = 0; i < sheet.length(); i++) {
            var c = sheet.charAt(i);
            if (c == '#') {
                sb.setCharAt(sb.length() - 1, Character.toLowerCase(sheet.charAt(i - 1)));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private static class Music {
        String title;
        int playTime;
        String originSheet;
        String playSheet;

        public Music(String info) {
            var tokens = info.split(",");
            var startTime = toMinute(tokens[0]);
            var endTime = toMinute(tokens[1]);

            this.title = tokens[2];
            this.playTime = endTime - startTime;
            this.originSheet = replaceSheet(tokens[3]);

            var sb = new StringBuilder(playTime);
            sb.append(this.originSheet, 0, Math.min(originSheet.length(), playTime));
            for (var i = originSheet.length(); i < playTime; i++) {
                sb.append(originSheet.charAt(i % originSheet.length()));
            }
            this.playSheet = sb.toString();
        }

        private int toMinute(String time) {
            var h = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            var m = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            return h * 60 + m;
        }
    }
}