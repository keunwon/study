package com.java.netty.ch6;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class UnsignedByteBufferTest {
    private final String source = "hello world";

    @Test
    public void unsignedBufferToJavaBuffer() {
        ByteBuf buf = Unpooled.buffer(11);
        buf.writeShort(-1);
        assertEquals(65535, buf.getUnsignedShort(0));
    }
}
