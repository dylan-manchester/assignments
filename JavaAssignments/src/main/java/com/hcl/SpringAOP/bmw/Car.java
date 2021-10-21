package com.hcl.SpringAOP.bmw;


import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class Car {
    private int id;
    private Set<Seat> seatOptions;
    private Set<Color> colorOptions;

    public Car() {
    }

    public Car(int id, Set<Seat> seatOptions, Set<Color> colorOptions) {
        this.id = id;
        this.seatOptions = seatOptions;
        this.colorOptions = colorOptions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Seat> getSeatOptions() {
        return seatOptions;
    }

    @Autowired(required = false)
    public void setSeatOptions(Set<Seat> seatOptions) {
        this.seatOptions = seatOptions;
    }

    public void addSeatOption(Seat seatOption) {
        this.seatOptions.add(seatOption);
    }

    public void removeSeatOption(Seat seatOption) {
        this.seatOptions.remove(seatOption);
    }

    public Set<Color> getColorOptions() {
        return colorOptions;
    }
    @Autowired(required = false)
    public void setColorOptions(Set<Color> colorOptions) {
        this.colorOptions = colorOptions;
    }

    public void addColorOption(Color colorOption) {
        this.colorOptions.add(colorOption);
    }

    public void removeColorOption(Color colorOption) {
        this.colorOptions.remove(colorOption);
    }

    @Override
    public String toString() {
        return "ID: "+getId()+"\n"
                +"Seat Options: "+getSeatOptions()+"\n"
                +"Color Options: "+getColorOptions()+"\n";
    }

    public void printItemDetails() {
        System.out.println("Car:\n"+toString());
    }
}
