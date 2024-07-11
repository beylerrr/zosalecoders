package com.project.zosale.service;

import com.project.zosale.entity.Image;
import com.project.zosale.entity.User;
import com.project.zosale.repository.ImageRepository;
import com.project.zosale.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

@Service
public class ImageFileService {
    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository ;

    public void addImage(MultipartFile file , Long userId) throws IOException {
        Optional<User> userOptional = userRepository.findById(userId);

        Optional<Image> existingImageOptional = imageRepository.findImageByUserId(userId);

        if(existingImageOptional.isPresent()){
            Image existingImage = existingImageOptional.get();
            existingImage.setName(file.getOriginalFilename());
            existingImage.setFileData(file.getBytes());
            existingImage.setContentType(file.getContentType());
            imageRepository.save(existingImage);
        }else{
            User userExisting = userOptional.get();
            Image  imageFile =  new Image();
            imageFile.setContentType(file.getContentType());
            imageFile.setFileData(file.getBytes());
            imageFile.setName(file.getOriginalFilename());
            imageFile.setUserId(userId);
            imageRepository.save(imageFile);
            userExisting.setImage(imageFile);
            userRepository.save(userExisting);
        }
    }

    public ResponseEntity<?> getFileById(Long id ) throws FileNotFoundException{
        Image imageForOptional = imageRepository.findImageById(id);

        if(imageForOptional !=null){
            return ResponseEntity.status(HttpStatus.OK)
                    .header("Content-Type",imageForOptional.getContentType())
                    .body(imageForOptional.getFileData());
        }
        throw new FileNotFoundException("file not  found");



    }
}
