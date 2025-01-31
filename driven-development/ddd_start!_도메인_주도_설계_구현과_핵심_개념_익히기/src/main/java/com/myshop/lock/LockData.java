package com.myshop.lock;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LockData {
    private String type;
    private String id;
    private String lockId;
    private long timestamp;

    public boolean isExpired() {
        return timestamp < System.currentTimeMillis();
    }
}
