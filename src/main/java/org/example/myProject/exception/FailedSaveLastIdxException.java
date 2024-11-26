package org.example.myProject.exception;

public class FailedSaveLastIdxException extends GlobalException {
    public FailedSaveLastIdxException() {
        super("마지막 명언 번호 저장에 실패하였습니다.");
    }
}
