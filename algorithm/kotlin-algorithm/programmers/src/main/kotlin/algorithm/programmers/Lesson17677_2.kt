package algorithm.programmers

class Lesson17677_2 {
    public int solution(String str1, String str2) {
        var count1 = new int[676];
        var count2 = new int[676];

        var n1 = extractTowCharAndFill(str1.toLowerCase(), count1);
        var n2 = extractTowCharAndFill(str2.toLowerCase(), count2);

        if (n1 == 0 && n2 == 0) {
            return 65536;
        }

        var inter = 0;
        var union = 0;

        for (var i = 0; i < 676; i++) {
            if (count1[i] > 0 || count2[i] > 0) {
                inter += Math.min(count1[i], count2[i]);
                union += Math.max(count1[i], count2[i]);
            }
        }

        var x = ((double) inter / union);
        return (int) (x * 65536);
    }

    private int extractTowCharAndFill(String s, int[] count) {
        var res = 0;
        for (var i = 0; i < s.length() - 1; i++) {
            var c1 = s.charAt(i);
            var c2 = s.charAt(i + 1);

            if ('a' <= c1 && c1 <= 'z' && 'a' <= c2 && c2 <= 'z') {
                var idx = (c1 - 'a') * 26 + (c2 - 'a');
                ++count[idx];
                ++res;
            }
        }
        return res;
    }
}
