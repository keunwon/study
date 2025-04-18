package algorithm.programmers;

public class Lesson12980 {
    public int solution(int n) {
        var answer = 0;
        while (n > 0) {
            if (n % 2 == 0) {
                n /= 2;
            } else {
                --n;
                ++answer;
            }
        }
        return answer;
    }
}
