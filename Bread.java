package com.example.jpa.demojpa.test;



import java.io.Serializable;
import java.util.Date;

public class Bread implements Serializable {
    private  String type;
    private double price;
    private int expiration;

    private Date create;

    public Bread(String type, double price, int expiration, Date create){
        this.type=type;
        this.price=price;
        this.expiration=expiration;
        this.create=create;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getExpiration() {
        return expiration;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }
}
