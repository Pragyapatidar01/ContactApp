package com.practice.contactsapp;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bluelinelabs.conductor.Controller;
import com.practice.contactsapp.Database.ContactDao;
import com.practice.contactsapp.Database.ContactDatabase;
import com.practice.contactsapp.MVP.ContactContract;
import com.practice.contactsapp.di.ContactComponent;
import com.practice.contactsapp.di.ContactModule;
import com.practice.contactsapp.models.Contact;

import java.util.List;

public class MainActivityController extends Controller implements ContactContract.View {

    private ContactContract.Presenter presenter;
    private ListView contactListView;
    private ContactListAdapter contactListAdapter;

    @NonNull
    @Override
    protected View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @Nullable Bundle savedViewState) {
        View rootView = inflater.inflate(R.layout.activity_main, container, false);

        contactListView = rootView.findViewById(R.id.contact_recycler_view);
        contactListView.setAdapter(contactListAdapter);
        contactListView.setOnItemClickListener((parent, view, position, id) -> {
            Contact contact = (Contact) contactListAdapter.getItem(position);
            presenter.onContactClicked(contact);
        });

        Button addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> presenter.onAddButtonClicked());

        return rootView;
    }

    @Override
    protected void onAttach(@NonNull View view){
        super.onAttach(view);

        /*ContactDao contactDao = ContactDatabase.getInstance(getContext()).contactDao();
        ContactComponent contactComponent = DaggerContactComponent.builder()
                .contactModule(new ContactModule(this))
                .build();*/
       /* contactComponent.inject(this);*/

        presenter.loadContacts();
    }

    @Override
    public void showContacts(List<Contact> contacts) {
        contactListAdapter.setContacts(contacts);
    }

    @Override
    public void showContactDetails(Contact contact) {
// Open the contact details page and pass the contact data
        /*ContactDetailsController detailsController = new ContactDetailsController(contact);
        Router router = Conductor.getRouter(getActivity());
        router.pushController(RouterTransaction.with(detailsController)
                .pushChangeHandler(new FadeChangeHandler())
                .popChangeHandler(new FadeChangeHandler()));*/
    }

    @Override
    public void showAddContactScreen() {

    }

    @Override
    public void showContactUpdateSuccess() {

    }

    @Override
    public void showContactDeleteSuccess() {

    }

    @Override
    public void showError(String message) {

    }
}
