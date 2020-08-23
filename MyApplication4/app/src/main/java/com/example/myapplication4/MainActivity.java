package com.example.myapplication4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication4.R;

public class MainActivity extends AppCompatActivity {

    Button   signinbtn;
    EditText editTextEmail;

    EditText editTextPassword;
    String actualEmail= "mehar@gmail.com";
    String actualPassword= "123456789";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        editTextEmail= findViewById(R.id.edttxt_email);
        editTextPassword= findViewById(R.id.edttxtPass);

        signinbtn= findViewById(R.id.btn_login);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String email= editTextEmail.getText().toString();
              String password= editTextPassword.getText().toString();

              if(email.isEmpty() || password.isEmpty())
              {
                  Toast.makeText(getApplicationContext(), "Email or Password not entered. Try again", Toast.LENGTH_LONG).show();
                  editTextEmail.setError("Try Again");
                  editTextEmail.requestFocus();
                  editTextPassword.requestFocus();
                  editTextPassword.setError("Try Again");
              }

              else
              {
                  if(email.equals(actualEmail) && password.equals(actualPassword)) {

                      Intent i = new Intent(getApplicationContext(), ContactList.class);
                      startActivity(i);


                  }
                  else
                  {
                      Toast.makeText(getApplicationContext(), "Email or Password incorrect. Try again", Toast.LENGTH_LONG).show();
                      editTextEmail.setError("Try Again");
                      editTextEmail.requestFocus();
                      editTextPassword.requestFocus();
                      editTextPassword.setError("Try Again");

                  }


              }

            }
        });

        SpannableString ss = new SpannableString("Don't have an account? Sign Up");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(MainActivity.this, Signup.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#13365A"));
                ds.setUnderlineText(false);
            }
        };
        final StyleSpan boldSpan = new StyleSpan(android.graphics.Typeface.BOLD);
        ss.setSpan(boldSpan, 23, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan, 23, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = (TextView) findViewById(R.id.textView12);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }




}
