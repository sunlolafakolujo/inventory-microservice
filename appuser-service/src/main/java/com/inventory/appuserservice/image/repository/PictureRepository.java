package com.inventory.appuserservice.image.repository;

import com.inventory.appuserservice.image.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long> {
}
