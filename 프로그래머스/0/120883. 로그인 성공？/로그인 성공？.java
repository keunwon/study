class Solution {
    public String solution(String[] id_pw, String[][] db) {
        for (var d : db) {
            if (id_pw[0].equals(d[0])) {
                if (id_pw[1].equals(d[1])) return "login";
                else return "wrong pw";
            }
        }
        return "fail";
    }
}