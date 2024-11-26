package org.example.myProject.exception;

public class FailedFileDeleteException extends GlobalException {
    public FailedFileDeleteException() {
        super("파일 삭제에 실패하였습니다.");
    }
}
