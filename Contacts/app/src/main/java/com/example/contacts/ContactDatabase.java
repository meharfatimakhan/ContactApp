package com.example.contacts;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = {ContactClass.class}, version = 1, exportSchema = false)
public abstract class ContactDatabase extends RoomDatabase {

    private static ContactDatabase instance;

    public abstract ContactDao contactDao();

    public static ContactDatabase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ContactDatabase.class, "MyContacts").build();
        }

        return instance;
    }
    public static void destroyInstance()
    {
        instance = null;
    }

    public static class PopulateDbAsuncTask extends AsyncTask<Void, Void, Void> {
        public ContactDao contactDao;
        List<ContactClass> contactClassList;
        public PopulateDbAsuncTask(ContactDatabase db, List<ContactClass> objClass){
            contactDao = db.contactDao();
            contactClassList = objClass;
        }

        @Override
        protected Void doInBackground(Void... voids){
            contactDao.insertObj(contactClassList);
            return null;
        }
    }
}
