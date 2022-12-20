package com.example.cbsdproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> bookNames;
    ArrayList<Integer> images;
    ArrayList<Integer> prices;
    LayoutInflater inflater;

    ListAdapter(Context context, ArrayList<String> BookNames, ArrayList<Integer> image, ArrayList<Integer> prices){
        this.context=context;
        this.bookNames=BookNames;
        this.images=image;
        this.prices=prices;
        inflater= LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return bookNames.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if ( inflater == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView==null) {
            convertView = inflater.inflate(R.layout.list_item, null);

        }
          //  convertView =inflater.inflate(R.layout.list_item,null);
        TextView title_txtView=(TextView) convertView.findViewById(R.id.list_book_title);
        TextView price_txtView=(TextView) convertView.findViewById(R.id.list_book_price);
        ImageView bookImages=(ImageView) convertView.findViewById(R.id.list_image);

        title_txtView.setText(bookNames.get(i));
        price_txtView.setText(Integer.toString(prices.get(i)));
        bookImages.setImageResource(images.get(i));

        return convertView;
    }
}
