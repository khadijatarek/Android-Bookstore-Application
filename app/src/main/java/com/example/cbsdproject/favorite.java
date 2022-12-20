package com.example.cbsdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class favorite extends activity_base {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        getSupportActionBar().setTitle("Favorites");

        Bundle b1= getIntent().getExtras();
        String username= b1.getString("username");

        TextView userText= findViewById(R.id.fav_username_label);
        userText.setText(username+"'s Favorites");

        DBHelper db = new DBHelper(this);

        ArrayList<String> bookNames= new ArrayList<>();
        ArrayList<Integer> bookPrices= new ArrayList<>();
        ArrayList<Integer> bookImages= new ArrayList<>();
        ArrayList<String >imagesPath =new ArrayList<>();
        Cursor cursor =db.getUserFav(username);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_activated_1);
        ListView favList = findViewById(R.id.favs_listView);



        if(cursor.getCount()>0) {
            while (!cursor.isAfterLast()) {
                Cursor temp_cursor= db.getSpecificBook(cursor.getString(0));

                bookNames.add(temp_cursor.getString(1));
                bookPrices.add(Integer.parseInt(temp_cursor.getString(5)));
                imagesPath.add(temp_cursor.getString(7));
                cursor.moveToNext();
            }
            for(int i=0; i<imagesPath.size();i++){
               String mDrawableName = imagesPath.get(i) ;
                int resId = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
                bookImages.add(resId);

           //     bookImages.add(R.drawable.beautyandbeast);
            }
            favList.setAdapter(new ListAdapter(getApplicationContext(),bookNames,bookImages,bookPrices));
            listAdapter.clear();

        }
        else{
            TextView message = findViewById(R.id.fav_text);
            message.setText("No Books in Favorites");
        }
    }
}
