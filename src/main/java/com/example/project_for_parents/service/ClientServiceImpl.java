package com.example.project_for_parents.service;

import com.example.project_for_parents.entity.Client;
import com.example.project_for_parents.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Override
    public void save(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Page<Client> findAllClient(Pageable pageable) {
        return clientRepository.findAllClient(pageable);
    }

    @Override
    public Page<Client> findClientByName(String name, Pageable pageable) {
        return clientRepository.findClientByName(name, pageable);
    }
}
