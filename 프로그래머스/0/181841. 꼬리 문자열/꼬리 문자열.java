class Solution {
    public String solution(String[] str_list, String ex) {
        var sb = new StringBuilder();
        for (var str : str_list) {
            if (!str.contains(ex)) {
                sb.append(str);
            }
        }
        return sb.toString();
    }
}