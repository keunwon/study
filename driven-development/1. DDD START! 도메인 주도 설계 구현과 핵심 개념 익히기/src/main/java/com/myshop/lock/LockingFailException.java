package com.myshop.lock;

import org.springframework.dao.DuplicateKeyException;

public class LockingFailException extends RuntimeException {

    public LockingFailException() {
    }

    public LockingFailException(Throwable cause) {
        super(cause);
    }
}
