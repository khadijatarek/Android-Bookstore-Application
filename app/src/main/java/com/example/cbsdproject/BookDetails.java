package com.example.cbsdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BookDetails extends activity_base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        getSupportActionBar().setTitle("Book Details");


        Bundle b1= getIntent().getExtras();
        String BookName= b1.getString("BookName");
        String username= b1.getString("username");

        DBHelper dbHelper=new DBHelper(this);

    

        Cursor bookdetails=dbHelper.getSpecificBook(BookName);

        ImageView imageView=findViewById(R.id.book_image);

        String mDrawableName = bookdetails.getString(7) ;
        int resId = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        imageView.setImageResource(resId);


        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
        
        ((TextView)findViewById(R.id.book_name)).setText(""+bookdetails.getString(1));
        ((TextView)findViewById(R.id.Author)).setText("   Author :    "+bookdetails.getString(2));
        ((TextView)findViewById(R.id.price)).setText("    Price :     "+bookdetails.getString(5));
        ((TextView)findViewById(R.id.About)).setText("    About :     "+bookdetails.getString(3));
        ((TextView)findViewById(R.id.Genre)).setText("    Genre :     "+bookdetails.getString(4));

        Button btnCart=findViewById(R.id.addcart);
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.addToCart(username,bookdetails.getString(1),Integer.valueOf(bookdetails.getString(5)));

                Intent intent= new Intent(BookDetails.this, Cart.class);
                intent.putExtra("username",username);

                startActivity(intent);

            }
        });

        Button btnFav=findViewById(R.id.addfav);
        btnFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.addToFav(username,bookdetails.getString(1));
                Intent intent= new Intent(BookDetails.this, favorite.class);
                intent.putExtra("username",username);

                startActivity(intent);
            }
        });
    }
}