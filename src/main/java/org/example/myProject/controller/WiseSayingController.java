package org.example.myProject.controller;

import java.util.Scanner;
import org.example.myProject.entity.WiseSaying;
import org.example.myProject.exception.GlobalException;
import org.example.myProject.service.WiseSayingService;

public class WiseSayingController {
    private final WiseSayingService wiseSayingService;
    private final Scanner scanner;

    // 의존성 주입
    public WiseSayingController(Scanner scanner, WiseSayingService wiseSayingService) {
        this.scanner = scanner;
        this.wiseSayingService = wiseSayingService;
    }

    // 명언 등록
    public void register() {
        printRequestWiseSaying();
        String msg = scanner.nextLine();

        printRequestAuthor();
        String author = scanner.nextLine();

        wiseSayingService.register(author, msg);
        int idx = wiseSayingService.getLastIdx();

        printCompleteRegisterMsg(idx);
    }

    // 명언 목록
    public void showList() {
        printListFormatMsg();
        wiseSayingService.getWiseSayings().forEach(System.out::println);
    }

    // 명언 삭제
    public void delete(int idx) {
        wiseSayingService.delete(idx);

        printCompleteDeleteMsg(idx);
    }

    // 명언 수정
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

    // 명언 파일에 빌드
    public void build() {
        wiseSayingService.build();

        printCompleteBuildMsg();
    }

    // 기존 명언 불러오기
    public WiseSaying getWiseSaying(int idx) {
        return wiseSayingService.getWiseSaying(idx);
    }

    // 명령 요구문 출력
    public void printCommand() {
        System.out.print("명령) ");
    }

    // 명언 목록 출력 시작문
    public void printListFormatMsg() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("-----------------------");
    }

    // 명언 등록 성공 출력문
    public void printCompleteRegisterMsg(int idx) {
        System.out.println(idx + "번 명언이 등록되었습니다.");
    }

    // 명언 삭제 성공 출력문
    public void printCompleteDeleteMsg(int idx) {
        System.out.println(idx + "번 명언이 삭제되었습니다.");
    }

    // 명언 빌드 성공 출력문
    private void printCompleteBuildMsg() {
        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }

    // 입력할 수 있는 명령 종류 출력
    public void printHintMsg() {
        System.out.println("등록, 목록, 삭제?id=(숫자), 수정?id=(숫자), 빌드, 종료");
    }

    // 에러 메시지 출력
    public void printErrorMsg(GlobalException e) {
        System.out.println(e.getMessage());
    }

    // 명언 입력 요구문 출력
    public void printRequestWiseSaying() {
        System.out.print("명언 : ");
    }

    // 작가 입력 요구문 출력
    public void printRequestAuthor() {
        System.out.print("작가 : ");
    }

    // 저장된 명언 출력
    public void printExistingWiseSaying(WiseSaying wiseSaying) {
        System.out.println("명언(기존) : " + wiseSaying.getSaying());
    }

    // 저장된 작가 출력
    public void printExistingAuthor(WiseSaying wiseSaying) {
        System.out.println("작가(기존) : " + wiseSaying.getAuthor());
    }
}
