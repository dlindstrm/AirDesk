package pt.ulisboa.tecnico.cmov.airdesk;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class AddWsActivity extends ActionBarActivity {

    ArrayList<String> InviteList = new ArrayList<String>();
    int publicWs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ws);


        final EditText keyWords = (EditText) findViewById(R.id.editTextKeyWords);
        CheckBox CheckboxPublic = (CheckBox) findViewById(R.id.checkBoxPublic);

        // add onCheckedListener on checkbox
        // when user clicks on this checkbox, this is the handler.
        CheckboxPublic.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    publicWs = 0;
                    keyWords.setVisibility(View.GONE);
                } else {
                    publicWs = 1;
                    keyWords.setVisibility(View.VISIBLE);
                }
            }
        });
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

        return super.onOptionsItemSelected(item);
    }

    // Called when the user clicks the save button
    public void saveWs(View view) {

        Workspace ws = new Workspace();

        EditText editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        ws.title = editTextTitle.getText().toString();
        WorkspaceRepo repoWs = new WorkspaceRepo(this);
        ws.publicWs = publicWs;
        int wsID = repoWs.insert(ws);

        Toast.makeText(this, " " + ws.publicWs , Toast.LENGTH_SHORT).show();
        Toast.makeText(this, " " + ws.title , Toast.LENGTH_SHORT).show();


        //add to Invite list
        InviteRepo repoInvite = new InviteRepo(this);
        repoInvite.insert(InviteList, wsID);

        //add to Keword list
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

    public void addInvite(View view) {
        EditText editTextInvite = (EditText) findViewById(R.id.editTextInvite);
        String Invite = editTextInvite.getText().toString();
        if(!Invite.isEmpty()) {
            //add Invite email
            InviteList.add(Invite);
            Toast.makeText(this, Invite + " is added", Toast.LENGTH_SHORT).show();
            editTextInvite.setText("");
        }else{
            Toast.makeText(this, "You have to add a email", Toast.LENGTH_SHORT).show();
        }
    }
}
