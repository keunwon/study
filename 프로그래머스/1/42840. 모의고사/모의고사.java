import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        var people = new int[][] {{1, 2, 3, 4, 5}, {2, 1, 2, 3, 2, 4, 2, 5}, {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        var point = new int[3];

        for (var i = 0; i < answers.length; i++) {
            var answer = answers[i];

            for (var j = 0; j < 3; j++) {
                var p = people[j];
                if (p[i % p.length] == answer) {
                    ++point[j];
                }
            }
        }

        var max = Math.max(point[0], Math.max(point[1], point[2]));
        var res = new ArrayList<Integer>();

        for (var i = 0; i < 3; i++) {
            if (point[i] == max) res.add(i + 1);
        }
        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}