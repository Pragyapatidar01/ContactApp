package com.practice.contactsapp.di;

import android.content.Context;

import com.practice.contactsapp.Database.ContactDao;
import com.practice.contactsapp.Database.ContactDatabase;
import com.practice.contactsapp.MVP.ContactContract;
import com.practice.contactsapp.MVP.ContactInteractor;
import com.practice.contactsapp.MVP.ContactPresenter;
import com.practice.contactsapp.MVP.ContactRepository;
import com.practice.contactsapp.MainActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactModule {
    private final ContactContract.View view;
    private final Context context;

    public ContactModule(ContactContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Provides
    ContactContract.View provideView() {
        return view;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    ContactContract.Presenter providePresenter(ContactContract.View view, ContactContract.Interactor interactor, MainActivity mainActivity) {
        return new ContactPresenter(view, interactor, mainActivity);
    }

    @Singleton
    @Provides
    ContactContract.Interactor provideInteractor(ContactContract.Repository repository) {
        return new ContactInteractor(repository);
    }

    @Singleton
    @Provides
    ContactContract.Repository provideRepository(ContactDao contactDao) {
        return new ContactRepository(contactDao);
    }

    @Provides
    @Singleton
    public ContactDao provideContactDao(ContactDatabase contactDatabase) {
        return contactDatabase.contactDao();
    }

    @Provides
    @Singleton
    public ContactDatabase provideContactDatabase(Context context) {
        return ContactDatabase.getInstance(context);
    }
}
