package org.example.myProject.exception;

public class FailedFileSaveException extends GlobalException {
    public FailedFileSaveException() {
        super("파일 저장에 실패하였습니다.");
    }
}
