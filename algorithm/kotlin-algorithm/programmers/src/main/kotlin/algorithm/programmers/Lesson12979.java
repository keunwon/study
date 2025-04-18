package algorithm.programmers;

public class Lesson12979 {
    public int solution(int n, int[] stations, int w) {
        var answer = 0;
        var sIndex = 0;
        var cur = 1;

        while (cur <= n) {
            if (sIndex < stations.length && cur >= stations[sIndex] - w) {
                cur = stations[sIndex] + w + 1;
                ++sIndex;
            } else {
                ++answer;
                cur += (w * 2) + 1;
            }
        }
        return answer;
    }
}
