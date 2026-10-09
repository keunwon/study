class Solution
{
    public int solution(int n, int a, int b)
    {
        var round = 0;
        while (a != b) {
            ++round;
            a = (a / 2) + (a % 2);
            b = (b / 2) + (b % 2);
        }
        return round;
    }
}