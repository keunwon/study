class Solution {
    public int solution(int hp) {
        var res = 0;
        
        res += hp / 5;
        hp %= 5;
        
        res += hp / 3;
        hp %= 3;
        
        return res + hp;
    }
}