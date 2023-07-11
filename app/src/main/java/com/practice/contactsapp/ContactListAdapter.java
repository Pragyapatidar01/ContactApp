package com.practice.contactsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.practice.contactsapp.models.Contact;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ContactListAdapter extends BaseAdapter {

    private final Context context;
    private List<Contact> contacts;
    private ContactItemClickListener itemClickListener;


    public ContactListAdapter(Context context,ContactItemClickListener itemClickListener) {
        this.context = context;
        this.contacts = contacts;
    }

    public void setContacts(List<Contact> contacts){
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return contacts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.fullNameTextView = convertView.findViewById(R.id.full_name_edittext);
            viewHolder.phoneNumberTextView = convertView.findViewById(R.id.phone_number_edittext);
            viewHolder.emailTextView = convertView.findViewById(R.id.email_edittext);
            viewHolder.companyTextView = convertView.findViewById(R.id.company_edittext);
            viewHolder.imageView = convertView.findViewById(R.id.contact_imageview);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Contact contact = (Contact) getItem(position);

        viewHolder.fullNameTextView.setText(contact.getFullName());
        viewHolder.phoneNumberTextView.setText(contact.getPhoneNumber());
        viewHolder.emailTextView.setText(contact.getEmail());
        viewHolder.companyTextView.setText(contact.getCompany());

        // Load and display image using Picasso
        Picasso.get().load(contact.getImageUri()).into(viewHolder.imageView);

        return convertView;
    }

    public interface ContactItemClickListener {
        void onUpdateClick(Contact contact);
    }

    private static class ViewHolder {
        TextView fullNameTextView;
        TextView phoneNumberTextView;
        TextView emailTextView;
        TextView companyTextView;
        ImageView imageView;
    }
}
