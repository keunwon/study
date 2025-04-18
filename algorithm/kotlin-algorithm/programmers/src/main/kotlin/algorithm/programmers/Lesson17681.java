package algorithm.programmers;

public class Lesson17681 {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        var result = new String[n];

        for (var i = 0; i < n; i++) {
            var binary = Integer.toString(arr1[i] | arr2[i], 2);
            result[i] = String.format("%" + n + "s", binary)
                    .replace("1", "#")
                    .replace("0", " ");
        }
        return result;
    }
}
