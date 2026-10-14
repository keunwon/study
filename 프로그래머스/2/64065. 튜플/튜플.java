import java.util.Arrays;
import java.util.LinkedHashSet;

import static java.util.Comparator.comparingInt;

class Solution {
    public int[] solution(String s) {
        var strs = s.substring(2, s.length() - 2).split("},\\{");
        Arrays.sort(strs, comparingInt(String::length));

        var res = new LinkedHashSet<String>();
        for (var str : strs) {
            var tokens = str.split(",");
            res.addAll(Arrays.asList(tokens));
        }
        return res.stream().mapToInt(Integer::parseInt).toArray();
    }
}