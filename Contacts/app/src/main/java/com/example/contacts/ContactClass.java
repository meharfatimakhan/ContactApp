package com.example.contacts;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "contact_table", primaryKeys = "number")
public class ContactClass implements Serializable {

    @ColumnInfo(name="name") @NonNull
    private String name;
    private String photo;
    private String email;

    //@TypeConverters(DataConverter.class)
    @ColumnInfo(name="number") @NonNull
    private String number;

    public ContactClass(String name, String photo, String email, @NonNull String number) {
        this.name = name;
        this.photo = photo;
        this.email = email;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NonNull
    public String getNumber() {
        return number;
    }

    public void setNumber(@NonNull String number) {
        this.number = number;
    }
}
