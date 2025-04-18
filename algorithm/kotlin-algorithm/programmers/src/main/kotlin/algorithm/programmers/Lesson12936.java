package algorithm.programmers;

import java.util.ArrayList;

public class Lesson12936 {
    public int[] solution(int n, long k) {
        var list = new ArrayList<Integer>();
        var size = factory(n - 1);
        var result = new int[n];
        var index = 0;

        for (var i = 1; i <= n; i++) {
            list.add(i);
        }

        --k;
        while (size > 0) {
            var div = (int) (k / size);
            k %= size;

            result[index++] = list.remove(div);
            --n;
            size = factory(n - 1);
        }
        result[index++] = list.remove(0);

        return result;
    }

    private long factory(int n) {
        if (n <= 1) return n;
        return n * factory(n - 1);
    }
}
