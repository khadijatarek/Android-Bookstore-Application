package com.example.cbsdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Cart extends activity_base {

    int totalPrice = 0;
    int numberOfItems=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setTitle("Cart");


        Bundle b1= getIntent().getExtras();
        String username= b1.getString("username");

        TextView userText= findViewById(R.id.username_label);
        userText.setText(username+"'s Cart");

        DBHelper dbHelper=new DBHelper(this);
        Cursor BooksAddedToCart=dbHelper.getAllCartBooks(username);

        ArrayAdapter<String>listAdapter = new ArrayAdapter<>(Cart.this, android.R.layout.simple_list_item_1);
        ListView cartList = findViewById(R.id.listview_cart);

        ArrayList<String> bookNames= new ArrayList<>();
        ArrayList<Integer> bookPrices= new ArrayList<>();
        ArrayList<Integer> bookImages= new ArrayList<>();
        ArrayList<String >imagesPath =new ArrayList<>();

        TextView message = findViewById(R.id.Total_textView);
        Button checkOut = findViewById(R.id.checkout_btn);


        if(BooksAddedToCart.getCount()>0) {

            while (!BooksAddedToCart.isAfterLast()) {
                Cursor temp_cursor= dbHelper.getSpecificBook(BooksAddedToCart.getString(0));

                bookNames.add(temp_cursor.getString(1));
                bookPrices.add(Integer.parseInt(temp_cursor.getString(5)));
                totalPrice+=Integer.parseInt(temp_cursor.getString(5));
                imagesPath.add(temp_cursor.getString(7));
                BooksAddedToCart.moveToNext();
            }

            numberOfItems=imagesPath.size();

            for(int i=0; i<imagesPath.size();i++){
                String mDrawableName = imagesPath.get(i) ;
                int resId = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
                bookImages.add(resId);
            }
            cartList.setAdapter(new ListAdapter(getApplicationContext(),bookNames,bookImages,bookPrices));

            listAdapter.clear();

            message.setText("Total Price= " + Integer.toString(totalPrice) + " EGP");

        }
        else{
            Toast.makeText(this, "Empty Cart", Toast.LENGTH_SHORT).show();
           // listAdapter.add("No Items");
            message.setText("Empty Cart, No Items were added!");
            checkOut.setEnabled(false);
            ((TextView)findViewById(R.id.City_textBox)).setEnabled(false);
            ((TextView)findViewById(R.id.address_textBox)).setEnabled(false);
            ((TextView)findViewById(R.id.phone_textBox)).setEnabled(false);

        }
        TextView clear = (TextView) findViewById(R.id.clear_cart_textView);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.ClearCart(username);
                Intent i1 =new Intent(getApplicationContext(),Cart.class);
                i1.putExtra("username",username);
                startActivity(i1);

            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 =new Intent(getApplicationContext(),CheckOut.class);
                i1.putExtra("username",username);
                i1.putExtra("city",  ((TextView)findViewById(R.id.City_textBox)).getText().toString());
                i1.putExtra("address",  ((TextView)findViewById(R.id.address_textBox)).getText().toString());
                i1.putExtra("phone",  ((TextView)findViewById(R.id.phone_textBox)).getText().toString());
                i1.putExtra("TotalPrice",  Integer.toString(totalPrice));
                i1.putExtra("Items",  Integer.toString(numberOfItems));

                startActivity(i1);
            }
        });

    }
}