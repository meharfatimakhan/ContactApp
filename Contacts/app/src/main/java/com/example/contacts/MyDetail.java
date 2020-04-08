package com.example.contacts;
import android.os.Bundle;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyDetail extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> phoneNumber;
    HashSet<String> hashSet;
    ContactAdapter adapterContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_detail);


        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        /*recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
      /*  phoneNumber = new ArrayList<>();
        hashSet = new HashSet<>();

        adapterContact = new ContactAdapter(this, phoneNumber);
        recyclerView.setAdapter(adapterContact);*/

        getIncomingIntent();
    }



    private void getIncomingIntent(){
        if (getIntent().hasExtra("picture") && getIntent().hasExtra("name") && getIntent().hasExtra("email") && getIntent().hasExtra("contact"))
        {
            String img = getIntent().getStringExtra("picture");
            String imgName = getIntent().getStringExtra("name");
            setTitle(imgName);
            String phoneNumber = getIntent().getStringExtra("contact");
            String imgEmail = getIntent().getStringExtra("email");
            setDetail(img,imgName, phoneNumber,imgEmail);

        }

    }

    private void setDetail(String img, String imgName, String imgContact, String imgEmail){
        TextView name=findViewById(R.id.image_name);
        name.setText(imgName);

        if (img != null){
            CircleImageView picture=findViewById(R.id.image1);
            CircleImageView picture2=findViewById(R.id.image2);
            Glide.with(this).asDrawable().load(img).into(picture2);
            Glide.with(this).asDrawable().load(img).into(picture);
        }else{
            CircleImageView picture=findViewById(R.id.image1);
            CircleImageView picture2=findViewById(R.id.image2);
            picture2.setImageResource(R.mipmap.ic_launcher_round);
            picture.setImageResource(R.mipmap.ic_launcher_round);
        }

        TextView email=findViewById(R.id.email_id);
        email.setText(imgEmail);
        TextView contact=findViewById(R.id.contact_number);
        contact.setText(imgContact);
      //  adapterContact.notifyDataSetChanged();


    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

