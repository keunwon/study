class Solution {
    public String solution(String my_string, int[] index_list) {
        var arr = new char[index_list.length];
        for (var i = 0; i < index_list.length; i++) {
            arr[i] = my_string.charAt(index_list[i]);
        }
        return new String(arr);
    }
}