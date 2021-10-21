package com.hcl.SpringAOP.bmw;

public class Color {
    private int id;
    private double price;
    private String color;

    public Color() {}

    public Color(int id, double price, String color) {
        this.id = id;
        this.price = price;
        this.color = color;
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
        return getColor();
    }
}
