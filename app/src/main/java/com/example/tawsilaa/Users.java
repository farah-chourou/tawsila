package com.example.tawsilaa;

public class Users {
    String ID, Fullname, Email, PhoneNumber;

    public Users() {
    }

    public Users(String Fullname, String Email, String PhoneNumber, String password, String ID) {
    }

    public Users(String Fullname, String Email, String PhoneNumber, String ID) {
        this.Fullname = Fullname;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.ID = ID;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String Fullname) {
        this.Fullname = Fullname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}