package com.practice.contactsapp.MVP;

import androidx.lifecycle.LiveData;

import com.practice.contactsapp.Database.ContactDao;
import com.practice.contactsapp.models.Contact;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ContactRepository implements ContactContract.Repository{
    private final ContactDao contactDao;

    @Inject
    public ContactRepository(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public LiveData<List<Contact>> getAllContacts() {
        return contactDao.getAllContacts();
    }

    @Override
    public Completable addContact(Contact contact) {
        return Completable.fromAction(() -> contactDao.insert(contact))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable updateContact(Contact contact) {
        return Completable.fromAction(() -> contactDao.update(contact))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable deleteContact(Contact contact) {
        return Completable.fromAction(() -> contactDao.delete(contact))
                .subscribeOn(Schedulers.io());
    }
}
