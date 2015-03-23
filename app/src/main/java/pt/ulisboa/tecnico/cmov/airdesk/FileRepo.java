package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class FileRepo {
    private DBHelper dbHelper;

    public FileRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(File file) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(File.KEY_content,file.content);
        values.put(File.KEY_title, file.title);

        // Inserting Row
        long file_Id = db.insert(File.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) file_Id;
    }

    public void delete(int file_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(File.TABLE, File.KEY_ID + "= ?", new String[] { String.valueOf(file_Id) });
        db.close(); // Closing database connection
    }

    public void update(File file) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(File.KEY_content,file.content);
        values.put(File.KEY_title, file.title);

        db.update(File.TABLE, values, File.KEY_ID + "= ?", new String[] { String.valueOf(file.file_ID) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>>  getFileList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                File.KEY_ID + "," +
                File.KEY_title + "," +
                File.KEY_content +
                " FROM " + File.TABLE;

        //File file = new File();
        ArrayList<HashMap<String, String>> fileList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> file = new HashMap<String, String>();
                file.put("id", cursor.getString(cursor.getColumnIndex(File.KEY_ID)));
                file.put("title", cursor.getString(cursor.getColumnIndex(File.KEY_title)));
                fileList.add(file);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return fileList;

    }

    public File getFileById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                File.KEY_ID + "," +
                File.KEY_title + "," +
                File.KEY_content +
                " FROM " + File.TABLE
                + " WHERE " +
                File.KEY_ID + "=?";

        int iCount =0;
        File file = new File();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                file.file_ID =cursor.getInt(cursor.getColumnIndex(File.KEY_ID));
                file.title =cursor.getString(cursor.getColumnIndex(File.KEY_title));
                file.content  =cursor.getString(cursor.getColumnIndex(File.KEY_content));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return file;
    }

}
