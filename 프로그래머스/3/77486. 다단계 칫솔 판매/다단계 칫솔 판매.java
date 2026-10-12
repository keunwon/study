import java.util.HashMap;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        var n = enroll.length;
        var idMap = new HashMap<String, Integer>(n);
        var parent = new HashMap<String, String>(n);

        for (var i = 0; i < n; i++) {
            var e = enroll[i];
            var r = referral[i];
            idMap.put(e, i);
            parent.put(e, r);
        }

        var fees = new int[n];
        for (var i = 0; i < seller.length; i++) {
            var s = seller[i];
            var a = amount[i] * 100;

            while (!s.equals("-") && a > 0) {
                var id = idMap.get(s);
                var percent10 = a * 10 / 100;

                fees[id] += a - percent10;
                a = percent10;
                s = parent.get(s);
            }
        }
        return fees;
    }
}