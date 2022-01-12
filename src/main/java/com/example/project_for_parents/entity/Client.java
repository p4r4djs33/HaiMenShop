package com.example.project_for_parents.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
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

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeFabric() {
        return typeFabric;
    }

    public void setTypeFabric(String typeFabric) {
        this.typeFabric = typeFabric;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getMoreInformation() {
        return moreInformation;
    }

    public void setMoreInformation(String moreInformation) {
        this.moreInformation = moreInformation;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getMoneyFabric() {
        return moneyFabric;
    }

    public void setMoneyFabric(String moneyFabric) {
        this.moneyFabric = moneyFabric;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", typeFabric='" + typeFabric + '\'' +
                ", moneyFabric='" + moneyFabric + '\'' +
                ", measure='" + measure + '\'' +
                ", moreInformation='" + moreInformation + '\'' +
                ", deposit='" + deposit + '\'' +
                ", dateCreation=" + dateCreation +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
