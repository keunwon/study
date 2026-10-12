import java.util.Arrays;

class Solution {
    public String solution(long n, String[] bans) {
        var base26List = Arrays.stream(bans).map(this::toBase26).sorted().toList();
        for (var base26 : base26List) {
            if (n < base26) break;
            ++n;
        }
        return base26ToString(n);
    }

    private String base26ToString(long n) {
        var sb = new StringBuilder();
        while (n-- > 0) {
            sb.append((char) ('a' + n % 26));
            n /= 26;
        }
        return sb.reverse().toString();
    }

    private long toBase26(String s) {
        var base26 = 0L;
        for (var i = 0; i < s.length(); i++) {
            var c = s.charAt(i) - 'a' + 1;
            base26 = base26 * 26 + c;
        }
        return base26;
    }
}