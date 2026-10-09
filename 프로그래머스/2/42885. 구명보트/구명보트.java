import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        var l = 0;
        var r = people.length - 1;
        var res = 0;
        
        while (l <= r) {
            if (limit >= people[l] + people[r]) {
                ++l;
            }
            --r;
            ++res;
        }
        return res;
    }
}