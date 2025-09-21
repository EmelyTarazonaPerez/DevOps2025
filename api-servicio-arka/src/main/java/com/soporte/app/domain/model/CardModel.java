package com.soporte.app.domain.model;
import java.util.Date;
import java.util.List;

public class CardModel {
    private Integer id;
    private String state;
    private Integer idClient;
    private Date updatedAt;
    private List<CardProductModel> details;

    public CardModel() {
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {this.state = state;}
    public Integer getIdClient() {
        return idClient;
    }
    public void setIdClient(Integer idClient) {
        this.idClient = idClient;
    }
    public Date getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    public List<CardProductModel> getDetails() {
        return details;
    }
    public void setDetails(List<CardProductModel> products) {
        this.details = products;
    }
}
