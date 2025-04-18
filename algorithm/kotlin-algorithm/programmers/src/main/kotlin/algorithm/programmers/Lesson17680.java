package algorithm.programmers;

import java.util.LinkedHashSet;

public class Lesson17680 {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;

        var cache = new LinkedHashSet<String>();
        var answer = 0;

        for (int i = 0; i < cities.length; i++) {
            var city = cities[i].toUpperCase();

            if (cache.contains(city)) {
                cache.remove(city);
                cache.add(city);
                ++answer;
            } else {
                if (cache.size() == cacheSize) cache.remove(cache.getFirst());

                answer += 5;
                cache.add(city);
            }
        }
        return answer;
    }
}
