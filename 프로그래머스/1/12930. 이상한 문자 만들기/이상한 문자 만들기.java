class Solution {
    public String solution(String s) {
        var arr = s.toUpperCase().toCharArray();
        var idx = 0;
        var flag = true;

        while (idx < s.length()) {
            if (s.charAt(idx) == ' ') {
                flag = true;
            } else {
                if (!flag) {
                    arr[idx] = Character.toLowerCase(arr[idx]);
                }
                flag = !flag;
            }

            ++idx;
        }
        return new String(arr);
    }
}