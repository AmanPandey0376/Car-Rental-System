package com.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private List<Rental> rentalHistory;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.rentalHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentalHistory() {
        return rentalHistory;
    }

    public void addRental(Rental rental) {
        rentalHistory.add(rental);
    }
}
