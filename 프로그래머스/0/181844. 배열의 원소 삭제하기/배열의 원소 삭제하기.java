import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    public int[] solution(int[] arr, int[] delete_list) {
        var set = Arrays.stream(delete_list).boxed().collect(Collectors.toSet());
        var res = new ArrayList<Integer>();

        for (var num : arr) {
            if (!set.contains(num)) res.add(num);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}