package com.example.tawsilaa;


public class Visa {
    String name, date, ExpDate, AccNumb, CVV;

    public Visa() {
    }

    public Visa(String name, String date, String expDate, String accNumb, String CVV) {
        this.name = name;
        this.date = date;
        this.ExpDate = expDate;
        this.AccNumb = accNumb;
        this.CVV = CVV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccNumb() {
        return AccNumb;
    }

    public void setAccNumb(String accNumb) {
        this.AccNumb = accNumb;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExpDate() {
        return ExpDate;
    }

    public void setExpDate(String expDate) {
        ExpDate = expDate;
    }
}
