import java.util.LinkedHashSet;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;

        var duration = 0;
        var cache = new LinkedHashSet<String>(cacheSize, 1);

        for (var c : cities) {
            var key = c.toLowerCase();

            if (cache.contains(key)) {
                cache.remove(key);
                cache.add(key);
                ++duration;
            } else {
                if (cache.size() == cacheSize) {
                    cache.removeFirst();
                }
                cache.add(key);
                duration += 5;
            }
        }
        return duration;
    }
}