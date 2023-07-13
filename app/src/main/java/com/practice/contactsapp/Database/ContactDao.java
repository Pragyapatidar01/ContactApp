package com.practice.contactsapp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Upsert;

import com.practice.contactsapp.models.Contact;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts ORDER BY fullName ASC")
    LiveData<List<Contact>> getAllContacts();

    @Upsert
    Completable insert(ContactEntity contactEntity);

    @Update
    Completable update(ContactEntity contactEntity);

    @Delete
    Completable delete(ContactEntity contactEntity);

}
