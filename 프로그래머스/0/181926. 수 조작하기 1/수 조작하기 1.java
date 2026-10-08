class Solution {
    public int solution(int n, String control) {
        for (var i = 0; i < control.length(); i++) {
            switch(control.charAt(i)) {
                case 'w' -> ++n;
                case 's' -> --n;
                case 'd' -> n += 10;
                case 'a' -> n -= 10;
            }
        }
        return n;
    }
}