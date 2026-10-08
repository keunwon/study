class Solution {
    public String solution(String my_string, int num1, int num2) {
        var arr = my_string.toCharArray();
        var tmp = arr[num1];
        arr[num1] = arr[num2];
        arr[num2] = tmp;
        return new String(arr);
    }
}