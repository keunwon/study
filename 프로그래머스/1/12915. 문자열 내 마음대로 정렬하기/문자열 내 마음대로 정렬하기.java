import java.util.Arrays;
import java.util.Comparator;

import static java.util.Comparator.comparingInt;

class Solution {
    public String[] solution(String[] strings, int n) {
        Arrays.sort(strings, comparingInt((String o) -> o.charAt(n)).thenComparing(o -> o));
        return strings;
    }
}