package org.example.myProject.exception;

public class FailedLoadWiseException extends GlobalException {
    public FailedLoadWiseException() {
        super("파일에서 명언들을 가져오는 것을 실패하였습니다.");
    }
}
