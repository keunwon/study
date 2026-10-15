import java.util.ArrayList;

class Solution {
    public int[] solution(String myString) {
        var res = new ArrayList<Integer>();
        var count = 0;
        
        for (var i = 0; i < myString.length(); i++) {
            var c = myString.charAt(i);
            if (c == 'x') {
                res.add(count);
                count = 0;
            } else {
                ++count;
            }
        }
        
        if (myString.charAt(myString.length() - 1) == 'x') {
            res.add(0);
        } else {
            res.add(count);
        }
        
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}