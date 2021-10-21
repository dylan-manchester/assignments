package com.hcl.SpringAOP.bmw;

public class Seat {
    private int id;
    private double price;
    private String color;
    private String fabric;

    public Seat() {}

    public Seat(int id, double price, String color, String fabric) {
        this.id = id;
        this.price = price;
        this.color = color;
        this.fabric = fabric;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getColor()+" -> "+getPrice();
    }

    public String getFabric() {
        return fabric;
    }

    public void setFabric(String fabric) {
        this.fabric = fabric;
    }
}

