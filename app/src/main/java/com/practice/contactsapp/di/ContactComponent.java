package com.practice.contactsapp.di;

import com.practice.contactsapp.AddContactController;
import com.practice.contactsapp.ContactDetailsController;
import com.practice.contactsapp.MainActivityController;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContactModule.class})
public interface ContactComponent {

    void inject(MainActivityController controller);
    void inject(AddContactController addContactController);
    void inject(ContactDetailsController contactDetailsController);
}