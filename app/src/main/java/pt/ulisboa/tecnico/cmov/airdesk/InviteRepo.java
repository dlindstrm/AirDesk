package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class InviteRepo {

    private DBHelper dbHelper;

    public InviteRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void insert(ArrayList<String> inviteList, int wsID) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        for (int i = 0; i < inviteList.size(); i++){
            ContentValues values = new ContentValues();
            values.put(Invite.KEY_workspaceID, wsID);
            values.put(Invite.KEY_email, inviteList.get(i));

            // Inserting Row
            long file_Id = db.insert(Invite.TABLE, null, values);
        }
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getInviteListByWsId(int WsId){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Invite.KEY_workspaceID + "," +
                Invite.KEY_email + "," +
                " FROM " + Invite.TABLE
                + " WHERE " +
                Invite.KEY_workspaceID + "=?";

        ArrayList<HashMap<String, String>> inviteList = new ArrayList<HashMap<String, String>>();


        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(WsId) } );

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> invite = new HashMap<String, String>();
                invite.put("id", cursor.getString(cursor.getColumnIndex(Invite.KEY_workspaceID)));
                invite.put("title", cursor.getString(cursor.getColumnIndex(Invite.KEY_email)));
                inviteList.add(invite);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return inviteList;
    }


}
