class Solution {
    public String solution(String myString) {
        var arr = myString.toCharArray();
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] < 'l') arr[i] = 'l';
        }
        return new String(arr);
    }
}