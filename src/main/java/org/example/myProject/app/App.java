package org.example.myProject.app;

import java.util.Scanner;
import org.example.myProject.controller.WiseSayingController;
import org.example.myProject.enums.Command;
import org.example.myProject.exception.GlobalException;
import org.example.myProject.exception.InvalidCommandInputException;
import org.example.myProject.repository.WiseSayingFileRepository;
import org.example.myProject.service.WiseSayingServiceImpl;

public class App {
    private final WiseSayingController wiseSayingController;
    private final Scanner scanner;

    public App() {
        scanner = new Scanner(System.in);
        this.wiseSayingController = new WiseSayingController(this.scanner,
                new WiseSayingServiceImpl(new WiseSayingFileRepository()));
    }

    // 프로그램 실행
    public void run() {
        boolean isNotQuit = true;
        System.out.println("== 명언 앱 ==");

        while (isNotQuit) {
            wiseSayingController.printCommand();

            isNotQuit = handleCommand();
        }
    }

    // 사용자 입력으로부터 커맨드 추출
    public boolean handleCommand() {
        String input = scanner.nextLine();
        Command command = Command.findByCommand(input);

        try {
            switch (command) {
                case REGISTER:
                    wiseSayingController.register();
                    break;
                case LIST:
                    wiseSayingController.showList();
                    break;
                case DELETE:
                    int idx = command.getId(input);
                    wiseSayingController.delete(idx);
                    break;
                case UPDATE:
                    idx = command.getId(input);
                    wiseSayingController.update(idx);
                    break;
                case BUILD:
                    wiseSayingController.build();
                    break;
                case EXIT:
                    return false;
                case INVALID:
                    throw new InvalidCommandInputException();
                case HINT:
                    wiseSayingController.printHintMsg();
                    break;
            }
        } catch (GlobalException e) {
            wiseSayingController.printErrorMsg(e);
        }

        return true;
    }
}
