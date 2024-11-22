package org.example.myProject.service;

import java.util.List;
import org.example.myProject.entity.WiseSaying;
import org.example.myProject.repository.WiseSayingRepository;

public class WiseSayingServiceImpl implements WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingServiceImpl(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    @Override
    public void register(String author, String msg) {
        wiseSayingRepository.saveWiseSaying(author, msg);
    }

    @Override
    public List<WiseSaying> getWiseSayings() {
        return wiseSayingRepository.getWiseSayings();
    }

    @Override
    public WiseSaying getWiseSaying(int idx) {
        return wiseSayingRepository.findWiseSayingsByNum(idx);
    }

    @Override
    public void delete(int idx) {
        wiseSayingRepository.deleteWiseSaying(idx);
    }

    @Override
    public void update(int idx, String msg, String author) {
        wiseSayingRepository.updateWiseSaying(idx, msg, author);
    }


    @Override
    public int getLastIdx() {
        return wiseSayingRepository.getLastIdx();
    }
}
