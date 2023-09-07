package com.example.tawsilaa;

public class Drivers {
    String ID, fullname, email, phonenum;

    public Drivers() {
    }

    public Drivers(String ID, String fullname, String email, String phonenum) {
        this.ID = ID;
        this.fullname = fullname;
        this.email = email;
        this.phonenum = phonenum;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
}
