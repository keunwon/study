import java.util.ArrayList;

class Solution {
    public int[] solution(int n, long k) {
        var numbers = new ArrayList<Integer>(n);
        for (int i = 1; i <= n; i++) {
            numbers.add(i);
        }

        var size = factory(n - 1);
        var res = new int[n];
        var rIdx = 0;
        
        --k;

        while (size > 0) {
            var idx = (int) (k / size);
            k %= size;
            res[rIdx++] = numbers.remove(idx);
            --n;
            size = factory(n - 1);
        }
        res[rIdx] = numbers.getFirst();
        return res;
    }

    private long factory(int n) {
        if (n < 2) return n;
        return (long) n * factory(n - 1);
    }
}