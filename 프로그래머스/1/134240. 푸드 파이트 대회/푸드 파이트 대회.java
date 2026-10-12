class Solution {
    public String solution(int[] food) {
        var sb = new StringBuilder();
        for (var i = 1; i < food.length; i++) {
            var x = food[i] / 2;
            sb.repeat(String.valueOf(i), x);
        }
        return sb + "0" + sb.reverse();
    }
}