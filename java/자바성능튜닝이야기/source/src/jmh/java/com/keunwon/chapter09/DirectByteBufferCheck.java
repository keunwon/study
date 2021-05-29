package com.keunwon.chapter09;

import java.nio.ByteBuffer;

public class DirectByteBufferCheck {

    public static void main(String[] args) {
        DirectByteBufferCheck check = new DirectByteBufferCheck();

        for (int loop = 0; loop < 1024000; loop++) {
            check.getDirectByteBuffer();

            if (loop % 100 == 0) {
                System.out.println(loop);
            }
        }
    }

    public ByteBuffer getDirectByteBuffer() {
        ByteBuffer buffer;
        buffer = ByteBuffer.allocateDirect(65536);
        return buffer;
    }
}
