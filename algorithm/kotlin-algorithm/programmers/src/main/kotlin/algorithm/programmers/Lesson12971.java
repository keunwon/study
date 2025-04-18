package algorithm.programmers;

public class Lesson12971 {
    public int solution(int[] sticker) {
        if (sticker.length == 1) return sticker[0];

        var arr1 = new int[sticker.length];
        var arr2 = new int[sticker.length];

        arr1[0] = sticker[0];
        arr1[1] = sticker[0];
        arr2[0] = 0;
        arr2[1] = sticker[1];

        for (int i = 2; i < sticker.length; i++) {
            arr1[i] = Math.max(arr1[i - 1], arr1[i - 2] + sticker[i]);
            arr2[i] = Math.max(arr2[i - 1], arr2[i - 2] + sticker[i]);
        }
        return Math.max(arr1[arr1.length - 2], arr2[arr2.length - 1]);
    }
}
