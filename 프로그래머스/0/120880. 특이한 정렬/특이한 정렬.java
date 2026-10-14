import java.util.ArrayList;
import java.util.Comparator;

class Solution {
    public int[] solution(int[] numlist, int n) {
        var list = new ArrayList<Integer>(numlist.length);
        for (var num : numlist) {
            list.add(num);
        }

        list.sort(Comparator.comparingInt((Integer o) -> Math.abs(o - n)).thenComparingInt(o -> -o));
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}