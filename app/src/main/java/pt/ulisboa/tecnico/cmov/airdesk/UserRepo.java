
package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class UserRepo {
    private DBHelper dbHelper;

    public UserRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insert(User user) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.KEY_email,user.email);
        values.put(User.KEY_fullName, user.fullName);

        // Inserting Row
        db.insert(User.TABLE, null, values);
        db.close(); // Closing database connection
    }

    public void delete(String email) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(User.TABLE, User.KEY_email + "= ?", new String[] { email });
        db.close(); // Closing database connection
    }

    public void update(User user) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User.KEY_email,user.email);
        values.put(User.KEY_fullName, user.fullName);

        db.update(User.TABLE, values, User.KEY_email + "= ?", new String[] { user.email });
        db.close(); // Closing database connection
    }



    public User getUser(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                User.KEY_email + ", " +
                User.KEY_fullName +
                " FROM " + User.TABLE;

        User user = new User();

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

