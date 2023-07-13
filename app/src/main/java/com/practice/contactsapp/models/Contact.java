package com.practice.contactsapp.models;

import com.practice.contactsapp.Database.ContactEntity;

public class Contact {
    private int id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String company;
    private String imageUri;

    // Constructor, getters, and setters

    public Contact(){

    }
    public Contact(String fullName, String phoneNumber, String email, String company, String imageUri) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.company = company;
        this.imageUri = imageUri;
    }

    public Contact(ContactEntity contactEntity) {
        this.id = contactEntity.id;
        this.fullName = contactEntity.fullName;
        this.phoneNumber = contactEntity.phoneNumber;
        this.email = contactEntity.email;
        this.company = contactEntity.company;
        this.imageUri = contactEntity.imageUri;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
