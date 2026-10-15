import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Comparator.comparingInt;

public class Solution {
    public int[] solution(String[] genres, int[] plays) {
        var genrePlayMap = new HashMap<String, Integer>(genres.length);
        var musicMap = new HashMap<String, List<Music>>(genres.length);

        for (var i = 0; i < genres.length; i++) {
            var genre = genres[i];
            var play = plays[i];

            genrePlayMap.put(genre, genrePlayMap.getOrDefault(genre, 0) + play);
            musicMap.computeIfAbsent(genre, s -> new ArrayList<>()).add(new Music(i, play));
        }

        var sortedGenrePlays = new ArrayList<>(genrePlayMap.keySet());
        sortedGenrePlays.sort(comparingInt(o -> -genrePlayMap.getOrDefault(o, 0)));

        var res = new ArrayList<Integer>();
        for (var genre : sortedGenrePlays) {
            var musics = musicMap.get(genre).stream()
                    .sorted(comparingInt((Music m) -> -m.play).thenComparingInt(m -> m.id))
                    .toList();
            var count = Math.min(musics.size(), 2);

            for (var i = 0; i < count; i++) {
                res.add(musics.get(i).id);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    private static class Music {
        int id;
        int play;

        public Music(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }
}
