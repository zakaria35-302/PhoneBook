package com.zakaria.phonebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameEt, phoneNumberEt;
    private Contact contact;
    private ContactManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameEt = (EditText) findViewById(R.id.inputName);
        phoneNumberEt = (EditText) findViewById(R.id.inputPhoneNo);
        manager = new ContactManager(this);
    }

    public void save(View view) {
        String name = nameEt.getText().toString();
        String phone = phoneNumberEt.getText().toString();
        contact=new Contact(name,phone);
       boolean isInserted=manager.addContact(contact);
        if (isInserted) {
            Toast.makeText(MainActivity.this, name + " " + phone + " Successfully inserted ", Toast.LENGTH_SHORT).show();
            Intent listIntent = new Intent(MainActivity.this, ListViewActivity.class);
            startActivity(listIntent);
        } else {
            Toast.makeText(MainActivity.this, name + " " + phone + " not inserted", Toast.LENGTH_SHORT).show();

        }
    }
}
