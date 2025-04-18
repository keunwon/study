package algorithm.programmers;

public class Lesson12899 {
    public String solution(int n) {
        var numbers = new int[]{4, 1, 2};
        var answer = new StringBuilder();

        while (n > 0) {
            var mod = n % 3;
            n /= 3;

            if (mod == 0) --n;
            answer.insert(0, numbers[mod]);
        }
        return answer.toString();
    }
}
