package com.example.shopclues_clone.models;

public class AddressModel {
    private String phone_no;
    private String pin;
    private String flat;
    private String hno;
    private String phone_address;
    private String city;
    private String date;
    private String State;

    public AddressModel(String phone_no, String pin, String flat, String hno, String phone_address, String city, String date, String state) {
        this.phone_no = phone_no;
        this.pin = pin;
        this.flat = flat;
        this.hno = hno;
        this.phone_address = phone_address;
        this.city = city;
        this.date = date;
        State = state;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public String getPin() {
        return pin;
    }

    public String getFlat() {
        return flat;
    }

    public String getHno() {
        return hno;
    }

    public String getPhone_address() {
        return phone_address;
    }

    public String getCity() {
        return city;
    }

    public String getDate() {
        return date;
    }

    public String getState() {
        return State;
    }

}
