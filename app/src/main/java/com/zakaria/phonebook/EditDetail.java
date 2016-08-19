package com.zakaria.phonebook;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Md.Nahid on 3/30/2016.
 */
public class EditDetail extends AppCompatActivity {
    EditText nameEt, phoneEt;
    ContactManager contactManager;
    Contact contact;
    Button updateContactBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        nameEt = (EditText) findViewById(R.id.editName);
        phoneEt = (EditText) findViewById(R.id.editPhoneNo);
        updateContactBtn = (Button) findViewById(R.id.btnUpdate);
        contactManager = new ContactManager(this);
        int contactID = getIntent().getIntExtra("ID", 0);
        if (contactID>0){
            final Contact haveToUpdateContact = contactManager.getContact(contactID);
            nameEt.setText(haveToUpdateContact.getName());
            phoneEt.setText(haveToUpdateContact.getPhoneNo());
            updateContactBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String updateName =nameEt.getText().toString();
                    String updatePhoneno=phoneEt.getText().toString();
                    if (updateName.length()==haveToUpdateContact.getName().length() && updatePhoneno.length()==haveToUpdateContact.getPhoneNo().length()){
                        Toast.makeText(EditDetail.this, "You have Make changes for update the contact", Toast.LENGTH_LONG).show();
                    }else{
                        Contact goingToUpdateContact=new Contact(updateName,updatePhoneno);
                        Boolean isUpdated= contactManager.updateContact(haveToUpdateContact.getId(),goingToUpdateContact);
                        if (isUpdated){
                            Toast.makeText(EditDetail.this,"Contact Updated",Toast.LENGTH_LONG).show();
                            Intent listIntent=new Intent(EditDetail.this,ContactDetailsActivity.class);
                            startActivity(listIntent);
                        }else {
                            Toast.makeText(EditDetail.this,"Contact Not Updated",Toast.LENGTH_LONG).show();
                        }
                    }
                }
            });
        }else {
            Toast.makeText(EditDetail.this,"Error",Toast.LENGTH_LONG).show();

        }
    }
}
