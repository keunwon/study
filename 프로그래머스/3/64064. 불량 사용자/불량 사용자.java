import java.util.BitSet;

class Solution {
    private String[] users;
    private String[] bans;
    private BitSet bitset;

    public int solution(String[] user_id, String[] banned_id) {
        this.users = user_id;
        this.bans = banned_id;
        this.bitset = new BitSet();

        dfs(0, 0);
        return bitset.cardinality();
    }

    private void dfs(int mask, int depth) {
        if (depth == bans.length) {
            bitset.set(mask);
            return;
        }

        for (var i = 0; i < users.length; i++) {
            var user = users[i];
            if ((mask & (1 << i)) == 0 && match(user, bans[depth])) {
                dfs(mask | (1 << i), depth + 1);
            }
        }
    }

    private boolean match(String user, String ban) {
        if (user.length() != ban.length()) return false;

        var n = user.length();
        for (var i = 0; i < n; i++) {
            if (ban.charAt(i) != '*' && ban.charAt(i) != user.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}