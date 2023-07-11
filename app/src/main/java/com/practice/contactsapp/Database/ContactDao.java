package com.practice.contactsapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.practice.contactsapp.models.Contact;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts")
    LiveData<List<Contact>> getAllContacts();

    Completable insert(Contact contact);

    Completable update(Contact contact);

    Completable delete(Contact contact);

}
