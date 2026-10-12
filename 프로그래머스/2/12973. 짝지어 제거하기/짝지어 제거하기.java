class Solution
{
    public int solution(String s)
    {
        var arr = s.toCharArray();
        var top = -1;

        for (var i = 0; i < arr.length; i++) {
            arr[++top] = arr[i];

            if (top >= 1 && arr[top - 1] == arr[top]) {
                top -= 2;
            }
        }
        return top == -1 ? 1 : 0;
    }
}