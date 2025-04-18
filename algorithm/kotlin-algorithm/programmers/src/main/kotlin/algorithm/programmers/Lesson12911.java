package algorithm.programmers;

public class Lesson12911 {
    public int solution(int n) {
        var oneCount = countByBinaryOne(n);
        var target = n;

        while (true) {
            ++target;
            var tmpOneCount = countByBinaryOne(target);
            if (oneCount == tmpOneCount) break;
        }
        return target;
    }

    private int countByBinaryOne(int n) {
        var arr = Integer.toString(n, 2).toCharArray();
        var count = 0;
        for (char c : arr) {
            if (c == '1') ++count;
        }
        return count;
    }
}
