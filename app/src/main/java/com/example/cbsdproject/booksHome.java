package com.example.cbsdproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class booksHome extends activity_base {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_home);
        getSupportActionBar().setTitle("Home");

        gridView = findViewById(R.id.books_gridview);


        Bundle b1= getIntent().getExtras();
        String username= b1.getString("username");


        ArrayList<String>bookNames;
        ArrayList<Integer>images =new ArrayList<>();
        DBHelper db = new DBHelper(this);

        bookNames=db.getAllBooksName();

        ArrayList<String >imagesPath = db.getAllBooksImages();
        for(int i=0; i<imagesPath.size();i++) {
            String mDrawableName = imagesPath.get(i) ;
            int resId = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            images.add(resId);
        }

        GridAdapter adapter= new GridAdapter(this,bookNames,images);
        gridView.setAdapter(adapter);

       //when we click on the book(details for each book)
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                Intent i1 =new Intent(getApplicationContext(),BookDetails.class);
                i1.putExtra("BookName",bookNames.get(i));
                i1.putExtra("username",username);
                startActivity(i1);
            }
        });

    }
}