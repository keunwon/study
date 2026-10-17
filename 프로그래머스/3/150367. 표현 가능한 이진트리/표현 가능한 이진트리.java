import java.util.Arrays;

class Solution {
    public int[] solution(long[] numbers) {
        var res = new int[numbers.length];
        for (var i = 0; i < numbers.length; i++) {
            var binary = generateBinary(numbers[i]);
            res[i] = check(binary, 0, binary.length() - 1) ? 1 : 0;
        }
        return res;
    }

    private boolean check(String s, int left, int right) {
        if (left >= right) return true;

        var m = (left + right) / 2;
        if (s.charAt(m) == '0') {
            for (var i = left; i <= right; i++) {
                if (s.charAt(i) == '1') return false;
            }
            return true;
        }
        return check(s, left, m - 1) && check(s, m + 1, right);
    }

    private String generateBinary(long number) {
        var b = Long.toString(number, 2);
        var size = (Integer.highestOneBit(b.length()) << 1) - 1;
        var arr = new char[size];

        Arrays.fill(arr, '0');

        var start = size - b.length();
        for (var i = start; i < size; i++) {
            arr[i] = b.charAt(i - start);
        }
        return new String(arr);
    }

    static void main() {
        new Solution().solution(new long[] {7, 42, 5});
    }
}