package com.practice.contactsapp.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.practice.contactsapp.models.Contact;

@Entity(tableName = "contacts")
public class ContactEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String fullName;
    public String contactNumber;
    public String email;
    public String companyInfo;
    public String imageUri;

    public ContactEntity(Contact contact) {
        this.fullName = contact.getFullName();
        this.contactNumber = contact.getPhoneNumber();
        this.email = contact.getEmail();
        this.companyInfo = contact.getCompany();
        this.imageUri = contact.getImageUri();
    }

    public Contact toContact() {
        return new Contact(id, fullName, contactNumber, email, companyInfo, imageUri);
    }
}
