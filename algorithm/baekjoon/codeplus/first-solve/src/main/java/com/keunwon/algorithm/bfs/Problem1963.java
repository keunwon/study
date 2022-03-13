package com.keunwon.algorithm.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Problem1963 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final boolean[] primes = createPrimesArray();

    public static void main(String[] args) throws IOException {
        var T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            var st = new StringTokenizer(br.readLine());
            var start = Integer.parseInt(st.nextToken());
            var end = Integer.parseInt(st.nextToken());

            if (start == end) {
                System.out.println(0);
                continue;
            }

            bfs(start, end);
        }
    }

    private static void bfs(int start, int end) {
        var queue = new LinkedList<Integer>();
        var visited = new int[10000];

        Arrays.fill(visited, Integer.MAX_VALUE);
        queue.offer(start);
        visited[start] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            int count = visited[now];

            if (now == end) {
                System.out.println(count);
                return;
            }

            int[] numbers = splitNum(now);

            for (int i = 0; i < numbers.length; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i == 0 && j == 0) { continue; }

                    var temp = numbers[i];
                    numbers[i] = j;
                    int nextNum = combine(numbers);
                    numbers[i] = temp;

                    if (primes[nextNum]) { continue; }

                    if (count + 1 < visited[nextNum]) {
                        queue.offer(nextNum);
                        visited[nextNum] = count + 1;
                    }
                }
            }
        }

        System.out.println("Impossible");
    }

    private static int combine(int[] nums) {
        var pow = (int) Math.pow(10, nums.length - 1);
        var result = 0;

        for (int num : nums) {
            result += num * pow;
            pow /= 10;
        }
        return result;
    }

    private static int[] splitNum(int num) {
        var size = (int) (Math.log10(num) + 1);
        var result =  new int[size];

        for (int i = size - 1; i >= 0; i--) {
            result[i] = num % 10;
            num /= 10;
        }
        return result;
    }


    private static boolean[] createPrimesArray() {
        boolean[] arr = new boolean[10000];

        for (int i = 2; i <= Math.sqrt(9999); i++) {
            for (int j = i * 2; j <= 9999; j += i) {
                arr[j] = true;
            }
        }
        return arr;
    }
}
