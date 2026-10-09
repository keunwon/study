class Solution {
    public int solution(String[] order) {
        var price = 0;
        for (var o : order) {
            price += o.contains("cafelatte") ? 5000 : 4500;
        }
        return price;
    }
}