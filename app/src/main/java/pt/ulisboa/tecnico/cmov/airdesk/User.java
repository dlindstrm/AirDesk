package pt.ulisboa.tecnico.cmov.airdesk;

import java.util.ArrayDeque;

public class User {
    // Labels table name
    public static final String TABLE = "User";

    // Labels Table Columns names
    public static final String KEY_email = "email";
    public static final String KEY_fullName = "fullName";

    // property help us to keep data
    public String email;
    public String fullName;

    public static boolean isEmailAddress(String m){
        if (m.isEmpty()) return false;
        if (!m.contains("@")) return false; //email must contain an @

        String[] mArray = m.split("@");
        if (mArray.length != 2 ) return false; // only one @ allowed
        if (!mArray[1].contains(".")) return false; //There must be a dot after the @

        return true;
    }
}
