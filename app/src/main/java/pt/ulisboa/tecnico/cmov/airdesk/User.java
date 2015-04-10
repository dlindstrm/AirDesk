package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class User {
    // Labels table name
    public static final String TABLE = "User";

    // Labels Table Columns names
    public static final String KEY_email = "email";
    public static final String KEY_fullName = "fullName";

    // property help us to keep data
    String email;
    String fullName;

    private DBHelper dbHelper;

    public User(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insert(String email, String fullName) {

        this.email = email;
        this.fullName = fullName;

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_email,email);
        values.put(User.KEY_fullName, fullName);

        // Inserting Row
        db.insert(User.TABLE, null, values);
        db.close(); // Closing database connection
    }

    public void delete(String email) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(User.TABLE, User.KEY_email + "= ?", new String[] { email });
        db.close(); // Closing database connection
    }

    public void update(String email, String fullName) {

        this.email = email;
        this.fullName = fullName;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User.KEY_email,email);
        values.put(User.KEY_fullName,fullName);

        db.update(User.TABLE, values, User.KEY_email + "= ?", new String[] { email });
        db.close(); // Closing database connection
    }



    public User getUser(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                User.KEY_email + ", " +
                User.KEY_fullName +
                " FROM " + User.TABLE;

        User user = this;

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                user.email =cursor.getString(cursor.getColumnIndex(User.KEY_email));
                user.fullName =cursor.getString(cursor.getColumnIndex(User.KEY_fullName));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return user;
    }



}
