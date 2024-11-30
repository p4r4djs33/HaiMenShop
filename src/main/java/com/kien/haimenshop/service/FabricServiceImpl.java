package com.kien.haimenshop.service;

import com.kien.haimenshop.entity.Fabric;
import com.kien.haimenshop.repository.FabricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabricServiceImpl implements FabricService{
    @Autowired
    private FabricRepository fabricRepository;
    @Override
    public Page<Fabric> findAllFabric(Pageable pageable) {
        return fabricRepository.findAllFabric(pageable);
    }

    @Override
    public Fabric findFabricById(Long id) {
        return fabricRepository.findFabricById(id);
    }

    @Override
    public List<Fabric> findAllFabric() {
        return fabricRepository.findAll();
    }

    @Override
    public void save(Fabric fabric) {
        fabricRepository.save(fabric);
    }
}
