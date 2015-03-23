package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper  extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 4;

    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_FILE = "CREATE TABLE " + File.TABLE  + "("
                + File.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + File.KEY_title + " TEXT, "
                + File.KEY_content + " TEXT )";

        db.execSQL(CREATE_TABLE_FILE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone
        db.execSQL("DROP TABLE IF EXISTS " + File.TABLE);

        // Create tables again
        onCreate(db);

    }

}