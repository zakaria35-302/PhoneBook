package com.zakaria.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Md.Nahid on 3/30/2016.
 */
public class ListViewActivity extends AppCompatActivity {
    ContactManager contactManager;
    ArrayList<Contact> contactsList;
    ListView listView;
    AdapterForContact adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = (ListView) findViewById(R.id.contact_list);
        contactManager=new ContactManager(this);
        contactsList=contactManager.getAllContacts();
        adapter=new AdapterForContact(this,contactsList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int contactID = contactsList.get(position).getId();
                Intent detailsIntent = new Intent(ListViewActivity.this, ContactDetailsActivity.class);
                detailsIntent.putExtra("contact_Id", contactID);
                startActivity(detailsIntent);

            }
        });
    }
}
