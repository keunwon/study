class Solution {
    public int solution(int[] num_list) {
        var n1 = 0;
        var n2 = 0;
        var flag = true;
        
        for (var i = 0; i < num_list.length; i++) {
            if (flag) n1 += num_list[i];
            else n2 += num_list[i];
            
            flag = !flag;
        }
        return Math.max(n1, n2);
    }
}