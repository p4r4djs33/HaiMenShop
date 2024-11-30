package com.kien.haimenshop.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String typeFabric; //loại vải
    private String moneyFabric; //tiền vải
    private String measure; //số đo
    private String moreInformation; //thông tin thêm
    private String deposit; //đặt cọc
    private String dateCreation; //ngày may
    private String dateEnd; //ngày lấy
    private Boolean isTaken; //đã lấy;
    public Client() {
    }
}
