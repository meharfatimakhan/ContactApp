package com.example.myapplication4;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class, Number.class}, version = 1)
public abstract class MyAppDatabase extends RoomDatabase {


public abstract MyDao myDao();


}
