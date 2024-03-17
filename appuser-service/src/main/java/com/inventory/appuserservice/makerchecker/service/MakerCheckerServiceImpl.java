package com.inventory.appuserservice.makerchecker.service;


import com.inventory.appuserservice.makerchecker.entity.MakerChecker;
import com.inventory.appuserservice.makerchecker.exception.MakerCheckerNotFoundException;
import com.inventory.appuserservice.makerchecker.repository.MakerCheckerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakerCheckerServiceImpl implements MakerCheckerService {
    @Autowired
    private MakerCheckerRepository makerCheckerRepository;

    @Override
    public MakerChecker fetchMakerCheckerById(String id) {
        return makerCheckerRepository.findById(id)
                .orElseThrow(()->new MakerCheckerNotFoundException("MakerChecker ID "+id+" not found"));
    }

    @Override
    public List<MakerChecker> fetchByCheckerId(Long checkerId, int pageNumber, int pageSize) {
        return makerCheckerRepository.findByCheckerId(checkerId, PageRequest.of(pageNumber, pageSize));
    }

    @Override
    public MakerChecker fetchByEntityId(Long entityId){

        return makerCheckerRepository.findByEntityId(entityId)
                .orElseThrow(()->new MakerCheckerNotFoundException("Entity ID "+entityId+" not found"));
    }

    @Override
    public List<MakerChecker> fetchAllMakerCheckers(int pageNumber, int pageSize) {
        return makerCheckerRepository.findAll(PageRequest.of(pageNumber,pageSize)).toList();
    }
}
