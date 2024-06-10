package com.keunwon.algorithm.programmers;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Lesson17683 {
    private final Map<String, String> replaceMap = Map.of(
            "C#", "c",
            "D#", "d",
            "F#", "f",
            "G#", "g",
            "A#", "a",
            "B#", "b"
    );

    public String solution(String m, String[] musicinfos) {
        var musics = Arrays.stream(musicinfos)
                .map(str -> new Music(replaceMusicSheet(str)))
                .filter(music -> music.match(replaceMusicSheet(m)))
                .sorted()
                .collect(Collectors.toList());

        if (musics.isEmpty()) return "(None)";
        return musics.get(0).name;
    }

    private String replaceMusicSheet(String sheet) {
        var s = sheet;
        for (var key : replaceMap.keySet()) {
            s = s.replace(key, replaceMap.get(key));
        }
        return s;
    }

    private static class Music implements Comparable<Music> {
        String name;
        int duration;
        String sheet;
        StringBuilder runningSheet = new StringBuilder();

        public Music(String text) {
            var arr = text.split(",");
            this.name = arr[2];
            this.duration = toMinutes(arr[1]) - toMinutes(arr[0]);
            this.sheet = arr[3];

            for (int i = 0; i < duration; i++) {
                this.runningSheet.append(sheet.charAt(i % sheet.length()));
            }
        }

        public boolean match(String target) {
            return runningSheet.toString().contains(target);
        }

        private int toMinutes(String time) {
            var arr = time.split(":");
            return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
        }

        @Override
        public int compareTo(@NotNull Music o) {
            if (duration != o.duration) return o.duration - duration;
            else return 0;
        }
    }
}
