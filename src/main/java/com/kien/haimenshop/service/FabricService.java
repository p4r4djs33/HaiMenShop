package com.kien.haimenshop.service;

import com.kien.haimenshop.entity.Fabric;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FabricService {
    void save(Fabric fabric);
    Page<Fabric> findAllFabric(Pageable pageable);
    Fabric findFabricById(Long id);
    List<Fabric> findAllFabric();
}
