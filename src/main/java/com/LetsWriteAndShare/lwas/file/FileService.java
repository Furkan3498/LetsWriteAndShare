package com.LetsWriteAndShare.lwas.file;


import com.LetsWriteAndShare.lwas.configuration.LwasProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {


    @Autowired
    LwasProperties lwasProperties;
    public String saveBase64StringAsFile(String image) {

        String fileName = UUID.randomUUID().toString();

        Path path = Paths.get(lwasProperties.getStorage().getRoot(), lwasProperties.getStorage().getProfile(), fileName);

        try {
            OutputStream outputStream = new FileOutputStream(path.toFile());
            outputStream.write(decodedImage(image));
            outputStream.close();
            return fileName;
        } catch (IOException e) {
           e.printStackTrace();;
        }
        return null;
    }

    public String detectedType(String value) {
        return null;
    }


    private byte[] decodedImage(String encodedImage){
        return  Base64.getDecoder().decode(encodedImage.split(",")[1]);
    }
}
