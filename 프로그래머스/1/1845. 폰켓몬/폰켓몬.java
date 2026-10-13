import java.util.HashSet;

class Solution {
    public int solution(int[] nums) {
        var m = nums.length / 2;
        var set = new HashSet<Integer>();
        
        for (var num : nums) {
            set.add(num);
            if (set.size() == m) break;
        }
        return set.size();
    }
}