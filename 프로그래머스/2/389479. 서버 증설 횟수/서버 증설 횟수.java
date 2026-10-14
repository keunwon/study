class Solution {
    public int solution(int[] players, int m, int k) {
        var servers = new int[players.length];
        var res = 0;

        for (var i = 0; i < players.length; i++) {
            if (i > 0) servers[i] += servers[i - 1];

            var p = players[i];
            if (p / m > servers[i]) {
                var add =  (p / m) - servers[i];
                res += add;
                servers[i] += add;
                if (i + k < players.length) {
                    servers[i + k] -= add;
                }
            }
        }
        return res;
    }
}