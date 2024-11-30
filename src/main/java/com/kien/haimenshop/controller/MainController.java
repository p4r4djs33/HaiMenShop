package com.kien.haimenshop.controller;


import com.kien.haimenshop.entity.Client;
import com.kien.haimenshop.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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

    @GetMapping("/addClient")
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
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Tạo đối tượng Date hiện tại
        Date dateNow = new Date();

        // Chuyển từ Date sang LocalDateTime
        LocalDateTime localDateTime = dateNow.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        String formattedDate = localDateTime.format(outputFormatter);

        client.setName(name.toLowerCase());
        client.setTypeFabric(typeFabric);
        client.setMoneyFabric(moneyFabric);
        client.setMeasure(measure);
        client.setMoreInformation(moreInformation);
        client.setDeposit(deposit);
        client.setDateCreation(formattedDate);
        LocalDate dateEndFormat = LocalDate.parse(dateEnd, inputFormatter);
        client.setDateEnd(dateEndFormat.format(outputFormatter));
        client.setIsTaken(false);
        clientService.save(client);

        return modelAndView;
    }

/*    @PostMapping("/client/update")
    public ModelAndView updateTakenStatus(@RequestBody TakenStatusUpdateRequest request) {
        // Tìm khách hàng theo ID và cập nhật trạng thái is_taken
        Client client = clientService.findById(request.getId());
        customer.setTaken(request.isTaken());
        customerService.save(customer);

        return ResponseEntity.ok().build();
    }*/


    @GetMapping("/search")
    public ModelAndView search(){
        ModelAndView modelAndView = new ModelAndView("client/searchClient");
//        modelAndView.addObject("clientListSearch", clientService.findAllClient(pageable));
        return modelAndView;
    }

    @GetMapping("/searchClient")
    public ModelAndView searchClient(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(required = false, defaultValue = "0") int page) {
        ModelAndView modelAndView = new ModelAndView("client/searchClient");
        Pageable pageable = PageRequest.of(page, 10); // 5 là số bản ghi mỗi trang
        name = name.toLowerCase();
        Page<Client> clientListSearch = clientService.findClientByName(name, pageable);
        // In thông tin tổng quan
        System.out.println("Page Number: " + clientListSearch.getNumber());
        System.out.println("Total Pages: " + clientListSearch.getTotalPages());
        System.out.println("Total Elements: " + clientListSearch.getTotalElements());
        System.out.println("Page Size: " + clientListSearch.getSize());

// In danh sách các Client
        System.out.println("Clients on current page:");
        for (Client client : clientListSearch.getContent()) {
            System.out.println(client);
        }
        modelAndView.addObject("clientListSearch", clientListSearch);
        modelAndView.addObject("name", name);

        return modelAndView;
    }



}
