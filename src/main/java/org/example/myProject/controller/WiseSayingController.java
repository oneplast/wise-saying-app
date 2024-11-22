package org.example.myProject.controller;

import java.util.Scanner;
import org.example.myProject.entity.WiseSaying;
import org.example.myProject.enums.Command;
import org.example.myProject.exception.GlobalException;
import org.example.myProject.exception.InvalidCommandInputException;
import org.example.myProject.service.WiseSayingService;

public class WiseSayingController {
    private WiseSayingService wiseSayingService;
    private Scanner scanner;

    public WiseSayingController(WiseSayingService wiseSayingService) {
        this.wiseSayingService = wiseSayingService;
    }

    public void run() {
        boolean isNotQuit = true;
        System.out.println("== 명언 앱 ==");

        while (isNotQuit) {
            printCommand();

            isNotQuit = handleCommand();
        }
    }

    public boolean handleCommand() {
        scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Command command = Command.findByCommand(input);

        try {
            switch (command) {
                case REGISTER:
                    register();
                    break;
                case LIST:
                    showList();
                    break;
                case DELETE:
                    int idx = command.getId(input);
                    delete(idx);
                    break;
                case UPDATE:
                    idx = command.getId(input);
                    update(idx);
                    break;
                case EXIT:
                    return false;
                case INVALID:
                    throw new InvalidCommandInputException();
                case HINT:
                    printHintMsg();
                    break;
            }
        } catch (GlobalException e) {
            printErrorMsg(e);
        }

        return true;
    }

    public void register() {
        printRequestWiseSaying();
        String msg = scanner.nextLine();

        printRequestAuthor();
        String author = scanner.nextLine();

        int idx = wiseSayingService.getLastIdx();
        wiseSayingService.register(author, msg);

        printCompleteRegisterMsg(idx);
    }

    public void showList() {
        printListFormatMsg();
        wiseSayingService.getWiseSayings().forEach(System.out::println);
    }

    public void delete(int idx) {
        wiseSayingService.delete(idx);

        printCompleteDeleteMsg(idx);
    }

    public void update(int idx) {
        WiseSaying wiseSaying = getWiseSaying(idx);

        printExistingWiseSaying(wiseSaying);
        printRequestWiseSaying();
        String saying = scanner.nextLine();

        printExistingAuthor(wiseSaying);
        printRequestAuthor();
        String author = scanner.nextLine();
        wiseSayingService.update(idx, saying, author);
    }

    public WiseSaying getWiseSaying(int idx) {
        return wiseSayingService.getWiseSaying(idx);
    }

    public void printCommand() {
        System.out.print("명령) ");
    }

    public void printListFormatMsg() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-----------------------");
    }

    public void printCompleteRegisterMsg(int idx) {
        System.out.println(idx + "번 명언이 등록되었습니다.");
    }

    public void printCompleteDeleteMsg(int idx) {
        System.out.println(idx + "번 명언이 삭제되었습니다.");
    }

    public void printHintMsg() {
        System.out.println("등록, 목록, 삭제?id=(숫자), 수정?id=(숫자), 빌드, 종료");
    }

    public void printErrorMsg(GlobalException e) {
        System.out.println(e.getMessage());
    }

    public void printRequestWiseSaying() {
        System.out.print("명언 : ");
    }

    public void printRequestAuthor() {
        System.out.print("작가 : ");
    }

    public void printExistingWiseSaying(WiseSaying wiseSaying) {
        System.out.println("명언(기존) : " + wiseSaying.getSaying());
    }

    public void printExistingAuthor(WiseSaying wiseSaying) {
        System.out.println("작가(기존) : " + wiseSaying.getAuthor());
    }
}
