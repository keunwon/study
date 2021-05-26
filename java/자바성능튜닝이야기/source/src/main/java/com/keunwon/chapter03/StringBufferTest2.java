package com.keunwon.chapter03;

public class StringBufferTest2 {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();

        sb.append("ABCDE");
        sb.append("FGHIJ");
        sb.append("JKLMNO");

        sb.append("ABCDE")
            .append("FGHIJ")
            .append("KLMNO");

        sb.insert(3, "123");
        System.out.println(sb);
    }
}
