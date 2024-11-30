package com.kien.haimenshop.restapi;

import com.kien.haimenshop.entity.Fabric;
import com.kien.haimenshop.service.FabricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FabricRestController {
    @Autowired
    private FabricService fabricService;

    @GetMapping("/fabric/list")
    public List<Fabric> getAllFabric(){
        return fabricService.findAllFabric();
    }

    @PostMapping("/fabric/add")
    public Fabric fabricAdd(@RequestBody Fabric fabric) {
        Fabric fabricNew = new Fabric();
        fabricNew.setTypeFabric(fabric.getTypeFabric());
        fabricNew.setMoneyFabric(fabric.getMoneyFabric());
        fabricService.save(fabricNew);
        return fabricNew;
    }

    @PostMapping("/fabric/update/{id}")
    public Fabric fabricUpdate(@PathVariable String id, @RequestBody Fabric fabric) {
        Fabric fabricOld = fabricService.findFabricById(Long.valueOf(id));
        fabricOld.setTypeFabric(fabric.getTypeFabric());
        fabricOld.setMoneyFabric(fabric.getMoneyFabric());
        fabricService.save(fabricOld);
        return fabricOld;
    }
}
