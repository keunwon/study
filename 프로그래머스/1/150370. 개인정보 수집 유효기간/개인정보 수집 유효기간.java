import java.util.ArrayList;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        var targetDay = toDay(today);
        var termArr = new int[26];

        for (var term : terms) {
            var t = term.charAt(0) - 'A';
            termArr[t] = Integer.parseInt(term.substring(2)) * 28;
        }

        var res = new ArrayList<Integer>();
        for (var i = 0; i < privacies.length; i++) {
            var p = privacies[i];
            var day = toDay(p.substring(0, 10));
            var t = p.charAt(11) - 'A';

            day += termArr[t];
            if (targetDay >= day) res.add(i + 1);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }

    private int toDay(String s) {
        var y = (Integer.parseInt(s.substring(0, 4)) - 1) * 28 * 12;
        var m = (Integer.parseInt(s.substring(5, 7)) - 1) * 28;
        var d = Integer.parseInt(s.substring(8, 10));
        return y + m + d;
    }
}