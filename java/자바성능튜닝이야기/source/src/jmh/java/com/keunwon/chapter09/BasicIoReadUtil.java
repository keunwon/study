package com.keunwon.chapter09;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BasicIoReadUtil {

    public static ArrayList readCharStream(String fileName) throws Exception {
        ArrayList<StringBuffer> list = new ArrayList<>();

        try (FileReader fr = new FileReader(fileName)) {
            int data = 0;
            StringBuffer sb = new StringBuffer();
            while ((data = fr.read()) != -1) {
                if (data == '\n' || data == '\r') {
                    list.add(sb);
                    sb = new StringBuffer();
                } else {
                    sb.append(data);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        }

        return list;
    }

    public static String readCharStreamWithBuffer(String fileName) throws Exception {
        StringBuffer retSB = new StringBuffer();

        try (FileReader fr = new FileReader(fileName)) {
            int bufferSize = 1024 * 1024;
            char[] readBuffer = new char[bufferSize];
            int resultSize = 0;

            while ((resultSize = fr.read(readBuffer)) != -1) {
                if (resultSize == bufferSize) {
                    retSB.append(readBuffer);
                } else {
                    for (int loop = 0; loop < resultSize; loop++) {
                        retSB.append(readBuffer[loop]);
                    }
                }
            }

            return retSB.toString();
        }
    }

    public static List<String> readBufferReader(String fileName) throws Exception {
        List<String> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String data;
            while ((data = br.readLine()) != null) {
                list.add(data);
            }
        }
        return list;
    }
}
