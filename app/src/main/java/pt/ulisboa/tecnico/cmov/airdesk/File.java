package pt.ulisboa.tecnico.cmov.airdesk;

public class File {
    // Labels table name
    public static final String TABLE = "File";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_title = "title";
    public static final String KEY_content = "content";
    public static final String KEY_author = "author";
    public static final String KEY_createdAt = "createdAt";
    public static final String KEY_ws = "ws";

    // property help us to keep data
    public int file_ID;
    public String title;
    public String content;
    public String author;
    public String createdAt;
    public int ws;
}