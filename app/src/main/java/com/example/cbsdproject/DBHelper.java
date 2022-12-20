package com.example.cbsdproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBname="BookstoreDB";
    SQLiteDatabase bookstoreDB;



    public DBHelper( Context context) {

        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("Create table books (book_id INTEGER PRIMARY KEY autoincrement, title text,author text,  about text,genre text, price int, quantity int, image text)");
        MyDB.execSQL("Create table users (user_id INTEGER PRIMARY KEY autoincrement,  email text NOT NULL,username text unique ,password text NOT NULL)");
        MyDB.execSQL("create table cart (username text ,bookName text,book_price int,FOREIGN KEY (username)REFERENCES users(username) ,FOREIGN KEY (bookName)REFERENCES books(title) )");
        MyDB.execSQL("create table fav (username text ,bookName text, FOREIGN KEY (username)REFERENCES users(username) ,FOREIGN KEY (bookName)REFERENCES books(title) )");


        MyDB.execSQL("Insert into books values(1,'Beauty and the Beast','Gabrielle-Suzanne Barbot','blablabla','ad',23,45,'beautyandbeast')");
        MyDB.execSQL("Insert into books values(2,'The Hunger Games','Suzanne Collins','blablabla','ad',23,45,'thehungergames')");
        MyDB.execSQL("Insert into books values(3,'Alice In WonderLand',' Lewis Carroll','blablabla','ad',23,45,'aliceinwonderland')");
        MyDB.execSQL("Insert into books values(4,'Anne of Grren Gables','Lucy Maud Montgomery','blablabla','ad',23,45,'anneofgreengables')");
        MyDB.execSQL("Insert into books values(5,'Beauty and the Beast','Gabrielle-Suzanne Barbot','blablabla','ad',23,45,'beautyandbeast')");
        MyDB.execSQL("Insert into books values(6,'Lost Apothecary','Sarah Penner','blablabla','ad',23,45,'lostapothecary')");
        MyDB.execSQL("Insert into books values(7,'Harry Potter','Joanne Rowling' ,'ella','Fantasy Literature',23,45,'harrypotter')");
        MyDB.execSQL("Insert into books values(8,'The Great Gatsby','Scott Fitzgerald','blablabla','ad',23,45,'thegreetgatsby')");
        MyDB.execSQL("Insert into books values(9,'Things we Lost to the water','Eric Nguyen','blablabla','ad',23,45,'thingswelost')");
        MyDB.execSQL("Insert into books values(10,'AL Chemist','Paulo Coelho','blablabla','Fantasy Fiction',23,45,'chemist')");
        MyDB.execSQL("Insert into books values(11,'It Ends with us','Colleen Hoover','blablabla','Romance',23,45,'itendswithus')");
        MyDB.execSQL("Insert into books values(12,'It start with us','Colleen Hoover','blablabla','Romance',23,45,'itsstartwithus')");
        MyDB.execSQL("Insert into books values(13,'Verity','Colleen Hoover','blablabla','Thriller',23,45,'verity')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users ");
        MyDB.execSQL("drop Table if exists books ");
        onCreate(MyDB);

    }

    public void addToCart(String username,String bookName, int book_price){
        ContentValues row= new ContentValues();
        row.put("username",username);
        row.put("bookName",bookName);
        row.put("book_price",book_price);

        bookstoreDB = getWritableDatabase();
        bookstoreDB.insert("cart",null,row);
        bookstoreDB.close();
    }
    public void addToFav(String username,String bookName){
        ContentValues row= new ContentValues();
        row.put("username",username);
        row.put("bookName",bookName);

        bookstoreDB = getWritableDatabase();
        bookstoreDB.insert("fav",null,row);
        bookstoreDB.close();
    }


    public void  insertUserData(String email, String username ,String password)
     {
         ContentValues row= new ContentValues();
         row.put("email",email);
         row.put("username",username);
         row.put("password",password);

         bookstoreDB = getWritableDatabase();
         bookstoreDB.insert("users",null,row);
         bookstoreDB.close();
     }

    public boolean Login(String input_email, String input_password) throws SQLException
    {
        bookstoreDB=getReadableDatabase();
        Cursor mCursor = bookstoreDB.rawQuery("SELECT * FROM users where email=? AND password=?", new String[]{input_email,input_password});

        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }

        bookstoreDB.close();
        return false;
    }

    public String getUsername(String email){
        bookstoreDB=getReadableDatabase();
        Cursor mCursor = bookstoreDB.rawQuery("SELECT username FROM users where email=?", new String[]{email});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                mCursor.moveToFirst();
                return mCursor.getString(0);
            }        else return null;

        }
        else return null;
    }

    public void insertBook(String title,String author, String about, String genre, int price,int quantity,String image_url)
    {
        ContentValues row= new ContentValues();
        row.put("title",title);
        row.put("author",author);
        row.put("about",about);
        row.put("genre",genre);
        row.put("price",price);
        row.put("quantity",quantity);
        row.put("image",image_url);

        bookstoreDB = getWritableDatabase();
        bookstoreDB.insert("books",null,row);
        bookstoreDB.close();
    }

    public ArrayList<String> getAllBooksName(){
        ArrayList<String> arrayList = new ArrayList<>();

        bookstoreDB=getReadableDatabase();
        Cursor books_cursor= bookstoreDB.rawQuery("Select title from books",null);

        books_cursor.moveToFirst();
        while(!books_cursor.isAfterLast()){
            String temp = books_cursor.getString(0);
            arrayList.add(temp);
            books_cursor.moveToNext();
        }

        books_cursor.close();
        return arrayList;
    }

    public ArrayList<String> getAllBooksImages(){
        ArrayList<String> arrayList = new ArrayList<>();

        bookstoreDB=getReadableDatabase();
        Cursor books_cursor= bookstoreDB.rawQuery("Select image from books",null);

        books_cursor.moveToFirst();
        while(!books_cursor.isAfterLast()){
            String temp = books_cursor.getString(0);
            arrayList.add(temp);
            books_cursor.moveToNext();
        }

        books_cursor.close();
        return arrayList;
    }
    public Cursor getAllCartBooks(String username){

        bookstoreDB=getReadableDatabase();
        Cursor books_cursor= bookstoreDB.rawQuery("Select bookName, book_price from cart where username=?",new String[]{username});

        books_cursor.moveToFirst();

        return books_cursor;
    }

    public Cursor getUserFav(String username){

        bookstoreDB=getReadableDatabase();
        Cursor books_cursor= bookstoreDB.rawQuery("Select bookName from fav where username=?",new String[]{username});

        books_cursor.moveToFirst();
        return books_cursor;

    }

    public Cursor getAllBooks(){
        bookstoreDB=getReadableDatabase();

        Cursor books_cursor= bookstoreDB.rawQuery("Select * from books",null);
        if(books_cursor.getCount()!=0){
            books_cursor.moveToFirst();
            bookstoreDB.close();
            return books_cursor;
        }
        bookstoreDB.close();
        return null;
    }

    public Cursor getSpecificBook(String BookTitle){
        bookstoreDB=getReadableDatabase();

        Cursor books_cursor= bookstoreDB.rawQuery("Select * from books where title like ?",new String[]{BookTitle});
        if(books_cursor.getCount()!=0){
            books_cursor.moveToFirst();
            bookstoreDB.close();
            return books_cursor;
        }
        bookstoreDB.close();
        return null;
    }

    public void ClearCart(String username){

        bookstoreDB=getWritableDatabase();
        bookstoreDB.execSQL("Delete from cart where username = ?",new String[]{username});
        bookstoreDB.close();
    }

    public ArrayList<String> getImages_Search(String title){
        ArrayList<String> arrayList = new ArrayList<>();

        bookstoreDB=getReadableDatabase();
        Cursor books_cursor= bookstoreDB.rawQuery("Select image from books WHERE title LIKE '"+ title + "%'",null);

        books_cursor.moveToFirst();
        while(!books_cursor.isAfterLast()){
            String temp = books_cursor.getString(0);
            arrayList.add(temp);
            books_cursor.moveToNext();
        }

        books_cursor.close();
        return arrayList;
    }
    public ArrayList<String> getBooksName_Search(String title){
        ArrayList<String> arrayList = new ArrayList<>();

        bookstoreDB=getReadableDatabase();
        Cursor books_cursor= bookstoreDB.rawQuery("Select title from books WHERE title LIKE '"+ title + "%'",null);

        books_cursor.moveToFirst();
        while(!books_cursor.isAfterLast()){
            String temp = books_cursor.getString(0);
            arrayList.add(temp);
            books_cursor.moveToNext();
        }

        books_cursor.close();
        return arrayList;
    }
}
