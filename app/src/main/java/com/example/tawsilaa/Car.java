package com.example.tawsilaa;

public class Car {
    int idC;
    String model, size;

    public Car() {
    }

    public Car(int idC, String model, String size) {
        this.idC = idC;
        this.model = model;
        this.size = size;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
