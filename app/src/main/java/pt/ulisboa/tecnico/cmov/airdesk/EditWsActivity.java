package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Intent.getIntent;
import static android.support.v4.app.ActivityCompat.startActivity;

public class EditWsActivity extends ActionBarActivity {
    private int _Ws_Id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ws);

        Intent intent = getIntent();
        _Ws_Id = intent.getIntExtra("ws_Id", 0);
        WorkspaceRepo repo = new WorkspaceRepo(this);
        Workspace ws = repo.getWorkspaceById(_Ws_Id);
        EditText editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        EditText editTextInvite = (EditText) findViewById(R.id.editTextInvite);
        CheckBox checkBoxPublic = (CheckBox) findViewById(R.id.checkBoxPublic);
        EditText editTextKeywords = (EditText) findViewById(R.id.editTextKeyWords);
        editTextTitle.setText(ws.title);
//        checkBoxPublic.setChecked(ws.publicWs);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_ws, menu);
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

    public void saveWs(View view) {

        Workspace ws = new Workspace();

        EditText editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        ws.title = editTextTitle.getText().toString();
        WorkspaceRepo repoWs = new WorkspaceRepo(this);
        int wsID = repoWs.insert(ws);

        //add to Invite list
//        InviteRepo repoInvite = new InviteRepo(this);
//        repoInvite.insert(InviteList, wsID);

        //add to Keword list
//        ws.publicWs = publicWs;
        KeywordsRepo repoKeyword = new KeywordsRepo(this);
        EditText editTextKeyword = (EditText) findViewById(R.id.editTextKeyWords);
        String[] Keywords = editTextKeyword.getText().toString().split(" ");
        repoKeyword.insert(Keywords, wsID);

        Toast.makeText(this, "Workspace added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MyWorkspacesActivity.class);
        startActivity(intent);
    }

    // Called when the user clicks the cancel button
    public void cancel(View view) {
        Intent intent = new Intent(this, MyWorkspacesActivity.class);
        startActivity(intent);
    }
}
