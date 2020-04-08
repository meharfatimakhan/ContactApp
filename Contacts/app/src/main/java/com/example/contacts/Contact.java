package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class Contact extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ContactClass> contactClassList;
    HashSet <ContactClass> hashSet;
    AdapterContact adapterContact;
    ContactDatabase contactDb;
    ContactClass manualContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_list);
        setTitle("Contact List");
       // contactViewModel = ;
        contactDb = ContactDatabase.getAppDatabase(getApplicationContext());
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        FloatingActionButton applyButton= findViewById(R.id.floatingActionButton);
        applyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Contact.this,NewContact.class);
                startActivityForResult(intent,1);
            }
        });


        getIncomingIntent();

        initRecyclerView();

    }

    private void getIncomingIntent(){
        if (getIntent().hasExtra("photo") && getIntent().hasExtra("name") && getIntent().hasExtra("email") && getIntent().hasExtra("number"))
        {
            String img = getIntent().getStringExtra("photo");
            String imgName = getIntent().getStringExtra("name");
            setTitle(imgName);
            String phoneNumber = getIntent().getStringExtra("number");
            String imgEmail = getIntent().getStringExtra("email");
            manualContact = new ContactClass(img,imgName, phoneNumber,imgEmail);

        }

    }

    private void initRecyclerView(){
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactClassList = new ArrayList<>();
        hashSet = new HashSet<>();

        adapterContact = new AdapterContact(this,contactClassList);
        recyclerView.setAdapter(adapterContact);

        Dexter.withActivity(this).withPermission(Manifest.permission.READ_CONTACTS)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        if (response.getPermissionName().equals(Manifest.permission.READ_CONTACTS)) {
                           getContacts();
                        }
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(Contact.this,"Permission should be granted!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void getContacts(){
        ArrayList<String> numbers = new ArrayList<>();
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null,null);
        String emails = "";
        HashSet<String> checkName = new HashSet<>();
        HashSet<String> checkNumber = new HashSet<>();

        int j=0;
        // ArrayList<String> phoneNumbers = new ArrayList<>();
        while (phones.moveToNext()) {

            //String id = phones.getString(phones.getColumnIndex(ContactsContract.Contacts._ID));
            String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneNumber = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String photo = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI));
            // phoneNumbers.add(phoneNumber);
            /*Cursor emailCur = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null
                    , ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?"
                    , new String[]{id}, null);
            if(emailCur!=null){
                emailCur.moveToFirst();
            }

            while (emailCur.moveToNext()) {
                emails = emailCur.getString( emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
                System.out.println(emails);
            }
            emailCur.close();*/
            //num.add(phoneNumber);
            numbers.clear();
            j=j+1;
            phoneNumber = phoneNumber.replaceAll("[^0-9]", "");
            numbers.add(phoneNumber);
            ContactClass contactt = new ContactClass(name, photo, emails, phoneNumber);


            if (!checkNumber.contains(contactt.getNumber())) {
                contactClassList.clear();
                hashSet.add(contactt);
                contactClassList.addAll(hashSet);
                checkName.add(contactt.getName());
                checkNumber.add(contactt.getNumber());
                Collections.sort(contactClassList, new Comparator<ContactClass>() {
                    public int compare(ContactClass lhs, ContactClass rhs) {
                        return lhs.getName().compareTo(rhs.getName());
                    }
                });
               // j++;
            }
            /*else {
                if (!checkNumber.contains(phoneNumber)){
                    for (int i = 0; i < contactClassList.size(); i++) {
                        if (contactClassList.get(i).getName().equals(contactt.getName())) {
                            ContactClass contactt1 = contactClassList.get(i);
                            System.out.println(contactt1);
                            ArrayList<String> numb = contactt1.getNumber();
                            numb.add(phoneNumber);
                            contactt1.setNumber(numb);
                            checkNumber.add(phoneNumber);
                            contactClassList.set(i,contactt1);
                            break;
                        }
                    }
                }


            }*/
        }


        ContactDatabase.PopulateDbAsuncTask objPop = new ContactDatabase.PopulateDbAsuncTask(contactDb,contactClassList);
        objPop.execute();

        /*if (manualContact != null && !checkNumber.contains(manualContact.getNumber())){
            contactClassList.clear();
            hashSet.add(manualContact);
            contactClassList.addAll(hashSet);
            //   contactClassList.add(manualContact);
            checkName.add(manualContact.getName());
            checkNumber.add(manualContact.getNumber());

            Collections.sort(contactClassList, new Comparator<ContactClass>() {
                public int compare(ContactClass lhs, ContactClass rhs) {
                    return lhs.getName().compareTo(rhs.getName());
                }
            });
        }*/
        adapterContact.notifyDataSetChanged();

    }
}