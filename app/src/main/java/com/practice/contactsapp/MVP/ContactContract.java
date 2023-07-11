package com.practice.contactsapp.MVP;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import com.practice.contactsapp.models.Contact;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;

public interface ContactContract {
    interface View {
        void showContacts(List<Contact> contacts);
        void showContactDetails(Contact contact);
        void showAddContactScreen();
        void showContactUpdateSuccess();
        void showContactDeleteSuccess();
        void showError(String message);
    }
    interface Presenter {
        void loadContacts();
        void addContact(Contact contact);
        void updateContact(Contact contact);
        void deleteContact(Contact contact);
        void onContactClicked(Contact contact);
        void onAddButtonClicked();
        void onDestroy();
    }
    interface Interactor {
        LiveData<List<Contact>> getAllContacts();
        Completable addContact(Contact contact);
        Completable updateContact(Contact contact);
        Completable deleteContact(Contact contact);

    }
    interface Repository {
        LiveData<List<Contact>> getAllContacts();
        Completable addContact(Contact contact);
        Completable updateContact(Contact contact);
        Completable deleteContact(Contact contact);
    }
}
