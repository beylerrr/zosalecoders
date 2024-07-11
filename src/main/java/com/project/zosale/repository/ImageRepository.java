package com.project.zosale.repository;

import com.project.zosale.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ImageRepository extends JpaRepository<Image,Long> {

    public Optional<Image> findImageByUserId(Long userId);
    public Image findImageById(Long imageId);
}
