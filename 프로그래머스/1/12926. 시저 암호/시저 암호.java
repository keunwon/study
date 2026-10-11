class Solution {
    public String solution(String s, int n) {
        var m = s.length();
        var arr = new char[m];

        for (var i = 0; i < m; i++) {
            var c = s.charAt(i);

            if (c >= 'a') {
                c = (char) (c + n);
                if (c > 'z') c -= 26;
                arr[i] = c;
            } else if (c >= 'A') {
                c = (char) (c + n);
                if (c > 'Z') c -= 26;
                arr[i] = c;
            } else {
                arr[i] = c;
            }
        }
        return new String(arr);
    }
}