import java.util.Arrays;
import java.util.Comparator;

public class Solution {
    public int solution(int[] food_times, long k) {
        var len = food_times.length;
        var foods = new Food[len];

        for (var i = 0; i < len; i++) {
            foods[i] = new Food(i + 1, food_times[i]);
        }
        Arrays.sort(foods);

        for (var i = 0; i < len; i++) {
            if (i > 0 && foods[i] == foods[i - 1]) {
                continue;
            }

            var food = foods[i];
            var diffTime = food.time - (i == 0 ? 0 : foods[i - 1].time);
            var size = len - i;
            var totalTime = diffTime * size;

            if (k >= totalTime) {
                k -= totalTime;
            } else {
                var idx = (int) (k % size);
                var arr = Arrays.copyOfRange(foods, i, len);
                Arrays.sort(arr, Comparator.comparingInt(o -> o.idx));
                return arr[idx].idx;
            }
        }
        return -1;
    }

    private record Food(int idx, long time) implements Comparable<Food> {
        @Override
        public int compareTo(Food o) {
            if (time != o.time) return Long.compare(time, o.time);
            return Integer.compare(idx, o.idx);
        }
    }
}
