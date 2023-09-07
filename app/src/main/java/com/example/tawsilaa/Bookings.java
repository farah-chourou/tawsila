package com.example.tawsilaa;

import java.time.LocalDateTime;

public class Bookings {
    int idB;
    String departure, arrival, dateTime;

    public Bookings() {
    }

    public Bookings(int idB, String departure, String arrival, String dateTime) {
        this.idB = idB;
        this.departure = departure;
        this.arrival = arrival;
        this.dateTime = dateTime;
    }

    public int getIdB() {
        return idB;
    }

    public void setIdB(int idB) {
        this.idB = idB;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

