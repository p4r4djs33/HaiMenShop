package com.example.project_for_parents.controller;


import com.example.project_for_parents.entity.Client;
import com.example.project_for_parents.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    ClientService clientService;

    @GetMapping("/")
    public ModelAndView home(@PageableDefault(size = 10) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("client/clientList");
        modelAndView.addObject("clientList", clientService.findAllClient(pageable));
        return modelAndView;
    }

    @GetMapping("/add-client")
    public ModelAndView addClient() {
        ModelAndView modelAndView = new ModelAndView("client/addClient");
        return modelAndView;
    }

    @PostMapping("/client/save")
    public ModelAndView clientSave(@RequestParam(value = "name", required = false) String name,
                                   @RequestParam(value = "typeFabric", required = false) String typeFabric,
                                   @RequestParam(value = "moneyFabric", required = false) String moneyFabric,
                                   @RequestParam(value = "measure", required = false) String measure,
                                   @RequestParam(value = "moreInformation", required = false) String moreInformation,
                                   @RequestParam(value = "deposit", required = false) String deposit,
                                   @RequestParam(value = "dateEnd", required = false) String dateEnd) {
        ModelAndView modelAndView = new ModelAndView("redirect:/");
        System.out.println(name + " " + typeFabric + " " + moneyFabric + " " + measure + " " + moreInformation + " " + deposit + " " + dateEnd);
        Client client = new Client();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        System.out.println(formatter.format(date));

        client.setName(name);
        client.setTypeFabric(typeFabric);
        client.setMoneyFabric(moneyFabric);
        client.setMeasure(measure);
        client.setMoreInformation(moreInformation);
        client.setDeposit(deposit);
        client.setDateCreation(formatter.format(date));
        client.setDateEnd(dateEnd);
        clientService.save(client);

        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(@RequestParam(value = "name", required = false) String name,
                               @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("client/clientList");

        Page<Client> clientList = clientService.findClientByName(name, pageable);

        modelAndView.addObject("clientList", clientList);

        return modelAndView;
    }



}
