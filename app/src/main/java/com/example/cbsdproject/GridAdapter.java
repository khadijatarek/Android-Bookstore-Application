package com.example.cbsdproject;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    Context context ;
    ArrayList<String> BookNames;
    ArrayList<Integer> image;

    LayoutInflater inflater;

    public GridAdapter(Context context, ArrayList<String> booksNames, ArrayList<Integer> image) {
        this.context = context;
        BookNames = booksNames;
        this.image = image;
        this.inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public Context getContext() {
        return context;
    }

    public ArrayList<String> getBookName() {
        return BookNames;
    }

    public ArrayList<Integer> getImage() {
        return image;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setBookName(ArrayList<String> bookName) {
        BookNames = bookName;
    }

    public void setImage(ArrayList<Integer> image) {
        this.image = image;
    }

    @Override
    public int getCount() {
        return BookNames.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        if ( inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null){
            convertView= inflater.inflate(R.layout.grid_item, null);
        }
        ImageView imageView = convertView.findViewById(R.id.book_image);
        TextView textView = convertView.findViewById(R.id.book_name);

        imageView.setImageResource(image.get(i));
        textView.setText(BookNames.get(i));

        return convertView;
    }
}
