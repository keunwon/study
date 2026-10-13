class Solution {
    public String solution(int[] numbers, String hand) {
        var left = 10;
        var right = 12;
        var sb = new StringBuilder(numbers.length);

        for (var num : numbers) {
            switch (num) {
                case 1, 4, 7 -> {
                    left = num;
                    sb.append("L");
                }

                case 3, 6, 9 -> {
                    right = num;
                    sb.append('R');
                }

                case 2, 5, 8, 0 -> {
                    var x = num == 0 ? 11 : num;
                    var diff1 = Math.abs(x - left) / 3 + Math.abs(x - left) % 3;
                    var diff2 = Math.abs(x - right) / 3 + Math.abs(x - right) % 3;

                    if (diff1 < diff2) {
                        sb.append('L');
                        left = x;
                    } else if (diff1 > diff2) {
                        sb.append('R');
                        right = x;
                    } else {
                        if (hand.equals("left")) {
                            sb.append('L');
                            left = x;
                        } else {
                            sb.append('R');
                            right = x;
                        }
                    }
                }
            }
        }
        return sb.toString();
    }
}