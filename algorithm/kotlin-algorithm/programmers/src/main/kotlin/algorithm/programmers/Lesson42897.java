package algorithm.programmers;

public class Lesson42897 {
    public int solution(int[] money) {
        var arr1 = new int[money.length];
        var arr2 = new int[money.length];

        arr1[0] = money[0];
        arr1[1] = arr1[0];
        arr2[0] = 0;
        arr2[1] = money[1];

        for (var i = 2; i < money.length; i++) {
            arr1[i] = Math.max(arr1[i - 1], arr1[i - 2] + money[i]);
            arr2[i] = Math.max(arr2[i - 1], arr2[i - 2] + money[i]);
        }
        return Math.max(arr1[arr1.length - 2], arr2[arr2.length - 1]);
    }
}
