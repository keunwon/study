import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (o1, o2) -> Integer.compare(o1[1], o2[1]));

        var res = 1;
        var end = routes[0][1];

        for (var i = 1; i < routes.length; i++) {
            var route = routes[i];
            if (end < route[0]) {
                ++res;
                end = route[1];
            }
        }
        return res;
    }
}