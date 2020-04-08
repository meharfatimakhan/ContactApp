package com.example.contacts;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ContactDao {
   /* @Insert
    void insert(ContactClass contact);*/

    @Insert
    void insertObj(List<ContactClass> contacts);



    @Query("SELECT * FROM contact_table ORDER BY name DESC")
    LiveData<List<ContactClass>> getAllContacts();
}
