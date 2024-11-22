package org.example.myProject.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.example.myProject.entity.WiseSaying;
import org.example.myProject.exception.FailedFindException;

public class WiseSayingRepository {
    private int curIdx = 1;
    private List<WiseSaying> wiseSayingList = new ArrayList<>();

    public void saveWiseSaying(String author, String msg) {
        WiseSaying wiseSaying = new WiseSaying(curIdx, author, msg);
        wiseSayingList.add(wiseSaying);

        curIdx++;
    }

    public List<WiseSaying> getWiseSayings() {
        return wiseSayingList.reversed();
    }

    public void deleteWiseSaying(int idx) {
        WiseSaying wiseSayings = findWiseSayingsByNum(idx);

        if (wiseSayings == null) {
            throw new FailedFindException(idx);
        }

        wiseSayingList.remove(wiseSayings);
    }

    public void updateWiseSaying(int idx, String msg, String author) {
        WiseSaying wiseSaying = findWiseSayingsByNum(idx);

        if (wiseSaying == null) {
            throw new FailedFindException(idx);
        }

        wiseSaying.setSaying(msg);
        wiseSaying.setAuthor(author);
    }

    public WiseSaying findWiseSayingsByNum(int idx) {
        Optional<WiseSaying> findWiseSaying = wiseSayingList.stream().filter(wiseSaying -> wiseSaying.getNum() == idx)
                .findFirst();

        return findWiseSaying.orElseThrow(() -> new FailedFindException(idx));
    }

    public int getLastIdx() {
        return this.curIdx;
    }
}
