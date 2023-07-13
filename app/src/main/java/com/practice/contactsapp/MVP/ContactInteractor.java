package com.practice.contactsapp.MVP;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.practice.contactsapp.models.Contact;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public class ContactInteractor implements ContactContract.Interactor{
    private final ContactContract.Repository repository;

    @Inject
    public ContactInteractor(ContactContract.Repository repository) {
        this.repository = repository;
    }

    @Override
    public LiveData<List<Contact>> getAllContacts() {
        Log.d(TAG, "getAllContacts: interactor");
        return repository.getAllContacts();
    }

    @Override
    public Completable addContact(Contact contact) {
        return repository.addContact(contact);
    }

    @Override
    public Completable updateContact(Contact contact) {
        return repository.updateContact(contact);
    }

    @Override
    public Completable deleteContact(Contact contact) {
        return repository.deleteContact(contact);
    }
}
