package com.LetsWriteAndShare.lwas.file;


import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Base64;
import java.util.UUID;

@Service
public class FileService {
    public String saveBase64StringAsFile(String image) {

        String fileName = UUID.randomUUID().toString();

        File file =new File(fileName);

        try {
            OutputStream outputStream = new FileOutputStream(file);
            byte[] base64Decoded = Base64.getDecoder().decode(image);
            outputStream.write(base64Decoded);
            outputStream.close();
            return fileName;
        } catch (IOException e) {
           e.printStackTrace();;
        }
        return null;
    }
}
