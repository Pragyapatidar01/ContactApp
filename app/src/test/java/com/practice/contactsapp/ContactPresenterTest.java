package com.practice.contactsapp;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import androidx.lifecycle.LiveData;

import com.practice.contactsapp.MVP.ContactContract;
import com.practice.contactsapp.MVP.ContactInteractor;
import com.practice.contactsapp.MVP.ContactPresenter;
import com.practice.contactsapp.MVP.ContactRepository;
import com.practice.contactsapp.models.Contact;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.core.Single;

public class ContactPresenterTest {

    @Rule // -> allows liveData to work on different thread besides main, must be public!
    public InstantTaskExecutorRule executorRule = new InstantTaskExecutorRule();

    @Mock
    private ContactContract.View mockView;

    @Mock
    private MainActivity mainActivity;

    @Mock
    private ContactInteractor mockInteractor;

    private ContactPresenter presenter;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new ContactPresenter(mockView, mockInteractor, mainActivity);
    }

    @Test
    public void loadContacts_whenContactsAvailable_shouldShowContacts() {
        // Arrange
        List<Contact> contacts = Arrays.asList(new Contact("John Doe", "1234567890", "johndoe@example.com", "Example Company", "imageUrl"));
/*
        contacts.add(new Contact("John Doe", "1234567890", "johndoe@example.com", "Example Company", "imageUrl"));
*/
        /*LiveData<List<Contact>> expected =
        when(mockInteractor.getAllContacts()).thenReturn((LiveData<List<Contact>>) contacts);*/

        // Act
        presenter.loadContacts();

        // Assert
        verify(mockView).showContacts(contacts);
    }

}
