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


        MyDB.execSQL("Insert into books values(1,'Beauty and the Beast','Gabrielle-Suzanne Barbot','A young girl who lived with her father and two sisters. They lived a good life in a big house but due to some unfortunate turn of events her father lost all his money. They were forced to move into a smaller house. The two sisters kept on grieving for their former life and Beauty maintained her sadness for herself and tried her best to help her family.','Fiction',52,45,'beautyandbeast')");
        MyDB.execSQL("Insert into books values(2,'The Hunger Games','Suzanne Collins','Katniss Everdeen, a 16-year-old girl who offers herself as a tribute in the annual Hunger Games in the post-apocalyptic world of Panem. Katniss undergoes several trials and tribulations as a part of the Hunger Games, which is a battle royale to the death of children aged between 12 and 18.','Dystopian ',100,45,'thehungergames')");
        MyDB.execSQL("Insert into books values(3,'Alice In WonderLand',' Lewis Carroll','A young girl named Alice falls down a rabbit-hole and finds herself in Wonderland, a fantasy land of strange characters and ideas.','Fantasy',23,45,'aliceinwonderland')");
        MyDB.execSQL("Insert into books values(4,'Anne of Green Gables','Lucy Maud Montgomery','Anne Shirley, an eleven-year-old orphan, has arrived in this verdant corner of Prince Edward Island only to discover that the Cuthberts—elderly Matthew and his stern sister, Marilla—want to adopt a boy, not a feisty redheaded girl. But before they can send her back, Anne—who simply must have more scope for her imagination and a real home—wins them over completely. ','Classic',85,45,'anneofgreengables')");
        MyDB.execSQL("Insert into books values(5,'War and Peace','Leo Tolstoy','War and Peacebroadly focuses on Napoleon'+'s invasion of Russia in 1812 and follows three of the most well-known characters in literature: Pierre Bezukhov, the illegitimate son of a count who is fighting for his inheritance and yearning for spiritual fulfillment','Novel',30,45,'warandpeace')");
        MyDB.execSQL("Insert into books values(6,'The Lost Apothecary','Sarah Penner','A female apothecary secretly dispenses poisons to liberate women from the men who have wronged them - setting three lives across centuries on a dangerous collision course.\n" +
                "\n" +
                "Rule #1: The poison must never be used to harm another woman.\n" +
                "Rule #2: The names of the murderer and her victim must be recorded in the apothecary’s register.','Novel',23,45,'lostapothecary')");
        MyDB.execSQL("Insert into books values(7,'Harry Potter','Joanne Rowling' ,'Harry Potter is a series of novels by British author J. K. Rowling. The novels follow Harry Potter, an 11-year-old boy who discovers he is the son of famous wizards and will attend Hogwarts School of Witchcraft and Wizardry.','Fantasy Literature',85,45,'harrypotter')");
        MyDB.execSQL("Insert into books values(8,'The Great Gatsby','Scott Fitzgerald','In spring 1922, Nick Carraway—a Yale alumnus from the Midwest and a World War I veteran—journeys to New York City to obtain employment as a bond salesman. He rents a bungalow in the Long Island village of West Egg, next to a luxurious estate inhabited by Jay Gatsby, an enigmatic multi-millionaire who hosts dazzling soirées yet does not partake in them.\n" +
                "\n','Tragedy',55,45,'thegreetgatsby')");
        MyDB.execSQL("Insert into books values(9,'Things we Lost to the water','Eric Nguyen','When Huong arrives in New Orleans with her two young sons, she is jobless, homeless, and worried about her husband, Cong, who remains in Vietnam. As she and her boys begin to settle in to life in America, she continues to send letters and tapes back to Cong, hopeful that they will be reunited and her children will grow up with a father.\n" +
                "\n" +
                "But with time, Huong realizes she will never see her husband again.','Novel',85,45,'thingswelost')");
        MyDB.execSQL("Insert into books values(10,'AL Chemist','Paulo Coelho','blablabla','Fantasy Fiction',62,45,'chemist')");
        MyDB.execSQL("Insert into books values(11,'It Ends with us','Colleen Hoover','it is a story of a womans struggle to break the cycle of abuse and rise above to create a safe life not only for herself but for her child. A tearjerker, this book is not only inspiring but also one of those books that leave an indelible mark on a readers heart','Romance',75,45,'itendswithus')");
        MyDB.execSQL("Insert into books values(12,'It start with us','Colleen Hoover','It Starts With Us is told from the alternating perspectives of two main characters, Atlas Corrigan and Lily Bloom. Lily deals with her chaotic life as a floral shop owner, single mom and co-parent with her ex-husband','Romance',66,45,'itsstartwithus')");
        MyDB.execSQL("Insert into books values(13,'Verity','Colleen Hoover','The Alchemist book summary tells the story of a young man who beat fear to follow his Personal Legend and how you can do the same thing too.','Thriller',23,45,'verity')");

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
        Cursor books_cursor= bookstoreDB.rawQuery("Select distinct bookName from fav where username=?",new String[]{username});

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
    public void ClearFav(String username){

        bookstoreDB=getWritableDatabase();
        bookstoreDB.execSQL("Delete from fav where username = ?",new String[]{username});
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
