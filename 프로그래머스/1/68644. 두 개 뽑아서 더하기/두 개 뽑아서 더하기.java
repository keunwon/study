import java.util.HashSet;

class Solution {
    public int[] solution(int[] numbers) {
        var n = numbers.length;
        var res = new HashSet<Integer>();

        for (var i = 0; i < n; i++) {
            for (var j = i + 1; j < n; j++) {
                res.add(numbers[i] + numbers[j]);
            }
        }
        return res.stream().mapToInt(Integer::intValue).sorted().toArray();
    }
}