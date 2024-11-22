package org.example.myProject.exception;

public class GlobalException extends RuntimeException {
    String msg;

    public GlobalException(String msg) {
        super(msg);
    }
}
