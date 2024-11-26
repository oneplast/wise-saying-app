package org.example.myProject.exception;

public class FailedFileBuildException extends GlobalException {
    public FailedFileBuildException() {
        super("파일 빌드에 실패하였습니다.");
    }
}
