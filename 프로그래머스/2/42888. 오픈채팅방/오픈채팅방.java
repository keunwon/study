import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] record) {
        var users = new HashMap<String, String>(record.length);
        for (var r : record) {
            var tokens = r.split(" ");
            if (tokens[0].equals("Enter") || tokens[0].equals("Change")) {
                users.put(tokens[1], tokens[2]);
            }
        }

        var res = new ArrayList<String>();
        for (var r : record) {
            var tokens = r.split(" ");
            var name = users.get(tokens[1]);

            switch (tokens[0]) {
                case "Enter" -> res.add(name + "님이 들어왔습니다.");
                case "Leave" -> res.add(name + "님이 나갔습니다.");
            }
        }
        return res.toArray(String[]::new);
    }
}