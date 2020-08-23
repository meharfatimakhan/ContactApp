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

public class Signup extends AppCompatActivity {

    TextView back;
    EditText name,email,password;

    TextView signin;
    Button signupbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupbtn= findViewById(R.id.btn_signup);
        name = findViewById(R.id.editText7);
        email = findViewById(R.id.editText);
        password = findViewById(R.id.editText1);

    signupbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String nameStr, emailStr, passStr;
            nameStr = name.getText().toString();
            emailStr = email.getText().toString();
            passStr = password.getText().toString();
            if (nameStr.isEmpty() || emailStr.isEmpty() || passStr.isEmpty()){
                name.setError("Try Again");
                email.setError("Try Again");
                password.setError("Try Again");
                password.requestFocus();
                email.requestFocus();
                name.requestFocus();
                Toast.makeText(getApplicationContext(), "Fields empty. Try again", Toast.LENGTH_LONG).show();
            }
            else {
                Intent i= new Intent(getApplicationContext(), ContactList.class);
                startActivity(i);
            }
        }
    });

        SpannableString ss = new SpannableString("Have an account? Sign In");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View textView) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#13365A"));
                ds.setUnderlineText(false);
            }
        };
        final StyleSpan boldSpan = new StyleSpan(android.graphics.Typeface.BOLD);
        ss.setSpan(boldSpan, 17, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(clickableSpan, 17, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textView = (TextView) findViewById(R.id.textView12);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        setTitle("Back");

        if (getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
