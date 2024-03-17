package com.inventory.appuserservice.makerchecker.repository;

import com.inventory.appuserservice.makerchecker.entity.MakerChecker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MakerCheckerRepository extends JpaRepository<MakerChecker, String>, MakerCheckerRepositoryCustom {
}
