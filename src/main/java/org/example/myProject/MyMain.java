package org.example.myProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class MyMain {
    static int status;
    static int wsNum;
    static Map<Integer, WiseSaying> map;
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        status = -1;
        wsNum = 1;
        map = new TreeMap<>(Comparator.reverseOrder());
        System.out.println("== 명언 앱 ==");
        br = new BufferedReader(new InputStreamReader(System.in));

        do {
            System.out.print("명령) ");
            checkCommand(br.readLine());
            doCommand();
        } while (status != 0);
    }

    public static void checkCommand(String command) {
        switch (command) {
            case "종료":
                status = 0;
                break;
            case "등록":
                status = 1;
                break;
            case "목록":
                status = 2;
                break;
            case "삭제":
                status = 3;
                break;
            case "수정":
                status = 4;
                break;
            default:
                status = -1;
                System.out.println("종료, 등록, 목록, 삭제, 수정 중 입력해주세요.");
                break;
        }
    }

    public static void doCommand() throws IOException {
        try {
            switch (status) {
                case -1:
                case 0:
                    break;
                case 1:
                    register();
                    break;
                case 2:
                    checkList();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    update();
                    break;
            }
        } catch (WsNumNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void register() throws IOException {
        System.out.print("명언 : ");
        String wiseSaying = br.readLine();
        System.out.print("작가 : ");
        String author = br.readLine();

        WiseSaying ws = new WiseSaying(wsNum, author, wiseSaying);
        map.put(wsNum, ws);
        System.out.println(ws.getNum() + "번 명언이 등록되었습니다.");
        wsNum++;
    }

    public static void checkList() throws IOException {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (WiseSaying ws : map.values()) {
            System.out.println(ws.toString());
        }
    }

    public static void delete() throws IOException {
        System.out.print("?id=");
        int delNum = Integer.parseInt(br.readLine());

        if (map.containsKey(delNum)) {
            map.remove(delNum);
        } else {
            throw new WsNumNotFoundException(delNum + "번 명언은 존재하지 않습니다.");
        }

        System.out.println(delNum + "번 명언이 삭제되었습니다.");
    }

    public static void update() throws IOException {
        System.out.print("?id=");
        int updateNum = Integer.parseInt(br.readLine());

        if (map.containsKey(updateNum)) {
            WiseSaying curWs = map.get(updateNum);

            System.out.println("명언(기존) : " + curWs.getWiseSaying());
            System.out.print("명언 : ");
            curWs.setWiseSaying(br.readLine());
            System.out.println("작가(기존) : " + curWs.getAuthor());
            System.out.print("작가 : ");
            curWs.setAuthor(br.readLine());
        } else {
            throw new WsNumNotFoundException(updateNum + "번 명언은 존재하지 않습니다");
        }
    }
}

class WsNumNotFoundException extends RuntimeException {
    public WsNumNotFoundException(String msg) {
        super(msg);
    }
}

class WiseSaying {
    private final int num;
    private String author;
    private String wiseSaying;

    public WiseSaying(int num, String author, String wiseSaying) {
        this.num = num;
        this.author = author;
        this.wiseSaying = wiseSaying;
    }

    public int getNum() {
        return num;
    }

    public String getAuthor() {
        return author;
    }

    public String getWiseSaying() {
        return wiseSaying;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setWiseSaying(String wiseSaying) {
        this.wiseSaying = wiseSaying;
    }

    @Override
    public String toString() {
        return this.num + " / " + this.author + " / " + this.wiseSaying;
    }
}

/*
// 테스트용 스캐너 생성 코드
import java.io.*;
import java.util.Scanner;

public class TestUtil {
    // gen == generate 생성하다.
    public static Scanner genScanner(final String input) {
        final InputStream in = new ByteArrayInputStream(input.getBytes());

        return new Scanner(in);
    }

    public static ByteArrayOutputStream setOutToByteArray() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        return output;
    }

    public static void clearSetOutToByteArray(final ByteArrayOutputStream output) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        try {
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
 */