package com.practice.contactsapp.models;

public class Contact {
    private int id;
    private String fullName;
    private String phoneNumber;
    private String email;
    private String company;
    private String imageUri;

    // Constructor, getters, and setters

    public Contact(int id, String fullName, String contactNumber, String email, String companyInfo, String imageUri) {
        this.id = id;
        this.fullName = fullName;
        this.phoneNumber = contactNumber;
        this.email = email;
        this.company = companyInfo;
        this.imageUri = imageUri;
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
