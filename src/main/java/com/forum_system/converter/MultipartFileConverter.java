package com.forum_system.converter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Component
public class MultipartFileConverter implements Converter<MultipartFile, String> {
    @Value("${com.forum_system.upload.path}")
    private String path;

    @Override
    public String convert(MultipartFile source) {
        if (!source.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + source.getOriginalFilename();

            try {
                source.transferTo(new File(path + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return fileName;

        }
        return null;
    }
}
