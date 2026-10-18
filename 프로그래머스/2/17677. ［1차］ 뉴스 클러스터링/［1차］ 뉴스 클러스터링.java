public class Solution {
    public int solution(String str1, String str2) {
        var count1 = createCountArr(str1.toLowerCase());
        var count2 = createCountArr(str2.toLowerCase());

        var allZero = true;
        for (var i = 0; i < count1.length; i++) {
            if (count1[i] > 0) {
                allZero = false;
                break;
            }
            if (count2[i] > 0) {
                allZero = false;
                break;
            }
        }
        if (allZero) {
            return 65536;
        }

        var union = 0;
        var inter = 0;
        for (var i = 0; i < count1.length; i++) {
            union += Math.max(count1[i], count2[i]);
            inter += Math.min(count1[i], count2[i]);
        }

        var x = ((double) inter / union);
        return (int) (x * 65536);
    }

    private int[] createCountArr(String s) {
        var count = new int[676];
        for (var i = 0; i < s.length() - 1; i++) {
            var c1 = s.charAt(i);
            var c2 = s.charAt(i + 1);

            if (Character.isAlphabetic(c1) && Character.isAlphabetic(c2)) {
                var idx = (c1 - 'a') * 26 + (c2 - 'a');
                ++count[idx];
            }
        }
        return count;
    }
}
