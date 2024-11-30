package com.kien.haimenshop.repository;

import com.kien.haimenshop.entity.Client;
import com.kien.haimenshop.entity.Fabric;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricRepository extends JpaRepository<Fabric, Long> {
    @Query(value = "select f from Fabric f order by f.moneyFabric desc")
    Page<Fabric> findAllFabric(Pageable pageable);

    Fabric findFabricById(Long id);
}
