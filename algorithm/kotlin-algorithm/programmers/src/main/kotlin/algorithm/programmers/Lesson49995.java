package algorithm.programmers;

public class Lesson49995 {
    public int solution(int[] cookie) {
        var result = 0;

        for (var i = 0; i < cookie.length - 1; i++) {
            var left = i;
            var right = i + 1;
            var leftSum = cookie[left];
            var rightSum = cookie[right];

            while (true) {
                if (leftSum == rightSum) {
                    result = Math.max(result, leftSum);
                }

                if (leftSum >= rightSum && right + 1 < cookie.length) {
                    rightSum += cookie[++right];
                } else if (leftSum <= rightSum && left - 1 >= 0) {
                    leftSum += cookie[--left];
                } else {
                    break;
                }
            }
        }

        return result;
    }
}
