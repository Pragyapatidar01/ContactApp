package com.practice.contactsapp.MVP;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.practice.contactsapp.MainActivity;
import com.practice.contactsapp.models.Contact;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ContactPresenter implements ContactContract.Presenter{
    private final ContactContract.View view;
    private final ContactContract.Interactor interactor;
    private final MainActivity mainActivity;

    @Inject
    public ContactPresenter(ContactContract.View view, ContactContract.Interactor interactor, MainActivity mainActivity) {
        this.view = view;
        this.interactor = interactor;
        this.mainActivity = mainActivity;
    }

    @Override
    public void loadContacts() {
        interactor.getAllContacts().observe(mainActivity, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                view.showContacts(contacts);
            }
        });
    }

    @Override
    public void addContact(Contact contact) {
        interactor.addContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () ->{
                            view.showContactUpdateSuccess();
                            loadContacts();
                        },
                        error -> view.showError(error.getMessage())
                );
    }

    @Override
    public void updateContact(Contact contact) {
        interactor.updateContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            view.showContactUpdateSuccess();
                            loadContacts();
                        },
                        error -> view.showError(error.getMessage())
                );
    }

    @Override
    public void deleteContact(Contact contact) {
        interactor.deleteContact(contact)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> {
                            view.showContactDeleteSuccess();
                            loadContacts();
                        },
                        error -> view.showError(error.getMessage())
                );
    }

    @Override
    public void onContactClicked(Contact contact) {
        view.showContactDetails(contact);
    }

    @Override
    public void onAddButtonClicked() {
        view.showAddContactScreen();
    }

    @Override
    public void onDestroy() {
        // Clean up any resources if necessary
    }
}
