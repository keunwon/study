import java.util.Arrays;

class Solution {
    public int solution(int[] array) {
        Arrays.sort(array);
        var m = array.length / 2;
        return array[m];
    }
}