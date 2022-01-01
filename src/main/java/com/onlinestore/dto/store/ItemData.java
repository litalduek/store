package com.onlinestore.dto.store;

public class ItemData {

    private Long id;
    private String name;
    private double price;
    private int stock;

    public ItemData(){}

    public ItemData(Long id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
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

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    @Override
    public String toString() {
        return "{id : " + id +
                ", name : " + name +
                ", price : " + price +
                ", stock : " + stock + "}";
    }
}
