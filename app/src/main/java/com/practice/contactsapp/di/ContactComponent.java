package com.practice.contactsapp.di;

import com.practice.contactsapp.MainActivityController;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {ContactModule.class})
public interface ContactComponent {

    void inject(MainActivityController controller);
}