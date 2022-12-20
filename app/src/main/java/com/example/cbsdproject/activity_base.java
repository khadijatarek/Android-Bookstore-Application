package com.example.cbsdproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class activity_base extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        Bundle b1= getIntent().getExtras();
        String username= b1.getString("username");

        int id=item.getItemId();
        if(id==R.id.search)
        {
            Intent i1=new Intent(this,Search.class);
            i1.putExtra("username",username);
            startActivity(i1);}
        if(id==R.id.cart)
        {
            Intent i1=new Intent(this,Cart.class);
            i1.putExtra("username",username);
            startActivity(i1);
        }
        if(id==R.id.logout)
        {
            startActivity(new Intent(this,MainActivity.class));
        }
        if(id==R.id.home)
        {
            Intent i1=new Intent(this,booksHome.class);
            i1.putExtra("username",username);
            startActivity(i1);
        }
        if(id==R.id.favourites){
            Intent i1=new Intent(this,favorite.class);
            i1.putExtra("username",username);
            startActivity(i1);
        }
        return super.onOptionsItemSelected(item);
    }
}