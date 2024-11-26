package org.example.myProject.service;

import java.util.List;
import org.example.myProject.entity.WiseSaying;

public interface WiseSayingService {
    public void register(String author, String msg);
    public List<WiseSaying> getWiseSayings();
    public WiseSaying getWiseSaying(int idx);
    public void delete(int idx);
    public void update(int idx, String msg, String author);
    public void build();

    public int getLastIdx();
}
