package org.i4di.green.api;

import org.i4di.green.service.FileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*") //makes server error
public class FileUploadApi {
    private final FileUpload fileUpload;

    public FileUploadApi(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    @RequestMapping("/")
    public String home(){
        return "home";
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image")MultipartFile multipartFile) throws IOException {
        String imageURL = fileUpload.uploadFile(multipartFile);
        return imageURL;
    }

/*    @PostMapping("/upload")
    public String uploadFile(@RequestParam("image")MultipartFile multipartFile,
                             Model model) throws IOException {
        String imageURL = fileUpload.uploadFile(multipartFile);
        model.addAttribute("imageURL",imageURL);
        return "gallery";
    }*/
}