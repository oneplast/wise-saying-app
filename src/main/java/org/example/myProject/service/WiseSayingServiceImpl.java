package org.example.myProject.service;

import java.util.List;
import org.example.myProject.entity.WiseSaying;
import org.example.myProject.repository.WiseSayingFileRepository;

public class WiseSayingServiceImpl implements WiseSayingService {
    private final WiseSayingFileRepository wiseSayingRepository;

    // 의존성 주입
    public WiseSayingServiceImpl(WiseSayingFileRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    // 명언 등록
    @Override
    public void register(String author, String msg) {
        wiseSayingRepository.saveWiseSaying(author, msg);
    }

    // 저장된 모든 명언들 가져오기
    @Override
    public List<WiseSaying> getWiseSayings() {
        return wiseSayingRepository.getWiseSayings();
    }

    // idx 번의 명령 가져오기
    @Override
    public WiseSaying getWiseSaying(int idx) {
        return wiseSayingRepository.findWiseSayingsByNum(idx);
    }

    // 명언 삭제
    @Override
    public void delete(int idx) {
        wiseSayingRepository.deleteWiseSaying(idx);
    }

    // 명언 수정
    @Override
    public void update(int idx, String msg, String author) {
        wiseSayingRepository.updateWiseSaying(idx, msg, author);
    }

    @Override
    public void build() {
        wiseSayingRepository.buildJson();
    }

    // 마지막으로 저장된 번호 가져오기
    @Override
    public int getLastIdx() {
        return wiseSayingRepository.getLastIdx();
    }
}
