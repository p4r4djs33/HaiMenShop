package com.kien.haimenshop.service;

import com.kien.haimenshop.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {
    void save(Client client);
    Page<Client> findAllClient(Pageable pageable);
    Page<Client> findClientByName(String name, Pageable pageable);
    Client findClientById(Long id);
}
