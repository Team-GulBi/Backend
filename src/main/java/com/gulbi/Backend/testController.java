package com.gulbi.Backend;

import com.gulbi.Backend.global.util.FileSender;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class testController {
    private final FileSender fileSender;

    public testController(FileSender fileSender) {
        this.fileSender = fileSender;
    }

    @PostMapping(value = "/files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String response = fileSender.sendFile(file);
        return response;
    }
}
