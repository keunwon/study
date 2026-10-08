class Solution {
    public int[] solution(int[] num_list) {
        var l = 0;
        var r = num_list.length - 1;
        
        while (l < r) {
            var tmp = num_list[l];
            num_list[l++] = num_list[r];
            num_list[r--] = tmp;
        }
        return num_list;
    }
}