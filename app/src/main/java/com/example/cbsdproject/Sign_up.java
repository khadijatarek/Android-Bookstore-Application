package com.example.cbsdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Sign_up extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setTitle("Sign Up");

        EditText input_email=findViewById(R.id.input_email);
        EditText input_username=findViewById(R.id.input_username);
        EditText input_password=findViewById(R.id.input_password);

        DBHelper db = new DBHelper(this);

        TextView textviewsignin=findViewById(R.id.textviewsignin);
        textviewsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterActivity =  new Intent(Sign_up.this,MainActivity.class);
                startActivity(goToRegisterActivity);
            }
        });


        Button signup_btn = findViewById(R.id.btn_signup);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= input_email.getText().toString();
                String password= input_password.getText().toString();
                String username= input_username.getText().toString();

                db.insertUserData(email,username,password);
                Toast.makeText(Sign_up.this, "User created", Toast.LENGTH_SHORT).show();
                Intent goToRegisterActivity =  new Intent(Sign_up.this,MainActivity.class);
                startActivity(goToRegisterActivity);
            }
        });



    }
}