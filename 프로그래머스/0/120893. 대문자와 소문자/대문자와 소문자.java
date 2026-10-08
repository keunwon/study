class Solution {
    public String solution(String my_string) {
        var arr = my_string.toCharArray();
        for (var i = 0; i < arr.length; i++) {
            var c = arr[i];
            arr[i] = (char) (c ^ 32);
        }
        return new String(arr);
    }
}