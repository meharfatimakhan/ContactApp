package com.example.contacts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel
{
    private ContactRepository repository;
    private LiveData<List<ContactClass>> allContacts;
    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new ContactRepository(application);
        allContacts = repository.getAllContacts();
    }

    public void insert(List<ContactClass> contact){
        repository.insertObj(contact);
    }

    public LiveData<List<ContactClass>> getAllContacts(){
        return allContacts;
    }
}
