package com.zakaria.phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Md.Nahid on 3/30/2016.
 */
public class ContactManager {
    private DBhelper helper;
    private Contact contact;
    private SQLiteDatabase database;

    public ContactManager(Context context) {
        helper = new DBhelper(context);
    }

    private void open() {
        database = helper.getWritableDatabase();
    }

    private void close() {
        helper.close();
    }

    public boolean addContact(Contact contact) {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.COL_NAME, contact.getName());
        contentValues.put(DBhelper.COL_PHONENO, contact.getPhoneNo());

        long inserted = database.insert(DBhelper.TABLE_CONTACT, null, contentValues);
        this.close();
        if (inserted > 0) {
            return true;
        } else
            return false;
    }

    public Contact getContact(int id) {

        this.open();
        Cursor cursor = database.query(DBhelper.TABLE_CONTACT, new String[]{DBhelper.COL_ID, DBhelper.COL_NAME, DBhelper.COL_PHONENO}, DBhelper.COL_ID + " = " + id, null, null, null, null);
        cursor.moveToFirst();
        int mId = cursor.getInt(cursor.getColumnIndex(DBhelper.COL_ID));
        String name = cursor.getString(cursor.getColumnIndex(DBhelper.COL_NAME));
        String phoneNo = cursor.getString(cursor.getColumnIndex(DBhelper.COL_PHONENO));

        contact = new Contact(mId, name, phoneNo);
        this.close();
        return contact;
    }

    public ArrayList<Contact> getAllContacts() {
        this.open();
        ArrayList<Contact> contactList = new ArrayList<>();
        Cursor cursor = database.query(DBhelper.TABLE_CONTACT, null, null, null, null, null, null);
        cursor.moveToFirst();

        if (cursor != null && cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                int mId = cursor.getInt(cursor.getColumnIndex(DBhelper.COL_ID));
                String name = cursor.getString(cursor.getColumnIndex(DBhelper.COL_NAME));
                String phoneNo = cursor.getString(cursor.getColumnIndex(DBhelper.COL_PHONENO));
                contact = new Contact(mId, name, phoneNo);

                contactList.add(contact);
                cursor.moveToNext();
            }
        }
        this.close();
        return contactList;
    }


    public boolean deleteContact(int id) {
        this.open();
        int deleted = database.delete(DBhelper.TABLE_CONTACT, DBhelper.COL_ID + " = " + id, null);
        this.close();
        if (deleted > 0) {
            return true;
        } else return false;
    }

    public boolean updateContact(int id, Contact contact) {
        this.open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBhelper.COL_NAME, contact.getName());
        contentValues.put(DBhelper.COL_PHONENO, contact.getPhoneNo());

        int updated = database.update(DBhelper.TABLE_CONTACT, contentValues, DBhelper.COL_ID + " = " + id, null);
        this.close();
        if (updated > 0) {
            return true;
        } else return false;
    }
}
