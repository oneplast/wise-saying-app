package org.example.myProject.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.myProject.entity.WiseSaying;
import org.example.myProject.exception.FailedFindException;

public class WiseSayingMemoryRepository implements WiseSayingRepository {
    private int curIdx = 1;
    private final List<WiseSaying> wiseSayingList = new ArrayList<>();

    // 명언 등록
    public void saveWiseSaying(String author, String msg) {
        WiseSaying wiseSaying = new WiseSaying(curIdx, author, msg);

        wiseSayingList.add(wiseSaying);

        curIdx++;
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
    }

    // 명언 수정
    public void updateWiseSaying(int idx, String msg, String author) {
        WiseSaying wiseSaying = findWiseSayingsByNum(idx);

        wiseSaying.setSaying(msg);
        wiseSaying.setAuthor(author);
    }

    // 번호가 일치하는 명언 반환
    public WiseSaying findWiseSayingsByNum(int idx) {
        Optional<WiseSaying> findWiseSaying = wiseSayingList.stream().filter(wiseSaying -> wiseSaying.getNum() == idx)
                .findFirst();

        return findWiseSaying.orElseThrow(() -> new FailedFindException(idx));
    }

    // 마지막 번호 반환
    public int getLastIdx() {
        return this.curIdx;
    }
}
