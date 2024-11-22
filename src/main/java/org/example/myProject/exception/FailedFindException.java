package org.example.myProject.exception;

public class FailedFindException extends GlobalException {
    public FailedFindException(int idx) {
        super(idx + "번 명언은 존재하지 않습니다.");
    }
}
