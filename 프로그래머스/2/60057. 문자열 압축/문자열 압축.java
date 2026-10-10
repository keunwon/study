class Solution {
    public int solution(String s) {
        var n = s.length();
        var minLen = n;

        for (var size = 1; size <= n / 2; size++) {
            var count = 1;
            var len = 0;

            for (var i = size; i < n; i += size) {
                var isSame = true;
                for (var j = 0; j < size; j++) {
                    if (i + j >= n || s.charAt(i + j) != s.charAt(i - size + j)) {
                        isSame = false;
                        break;
                    }
                }

                if (isSame) {
                    ++count;
                } else {
                    if (count > 1) {
                        len += (int) Math.log10(count) + 1;
                    }
                    len += size;
                    count = 1;
                }
            }

            if (count > 1) {
                len += (int) Math.log10(count) + 1;
            }
            len += n % size == 0 ? size : n % size;
            minLen = Math.min(minLen, len);
        }
        return minLen;
    }
}