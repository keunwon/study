class Solution {
    public int solution(int[] num_list) {
        var n1 = 0;
        var n2 = 0;
        
        for (var i = 0; i < num_list.length; i++) {
            var num = num_list[i];
            if (num % 2 == 0) {
                n1 = n1 * 10 + num;
            } else {
                n2 = n2 * 10 + num;
            }
        }
        return n1 + n2;
    }
}