package com.example.contacts;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewContact extends AppCompatActivity {

    List<ContactClass> contactClassList;
    HashSet<ContactClass> hashSet;
    ContactDatabase contactDb;
    ContactClass contact;

    Button button1;
    Button button2;
    CircleImageView imgV;
    EditText name;
    EditText number;
    EditText email;
    String getName;
    String getNumber;
    String getEmail;
    String getPhoto;
    Uri selectImage;
    private static final int SELECT_PICTURE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_add);
        contactDb = ContactDatabase.getAppDatabase(getApplicationContext());
        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Add Contact");

        button1=(Button)findViewById(R.id.button);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
            }
        });
        imgV = (CircleImageView) findViewById(R.id.image1);
        name = (EditText)findViewById(R.id.name);
        getName = name.getText().toString();
        number = (EditText)findViewById(R.id.number);
        getNumber = number.getText().toString();
        email = (EditText) findViewById (R.id.email);
        getEmail = email.getText().toString();

        button2 = (Button)findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   contact = new ContactClass(getName,getPhoto,getEmail,getNumber);
                /*Intent intent = new Intent(NewContact.this,Contact.class);
                intent.putExtra("name",getName);
                intent.putExtra("number",getNumber);
                intent.putExtra("email",getEmail);
                intent.putExtra("photo",getPhoto);

                startActivityForResult(intent,1);*/

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if((requestCode == SELECT_PICTURE) && (resultCode == RESULT_OK) && (data != null)){
            selectImage = data.getData();
            imgV.setImageURI(selectImage);
            getPhoto = selectImage.toString();
        }
        else {
            getPhoto = "";
        }


        /*switch(requestCode) {
            case SELECT_PICTURE:
                if (resultCode == Activity.RESULT_OK){
                    Uri selectImage = data.getData();
                    Intent i = new Intent(getApplicationContext(),NewContact.class);
                    i.putExtra("PICTURE_LOCATION", selectImage.toString());
                    startActivity(i);
                }
        }*/

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
