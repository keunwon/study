class Solution {
    public String[] solution(String[] s) {
        var res = new String[s.length];

        for (var i = 0; i < s.length; i++) {
            var arr = s[i].toCharArray();
            var top = -1;
            var countBy110 = 0;

            for (var j = 0; j < arr.length; j++) {
                arr[++top] = arr[j];
                if (top >= 2 && arr[top] == '0' && arr[top - 1] == '1' && arr[top - 2] == '1') {
                    top -= 3;
                    ++countBy110;
                }
            }

            var lastZeroIdx = -1;
            for (var j = top; j >= 0; j--) {
                if (arr[j] == '0') {
                    lastZeroIdx = j;
                    break;
                }
            }

            var len = top - lastZeroIdx;
            System.arraycopy(arr, lastZeroIdx + 1, arr, arr.length - len, len);

            var idx = lastZeroIdx;
            for (var j = 0; j < countBy110; j++) {
                arr[++idx] = '1';
                arr[++idx] = '1';
                arr[++idx] = '0';
            }
            res[i] = new String(arr);
        }
        return res;
    }
}