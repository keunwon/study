package com.keunwon.algorithm.sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Problem5052 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T -- > 0) {
            int N = Integer.parseInt(br.readLine());
            List<String> phoneNumbers = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                phoneNumbers.add(br.readLine());
            }

            Collections.sort(phoneNumbers);

            if (hasPhoneNumbers(N, phoneNumbers)) {
                bw.write("No");
            } else {
                bw.write("YES");
            }
            bw.write("\n");
        }

        bw.flush();
    }

    static boolean hasPhoneNumbers(int n, List<String> list) {
        for (int i = 0; i < n - 1; i++) {
            if (list.get(i + 1).startsWith(list.get(i))) {
                return true;
            }
        }
        return false;
    }
}
