package com.model;

public class Car {
    private String id;
    private String make;
    private String model;
    private boolean isAvailable;

    public Car(String id, String make, String model) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.isAvailable = true;
    }

    public String getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
