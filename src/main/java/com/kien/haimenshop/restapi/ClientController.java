package com.kien.haimenshop.restapi;

import com.kien.haimenshop.entity.Client;
import com.kien.haimenshop.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    ClientService clientService;
    @GetMapping("/client")
    public Client getClientById(@RequestParam(value = "id", required = false) Long id) {
        return clientService.findClientById(id);
    }

    @GetMapping("/client/take/product")
    public Client clientTakeProduct(@RequestParam(value = "id", required = false) Long id,
                                    @RequestParam(value = "isTaken", required = false) Boolean isTaken) {
        System.out.println("Call clientTakeProduct:" + id + isTaken);
        Client client = clientService.findClientById(id);
        client.setIsTaken(isTaken);
        System.out.println(client);
        clientService.save(client);
        return client;
    }

}
