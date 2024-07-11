package com.project.zosale.controller;

import com.project.zosale.service.ImageFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
@RequestMapping("/api/image/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ImageController {
    @Autowired
    private ImageFileService imageService ;
    @PostMapping("/upload")
    public void uploadImage(@RequestParam MultipartFile file, @RequestParam Long userId) throws IOException {
            imageService.addImage(file,userId);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadImage(@PathVariable("id") Long id) throws
            FileNotFoundException {
        return imageService.getFileById(id);
    }
}
