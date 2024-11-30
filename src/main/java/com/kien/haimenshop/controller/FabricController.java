package com.kien.haimenshop.controller;

import com.kien.haimenshop.service.FabricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FabricController {
    @Autowired
    FabricService fabricService;
    @GetMapping("/fabric")
    public ModelAndView home(@PageableDefault(size = 5) Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("fabric/fabricList");
        return modelAndView;
    }

    @GetMapping("/add-fabric")
    public ModelAndView addClient() {
        ModelAndView modelAndView = new ModelAndView("fabric/addFabric");
        return modelAndView;
    }
}
