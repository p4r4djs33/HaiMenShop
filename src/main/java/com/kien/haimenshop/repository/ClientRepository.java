package com.kien.haimenshop.repository;

import com.kien.haimenshop.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    @Query(value = "select c from Client c where c.name like %:name% order by c.isTaken asc, c.dateEnd asc")
    Page<Client> findClientByName(@Param("name") String name, Pageable pageable);
    @Query(value = "select c from Client c order by c.isTaken asc, c.dateEnd asc ")
    Page<Client> findAllClient(Pageable pageable);
    Client findClientById(Long id);
    @Query(value = "select c from Client c order by c.isTaken asc, c.dateEnd asc ")
    List<Client> findAllClient();
}
