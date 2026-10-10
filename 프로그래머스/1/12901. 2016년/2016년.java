class Solution {
    public String solution(int a, int b) {
        var month = new int[] {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        var days = new String[] {
            "FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU",
        };

        var targetDay = 0;
        for (var i = 0; i < a - 1; i++) {
            targetDay += month[i];
        }
        targetDay += b - 1;
        
        return days[targetDay % 7];
    }
}