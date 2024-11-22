package org.example.myProject.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {
    REGISTER("등록"),
    LIST("목록"),
    DELETE("삭제"),
    UPDATE("수정"),
    EXIT("종료"),
    INVALID("부적절"),
    HINT("힌트");

    public final String name;
    public final static String DEL_REG = "^삭제\\?id=\\d+$";
    public final static String UPDATE_REG = "^수정\\?id=\\d+$";

    Command(String name) {
        this.name = name;
    }

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

    public int getId(String command) {
        return Integer.parseInt(command.split("\\?id=")[1]);
    }

    private static boolean match(String reg, String command) {
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(command);

        return matcher.matches();
    }
}
