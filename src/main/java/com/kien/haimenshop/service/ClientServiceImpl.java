package com.kien.haimenshop.service;

import com.kien.haimenshop.entity.Client;
import com.kien.haimenshop.repository.ClientRepository;
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

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findClientById(id);
    }

    @Override
    public List<Client> findAllClient() {
        return clientRepository.findAll();
    }
}
