package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

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

}
