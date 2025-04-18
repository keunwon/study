package algorithm.programmers;

public class Lesson12920 {
    public int solution(int n, int[] cores) {
        var left = 0;
        var right = 10_000 * n;
        var work = 0;

        while (left <= right) {
            var mid = (left + right) / 2;
            var tmpWork = cores.length;

            for (var core : cores) {
                tmpWork += mid / core;
            }

            if (n <= tmpWork) {
                right = mid - 1;
                work = tmpWork;
            } else {
                left = mid + 1;
            }
        }

        for (var i = cores.length - 1; i >= 0; i--) {
            if (left % cores[i] == 0) {
                if (work == n) return i + 1;
                --work;
            }
        }
        return 0;
    }
}
