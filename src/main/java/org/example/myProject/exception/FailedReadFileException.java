package org.example.myProject.exception;

public class FailedReadFileException extends GlobalException {
    public FailedReadFileException() {
        super("파일을 읽어오는 것을 실패하였습니다.");
    }
}
