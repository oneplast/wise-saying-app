package org.example.myProject.repository;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import org.example.myProject.entity.WiseSaying;
import org.example.myProject.exception.*;

public class WiseSayingFileRepository implements WiseSayingRepository {
    private int curIdx = 0;
    private final String DIR_PATH;
    private final List<WiseSaying> wiseSayingList = new ArrayList<>();

    // 레포지토리 생성 시 Base Directory Path 지정
    public WiseSayingFileRepository() {
        DIR_PATH = "db/wiseSaying/";
        this.loadWiseSayings();
    }

    // 명언 등록
    public void saveWiseSaying(String author, String msg) {
        WiseSaying wiseSaying = new WiseSaying(++curIdx, author, msg);

        saveFile(wiseSaying);
        wiseSayingList.add(wiseSaying);
        saveLastIdx();
    }

    // 명언 목록을 번호 기준 역순으로 반환
    public List<WiseSaying> getWiseSayings() {
        return wiseSayingList.reversed();
    }

    // 명언 삭제
    public void deleteWiseSaying(int idx) {
        boolean wiseSaying = wiseSayingList.removeIf(ws -> ws.getNum() == idx);

        if (!wiseSaying) {
            throw new FailedFindException(idx);
        }

        deleteFile(idx);
    }

    // 명언 수정
    public void updateWiseSaying(int idx, String msg, String author) {
        // 파일을 수정하기 위한 객체
        WiseSaying updatedWiseSaying = new WiseSaying(idx, author, msg);

        // 메모리 객체 수정
        WiseSaying wiseSaying = findWiseSayingsByNum(idx);

        wiseSaying.setSaying(msg);
        wiseSaying.setAuthor(author);

        // 파일 수정
        this.updateFile(idx, wiseSaying, updatedWiseSaying);
    }

    // 번호가 일치하는 명언 반환
    public WiseSaying findWiseSayingsByNum(int idx) {
        Optional<WiseSaying> findWiseSaying = wiseSayingList.stream().filter(wiseSaying -> wiseSaying.getNum() == idx)
                .findFirst();

        return findWiseSaying.orElseThrow(() -> new FailedFindException(idx));
    }

    // 파일에 저장
    public void saveFile(WiseSaying wiseSaying) {
        String filePath = DIR_PATH + curIdx + ".json";
        Path path = Paths.get(filePath);

        try {
            Files.createDirectories(Paths.get(DIR_PATH));
            Files.writeString(path, this.wiseToJson(wiseSaying));
        } catch (IOException e) {
            throw new FailedFileSaveException();
        }
    }

    // 파일 삭제
    public void deleteFile(int idx) {
        Path path = Paths.get(DIR_PATH + idx + ".json");

        if (Files.exists(path)) {
            try {
                Files.delete(path);
            } catch (IOException e) {
                throw new FailedFileDeleteException();
            }
        }
    }

    // 번호가 일치하는 명언 파일 수정
    public void updateFile(int idx, WiseSaying wiseSaying, WiseSaying updatedWiseSaying) {
        Path path = Paths.get(DIR_PATH + idx + ".json");

        try {
            Files.writeString(path, wiseToJson(updatedWiseSaying));
        } catch (IOException e) {
            throw new FailedFileUpdateException();
        }
    }

    // 빌드 진행
    public void buildJson() {
        Path path = Paths.get(DIR_PATH + "data.json");
        String allJsons = String.join(",\n", this.wrapJsons());

        try {
            Files.writeString(path, "[\n");
            Files.writeString(path, allJsons, StandardOpenOption.APPEND);
            Files.writeString(path, "\n]", StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new FailedFileBuildException();
        }
    }

    // 파일에서 명언 가져오기
    public void loadWiseSayings() {
        Path filePath = Paths.get(DIR_PATH);

        try {
            Files.list(filePath).filter(path -> {
                String name = String.valueOf(path.getFileName());
                return name.endsWith(".json") && !name.startsWith("data");
            }).forEach(this::jsonToWise);  // 이 부분부터 수정
        } catch (IOException e) {
            throw new FailedLoadWiseException();
        }

        this.curIdx = getLastIdxFromFile();
    }

    // 마지막 번호 파일에 저장
    public void saveLastIdx() {
        Path path = Paths.get(DIR_PATH + "lastId.txt");

        try {
            if (!Files.exists(path)) {
                Files.writeString(path, "1");
            } else {
                Files.writeString(path, String.valueOf(this.curIdx));
            }
        } catch (IOException e) {
            throw new FailedSaveLastIdxException();
        }
    }

    // 마지막 번호 반환
    public int getLastIdxFromFile() {
        Path path = Paths.get(DIR_PATH + "lastId.txt");
        try {
            return Integer.parseInt(Files.readString(path));
        } catch (IOException e) {
            return this.curIdx;
        }
    }

    // 메모리에서 마지막 번호 반환
    public int getLastIdx() {
        return this.curIdx;
    }

    // 명언 객체를 json 형식으로
    public String wiseToJson(WiseSaying wiseSaying) {
        return "{\n" +
                "  \"id\": " + wiseSaying.getNum() + ",\n" +
                "  \"content\": \"" + wiseSaying.getSaying() + "\",\n" +
                "  \"author\": \"" + wiseSaying.getAuthor() + "\"\n" +
                "}";
    }

    public void jsonToWise(Path path) {
        try {
            String allFileData = Files.readString(path);

            String[] splitData = allFileData.replaceAll("[ \n\"{}]", "").split(",");
            this.wiseSayingList.add(loadWiseFromJson(splitData));
        } catch (IOException e) {
            throw new FailedReadFileException();
        }
    }

    public WiseSaying loadWiseFromJson(String[] datas) {
        List<String> result = new ArrayList<>();
        for (String data : datas) {
            String[] entry = data.split(":");
            result.add(entry[1]);
        }

        return new WiseSaying(Integer.parseInt(result.get(0)), result.get(1), result.get(2));
    }

    // 모든 명언 목록을 json 리스트로 반환
    public List<String> wrapJsons() {
        return wiseSayingList.stream().map(this::wiseToJson).toList();
    }
}
