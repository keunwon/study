class Solution {
    public int solution(int[] wallet, int[] bill) {
        sort(wallet);
        sort(bill);

        var res = 0;
        while (bill[0] > wallet[0] || bill[1] > wallet[1]) {
            bill[1] /= 2;
            sort(bill);
            ++res;
        }
        return res;
    }

    private void sort(int[] arr) {
        if (arr[0] > arr[1]) {
            var tmp = arr[0];
            arr[0] = arr[1];
            arr[1] = tmp;
        }
    }
}