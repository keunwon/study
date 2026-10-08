class Solution {
    public int solution(int[] num_list) {
        var sum = 0;
        var multi = 1;
        
        for (var num : num_list) {
            sum += num;
            multi *= num;
        }
        return (sum * sum) > multi ? 1 : 0;
    }
}