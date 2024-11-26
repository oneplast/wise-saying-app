package org.example.myProject.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.example.myProject.exception.InvalidCommandInputException;

public enum Command {
    REGISTER("등록"),
    LIST("목록"),
    DELETE("삭제"),
    UPDATE("수정"),
    EXIT("종료"),
    BUILD("빌드"),
    INVALID("부적절"),
    HINT("힌트");

    public final String name;
    public final static String DEL_REG = "^삭제\\?id=\\d+$";
    public final static String UPDATE_REG = "^수정\\?id=\\d+$";

    Command(String name) {
        this.name = name;
    }

    // 명령 추출
    public static Command findByCommand(String command) {
        for (Command com : Command.values()) {
            if (match(DEL_REG, command)) {
                return DELETE;
            } else if (match(UPDATE_REG, command)) {
                return UPDATE;
            } else if (com.name.equals(command)) {
                return com;
            }
        }

        return INVALID;
    }

    // 명령에서 idx 추출
    public int getId(String command) {
        try {
            return Integer.parseInt(command.split("\\?id=")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidCommandInputException();
        }
    }

    // 정규식 매칭
    private static boolean match(String reg, String command) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(command);

        return matcher.matches();
    }
}
