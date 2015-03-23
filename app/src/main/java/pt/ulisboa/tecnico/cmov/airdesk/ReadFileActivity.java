package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ReadFileActivity extends ActionBarActivity {
    TextView fileTitle, fileContent;
    private int _File_Id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_file);

        fileTitle = (TextView) findViewById(R.id.file_title);
        fileContent = (TextView) findViewById(R.id.file_content);


        _File_Id =0;
        Intent intent = getIntent();
        _File_Id =intent.getIntExtra("file_Id", 0);
        FileRepo repo = new FileRepo(this);
        File file = repo.getFileById(_File_Id);

        fileTitle.setText(String.valueOf(file.title));
        fileContent.setText(file.content);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_read_file, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
