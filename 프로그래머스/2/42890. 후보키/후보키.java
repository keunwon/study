import java.util.ArrayList;
import java.util.HashSet;

class Solution {
    public int solution(String[][] relation) {
        var m = relation.length;
        var n = relation[0].length;
        var candidateKeys = new ArrayList<Integer>();

        for (var i = 1; i < (1 << n); i++) {
            var valid = true;
            for (var key : candidateKeys) {
                if ((key & i) == key) {
                    valid = false;
                    break;
                }
            }
            if (!valid) continue;

            var set = new HashSet<String>();
            for (String[] r : relation) {
                var sb = new StringBuilder();
                for (var k = 0; k < n; k++) {
                    if ((i & (1 << k)) > 0) {
                        sb.append(r[k]);
                    }
                }
                set.add(sb.toString());
            }

            if (set.size() == m) {
                candidateKeys.add(i);
            }
        }
        return candidateKeys.size();
    }
}