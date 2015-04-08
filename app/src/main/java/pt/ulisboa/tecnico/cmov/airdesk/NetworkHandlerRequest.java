package pt.ulisboa.tecnico.cmov.airdesk;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class NetworkHandlerRequest {
    public ArrayList<HashMap<String, String>> getListWs(String email) {
        ArrayList<HashMap<String, String>> listOfWs = getWsByEmail(email);
        return listOfWs;
    }



    public ArrayList<HashMap<String, String>> getWsById(int wsId) {
        //Open connection to read only

        String DB_PATH = "/data/data/pt.ulisboa.tecnico.cmov.airdesk/databases/";
        String DB_NAME = "airDesk";
        SQLiteDatabase db =  SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String selectQuery =  "SELECT * FROM " +
                File.TABLE +
                " WHERE " + File.KEY_ws + "=?";

        ArrayList<HashMap<String, String>> listOfFiles = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { Integer.toString(wsId) });
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> file = new HashMap<String, String>();
                file.put("id", cursor.getString(cursor.getColumnIndex(File.KEY_ID)));
                file.put("title", cursor.getString(cursor.getColumnIndex(File.KEY_title)));
                listOfFiles.add(file);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listOfFiles;
    }


    public ArrayList<HashMap<String, String>> getWsByEmail(String email) {
        //Open connection to read only

        String DB_PATH = "/data/data/pt.ulisboa.tecnico.cmov.airdesk/databases/";
        String DB_NAME = "airDesk";
        SQLiteDatabase db =  SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String selectQuery =  "SELECT  " +
                Workspace.TABLE + "." +
                Workspace.KEY_ID+ ", " +
                Workspace.TABLE + "." +
                Workspace.KEY_title +
                " FROM " + Workspace.TABLE +
                " INNER JOIN " + Invite.TABLE +
                " ON " + Invite.TABLE + "." +
                Invite.KEY_workspaceID +
                "=" + Workspace.TABLE +
                "." + Workspace.KEY_ID +
                " WHERE " + Invite.KEY_email + "=?";

        ArrayList<HashMap<String, String>> listOfWs = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { email });
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> ws = new HashMap<String, String>();
                ws.put("id", cursor.getString(cursor.getColumnIndex(Workspace.KEY_ID)));
                ws.put("title", cursor.getString(cursor.getColumnIndex(File.KEY_title)));
                listOfWs.add(ws);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return listOfWs;

    }


    public File getFileById(int Id){
        String DB_PATH = "/data/data/pt.ulisboa.tecnico.cmov.airdesk/databases/";
        String DB_NAME = "airDesk";
        SQLiteDatabase db =  SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        String selectQuery =  "SELECT  " +
                File.KEY_ID + "," +
                File.KEY_title + "," +
                File.KEY_content + "," +
                File.KEY_author + "," +
                File.KEY_createdAt + "," +
                File.KEY_ws +
                " FROM " + File.TABLE
                + " WHERE " +
                File.KEY_ID + "=?";

        File file = new File();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                file.file_ID =cursor.getInt(cursor.getColumnIndex(File.KEY_ID));
                file.title =cursor.getString(cursor.getColumnIndex(File.KEY_title));
                file.content =cursor.getString(cursor.getColumnIndex(File.KEY_content));
                file.author =cursor.getString(cursor.getColumnIndex(File.KEY_author));
                file.createdAt =cursor.getString(cursor.getColumnIndex(File.KEY_createdAt));
                file.ws =cursor.getInt(cursor.getColumnIndex(File.KEY_ws));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return file;
    }

}