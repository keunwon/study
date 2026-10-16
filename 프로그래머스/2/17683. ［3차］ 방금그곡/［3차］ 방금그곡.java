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
        return sheet.replace("C#", "c")
                .replace("D#", "d")
                .replace("E#", "e")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a")
                .replace("B#", "b");
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