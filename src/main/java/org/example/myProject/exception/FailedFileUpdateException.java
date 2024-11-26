package org.example.myProject.exception;

public class FailedFileUpdateException extends GlobalException {
    public FailedFileUpdateException() {
        super("파일 수정에 실패하였습니다.");
    }
}
