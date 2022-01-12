package com.example.project_for_parents.service;

import com.example.project_for_parents.entity.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClientService {
    void save(Client client);
    Page<Client> findAllClient(Pageable pageable);
    Page<Client> findClientByName(String name, Pageable pageable);
}
