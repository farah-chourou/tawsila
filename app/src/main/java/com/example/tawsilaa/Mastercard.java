package com.example.tawsilaa;

public class Mastercard {
    String name, date, ExpDate, AccNumb, CVC;

    public Mastercard() {
    }

    public Mastercard(String name, String date, String expDate, String accNumb, String CVC) {
        this.name = name;
        this.date = date;
        this.ExpDate = expDate;
        this.AccNumb = accNumb;
        this.CVC = CVC;
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

    public String getCVC() {
        return CVC;
    }

    public void setCVC(String CVC) {
        this.CVC = CVC;
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
