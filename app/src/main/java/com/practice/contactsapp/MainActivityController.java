package com.practice.contactsapp;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler;
import com.practice.contactsapp.Database.ContactDao;
import com.practice.contactsapp.Database.ContactDatabase;
import com.practice.contactsapp.MVP.ContactContract;
import com.practice.contactsapp.MVP.ContactPresenter;
import com.practice.contactsapp.di.ContactComponent;
import com.practice.contactsapp.di.ContactModule;
import com.practice.contactsapp.di.DaggerContactComponent;
import com.practice.contactsapp.models.Contact;

import java.util.List;

import javax.inject.Inject;

public class MainActivityController extends Controller implements ContactContract.View {

    @Inject
    ContactPresenter presenter;
    private ContactListAdapter contactListAdapter;

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        super.onContextAvailable(context);
        DaggerContactComponent.builder()
                .contactModule(new ContactModule(this, context))
                .build()
                .inject(this);
    }

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedViewState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        ListView contactListView = rootView.findViewById(R.id.contact_list);


        /*ArrayAdapter<String> contacts;
        contacts = new ArrayAdapter<>(getApplicationContext(), R.layout.contacts_list_item);*/

        /*contactListAdapter = new ContactListAdapter(getContext());
        contactListAdapter.setPresenter(presenter);*/

        contactListAdapter = new ContactListAdapter(inflater.getContext());
        contactListView.setAdapter(contactListAdapter);
        contactListView.setOnItemClickListener((parent, view, position, id) -> {
            Contact contact = (Contact) contactListView.getItemAtPosition(position);
            presenter.onContactClicked(contact);
        });

        Button addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> presenter.onAddButtonClicked());

        return rootView;
    }

    @Override
    protected void onAttach(@NonNull View view){
        super.onAttach(view);

        ContactDao contactDao = ContactDatabase.getInstance(getApplicationContext()).contactDao();
        ContactComponent contactComponent = DaggerContactComponent.builder()
                .contactModule(new ContactModule(this, getApplicationContext()))
                .build();
        contactComponent.inject(this);

        presenter.loadContacts();
    }

    @Override
    public void showContacts(List<Contact> contacts) {
        Log.d(TAG, "showContacts: ");
        contactListAdapter.setContacts(contacts);
    }

    public void showContactDetails(Contact contact) {
        // Open the contact details page and pass the contact data
        ContactDetailsController detailsController = new ContactDetailsController(contact);
        Router router = getRouter();
        router.pushController(RouterTransaction.with(detailsController)
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));

    }

    @Override
    public void showAddContactScreen() {
        // Open the add contact screen
        AddContactController addController = new AddContactController();
        Router router = getRouter();
        router.pushController(RouterTransaction.with(addController)
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));
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
