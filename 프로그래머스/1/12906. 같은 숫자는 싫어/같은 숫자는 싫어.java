import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        var res = new ArrayList<Integer>();
        res.add(arr[0]);
        
        for (var i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i - 1]) {
                res.add(arr[i]);
            }
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}