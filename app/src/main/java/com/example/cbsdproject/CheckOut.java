package com.example.cbsdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckOut extends activity_base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        getSupportActionBar().setTitle("Cart");


        Bundle b1= getIntent().getExtras();
        String username= b1.getString("username");
        String address=b1.getString("address");
        String phone=b1.getString("phone");
        String city=b1.getString("city");
        String TotalPrice=b1.getString("TotalPrice");
        String Items=b1.getString("Items");

        ((TextView)findViewById(R.id.check_price)).setText(TotalPrice);
        ((TextView)findViewById(R.id.check_numberOfItems)).setText(Items);
        ((TextView)findViewById(R.id.check_address)).setText(address);
        ((TextView)findViewById(R.id.check_city)).setText(city);
        ((TextView)findViewById(R.id.check_phone)).setText(phone);

        DBHelper dbHelper = new DBHelper(this);


        Button confirm = (Button) findViewById(R.id.confirm_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.ClearCart(username);
                Intent i1 =new Intent(getApplicationContext(),booksHome.class);
                i1.putExtra("username",username);
                startActivity(i1);
            }
        });

    }
}