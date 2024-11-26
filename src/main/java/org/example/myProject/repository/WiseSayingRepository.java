package org.example.myProject.repository;

import java.util.List;
import org.example.myProject.entity.WiseSaying;

interface WiseSayingRepository {
    void saveWiseSaying(String author, String msg);

    List<WiseSaying> getWiseSayings();

    public void deleteWiseSaying(int idx);

    void updateWiseSaying(int idx, String msg, String author);
}
