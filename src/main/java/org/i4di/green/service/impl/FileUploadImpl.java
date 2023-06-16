package org.i4di.green.service.impl;
import com.cloudinary.Cloudinary;
import org.i4di.green.service.FileUpload;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Collections;
import java.util.UUID;
@Service
public class FileUploadImpl implements FileUpload {
    private final Cloudinary cloudinary;

    public FileUploadImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {
        return cloudinary.uploader()
                .upload(multipartFile.getBytes(),
                        Collections.singletonMap("public_id", UUID.randomUUID().toString()))//sets random unique name for the file
                .get("url")//vraca url uploadovanog fajla
                .toString();
    }
}