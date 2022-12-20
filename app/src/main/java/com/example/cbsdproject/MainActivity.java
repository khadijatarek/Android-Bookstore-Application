package com.example.cbsdproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Online Book Store");


        final EditText input_email=findViewById(R.id.input_email);
        final EditText input_password=findViewById(R.id.input_password);


        TextView textviewsignup=findViewById(R.id.textviewsignin);
        textviewsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToRegisterActivity =  new Intent(MainActivity.this,Sign_up.class);
                startActivity(goToRegisterActivity);
            }
        });



        Button btn_login=findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

           String email = input_email.getText().toString();
           String password = input_password.getText().toString();
           try{
               if(email.length() > 0 && password.length() >0)
               {
                   DBHelper dbUser = new DBHelper(MainActivity.this);


                   if(dbUser.Login(email, password))
                   {
                       String username=dbUser.getUsername(email);

                       Toast.makeText(MainActivity.this,"Successfully Logged In", Toast.LENGTH_LONG).show();
                       Intent goToHome =  new Intent(MainActivity.this,booksHome.class);
                       goToHome.putExtra("username",username);

                       startActivity(goToHome);
                   }else{
                       Toast.makeText(MainActivity.this,"Invalid Username/Password", Toast.LENGTH_LONG).show();
                   }
                   dbUser.close();
               }

           }catch(Exception e)
           {
               Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
           }
       }
   });

    }
}