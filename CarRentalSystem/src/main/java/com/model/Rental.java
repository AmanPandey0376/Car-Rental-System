package com.model;

import java.time.LocalDate;

public class Rental {
    private String rentalId;
    private User user;
    private Car car;
    private LocalDate rentalDate;
    private LocalDate returnDate;

    public Rental(String rentalId, User user, Car car, LocalDate rentalDate) {
        this.rentalId = rentalId;
        this.user = user;
        this.car = car;
        this.rentalDate = rentalDate;
        this.returnDate = null;
    }

    public String getRentalId() {
        return rentalId;
    }

    public User getUser() {
        return user;
    }

    public Car getCar() {
        return car;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
