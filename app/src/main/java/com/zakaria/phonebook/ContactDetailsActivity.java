package com.zakaria.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Md.Nahid on 3/30/2016.
 */
public class ContactDetailsActivity extends AppCompatActivity {
    ContactManager contactManager;
    Contact contact;
    TextView nameTV,phoneTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        nameTV= (TextView) findViewById(R.id.detailNameTv);
        phoneTV= (TextView) findViewById(R.id.detailPhoneTv);
        int contact_ID = getIntent().getIntExtra("contact_Id", 0);
        contactManager=new ContactManager(this);
        contact=contactManager.getContact(contact_ID);
        nameTV.setText(contact.getName());
        phoneTV.setText(contact.getPhoneNo());


    }

    public void deleteContact(View view)
    {
        boolean isDeleted=contactManager.deleteContact(contact.getId());

        if (isDeleted){
            Intent allContactIntent=new Intent(ContactDetailsActivity.this,ContactDetailsActivity.class);
            startActivity(allContactIntent);
            Toast.makeText(ContactDetailsActivity.this, "Delete Successfull", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(ContactDetailsActivity.this,"Delete Failed",Toast.LENGTH_LONG).show();

        }
    }

    public void updateContact(View view)
    {
        Intent updateDataIntent=new Intent(ContactDetailsActivity.this,EditDetail.class);
        updateDataIntent.putExtra("ID", contact.getId());
        startActivity(updateDataIntent);
    }
}
