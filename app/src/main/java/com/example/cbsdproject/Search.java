package com.example.cbsdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {
    GridView gridView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().setTitle("Search");

        Bundle b1= getIntent().getExtras();
        String username= b1.getString("username");

        Button search=findViewById(R.id.button_search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gridView1 = findViewById(R.id.search_gridview);
                gridView1.setAdapter(null);
                EditText book_title = findViewById(R.id.search);
                String title = book_title.getText().toString();

                ArrayList<String>names;
                ArrayList<Integer>images_arr =new ArrayList<>();
                DBHelper db = new DBHelper(Search.this);
                names=db.getBooksName_Search(title);


                ArrayList<String >image_Path = db.getImages_Search(title);

                for(int i=0; i<image_Path.size();i++) {
                    String mDrawableName = image_Path.get(i) ;
                    int resId = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
                    images_arr.add(resId);
                }

                GridAdapter adapter= new GridAdapter(Search.this,names,images_arr);
                gridView1.setAdapter(adapter);
                gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int i, long id) {


                        Intent i1 =new Intent(getApplicationContext(),BookDetails.class);
                        i1.putExtra("BookName",names.get(i));
                        i1.putExtra("username",username);
                        startActivity(i1);
                    }
                });


            }

        });





    }
}

  /* gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent =new Intent(getApplicationContext(),BookDetails.class);
                startActivity(intent);
            }
        });*/