import java.util.HashMap;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        var map = new HashMap<String, Integer>(players.length);
        for (var i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }

        for (var call : callings) {
            var curId = map.get(call);
            if (curId == 0) continue;

            var prevPlayer = players[curId - 1];
            var prevId = curId - 1;

            players[prevId] = call;
            players[curId] = prevPlayer;

            map.put(call, prevId);
            map.put(prevPlayer, curId);
        }
        return players;
    }
}