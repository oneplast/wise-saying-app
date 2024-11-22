package org.example.myProject.exception;

public class InvalidCommandInputException extends GlobalException {
    public InvalidCommandInputException() {
        super("부적절한 명령이 입력되었습니다. 명령어 목록을 보고 싶으면 '힌트' 를 입력해주세요.");
    }
}
