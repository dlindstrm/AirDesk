package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class AddFileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_file);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_file, menu);
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

        else if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Called when the user clicks the save button
    public void saveFile(View view) {
        User user = new User(this);
        user = user.getUser();

        File file = new File();

        EditText editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        EditText editTextContent = (EditText) findViewById(R.id.editTextContent);
        file.title = editTextTitle.getText().toString();
        file.content = editTextContent.getText().toString();
        file.author = user.email;
        Intent wsIntent = getIntent();
        int ws = wsIntent.getIntExtra("ws_Id", 0);
        file.ws = ws;
        FileRepo repo = new FileRepo(this);
        repo.insert(file);
        Toast.makeText(this, "File added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MyWorkspaceActivity.class);
        intent.putExtra("ws_Id",ws);
        startActivity(intent);
    }

    // Called when the user clicks the cancel button
    public void cancel(View view) {
        Intent intent = new Intent(this, MyWorkspaceActivity.class);
        startActivity(intent);
    }
}
