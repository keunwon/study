package com.myshop.intergration.infra;

import com.myshop.intergration.OffsetStore;
import org.springframework.stereotype.Component;

@Component
public class MemoryOffsetStore implements OffsetStore {
    private long nextOffset = 0;

    @Override
    public long get() {
        return nextOffset;
    }

    @Override
    public void update(long nextOffset) {
        this.nextOffset = nextOffset;
    }
}
