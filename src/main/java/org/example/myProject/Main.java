package org.example.myProject;

import org.example.myProject.controller.WiseSayingController;
import org.example.myProject.repository.WiseSayingRepository;
import org.example.myProject.service.WiseSayingServiceImpl;

public class Main {
    public static void main(String[] args) {
        WiseSayingController wiseSayingController = new WiseSayingController(new WiseSayingServiceImpl(new WiseSayingRepository()));

        wiseSayingController.run();
    }
}
