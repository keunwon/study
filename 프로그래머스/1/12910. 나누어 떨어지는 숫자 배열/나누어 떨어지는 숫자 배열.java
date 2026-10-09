import java.util.ArrayList;

class Solution {
    public int[] solution(int[] arr, int divisor) {
        var res = new ArrayList<Integer>();
        for (var num : arr) {
            if (num % divisor == 0) res.add(num);
        }
        return res.isEmpty()
                ? new int[] {-1}
                : res.stream().mapToInt(Integer::intValue).sorted().toArray();
    }
}