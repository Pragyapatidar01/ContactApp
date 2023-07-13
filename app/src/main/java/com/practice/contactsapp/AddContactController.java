package com.practice.contactsapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bluelinelabs.conductor.Controller;
import com.practice.contactsapp.MVP.ContactContract;
import com.practice.contactsapp.MVP.ContactPresenter;
import com.practice.contactsapp.di.ContactModule;
import com.practice.contactsapp.di.DaggerContactComponent;
import com.practice.contactsapp.models.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

public class AddContactController extends Controller implements ContactContract.View{
    private ContactContract.View view;
    @Inject
    ContactPresenter presenter;
    public AddContactController() {
        // Required empty public constructor
    }

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        super.onContextAvailable(context);

        DaggerContactComponent.builder()
                .contactModule(new ContactModule( this, context))
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedViewState) {
        View rootView = inflater.inflate(R.layout.add_contact, container, false);

        EditText fullNameEditText = rootView.findViewById(R.id.full_name_edittext1);
        EditText phoneNumberEditText = rootView.findViewById(R.id.phone_number_edittext1);
        EditText emailEditText = rootView.findViewById(R.id.email_edittext1);
        EditText companyEditText = rootView.findViewById(R.id.company_edittext1);
        /*ImageView imageView = rootView.findViewById(R.id.contact_imageview1);*/
        Button addButton = rootView.findViewById(R.id.add_button);

        addButton.setOnClickListener(v -> {

            Contact contact = new Contact();
            contact.setFullName(fullNameEditText.getText().toString());
            contact.setPhoneNumber(phoneNumberEditText.getText().toString());
            contact.setEmail(emailEditText.getText().toString());
            contact.setCompany(companyEditText.getText().toString());

            // Call the presenter or repository method to add the contact
            presenter.addContact(contact);
            Log.d(TAG, "onCreateView: addContactController 1st");
            getRouter().popCurrentController();
            Log.d(TAG, "onCreateView: addContactController 2nd");
            presenter.loadContacts();
            Log.d(TAG, "onCreateView: addContactController 3rd");
        });

        return rootView;
    }

    @Override
    public void showContacts(List<Contact> contacts) {
    }

    @Override
    public void showContactDetails(Contact contact) {
    }

    @Override
    public void showAddContactScreen() {
    }

    @Override
    public void showContactUpdateSuccess() {
        Toast.makeText(getApplicationContext(),"Contact updated successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showContactDeleteSuccess() {
        Toast.makeText(getApplicationContext(), "Contact deleted successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }
}

