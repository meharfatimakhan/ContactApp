package com.example.contacts;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactRepository {
    private ContactDao contactDao;
    private LiveData<List<ContactClass>> allContacts;

    public ContactRepository(Application application){
        ContactDatabase db = ContactDatabase.getAppDatabase(application);
        contactDao = db.contactDao();
        allContacts = contactDao.getAllContacts();
    }

    public void insertObj(List<ContactClass> contact){
        new InsertContactAsyncTask(contactDao).execute(contact);
    }


    public LiveData<List<ContactClass>> getAllContacts(){
        return allContacts;
    }

    private static class InsertContactAsyncTask extends AsyncTask<List<ContactClass>, Void, Void> {
        private ContactDao contactDao;
        private InsertContactAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(List<ContactClass>... lists) {
            contactDao.insertObj(lists[0]);
            return null;
        }
    }


}
